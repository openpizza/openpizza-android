package de.openpizza.android.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import de.openpizza.android.R;
import de.openpizza.android.service.OrderService;
import de.openpizza.android.service.data.DeliveryAddress;
import de.openpizza.android.service.data.OrderRequest;
import de.openpizza.android.service.data.OrderResponse;
import de.openpizza.android.service.data.Product;
import de.openpizza.android.service.restapi.RESTServiceCall;
import de.openpizza.android.service.restapi.RESTServiceHandler;

public abstract class OrderActivity extends ActionBarActivity implements
		RESTServiceHandler<OrderResponse> {

	private Activity activity;
	private String nickname;
	private RESTServiceCall<OrderRequest, OrderResponse> service;
	Timer t;

	protected abstract int getMenuId();

	@Override
	public void handlePostResponse(OrderResponse response) {
		Log.v("handlePostResponse", new Gson().toJson(response));
		TextView link = (TextView) findViewById(R.id.link_text);
		link.setText(response.getShort_link());
		setqr(response.getShort_link());
	}

	@Override
	public void handleGetResponse(OrderResponse response) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		activity = this;
		
		

		ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
		final OrderActivity thisActivity = this;
		service = new OrderService(thisActivity);
		exec.scheduleAtFixedRate(new Runnable() {
		           public void run() {
		        	   runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							 OrderRequest request = new OrderRequest(2, 1, new DeliveryAddress(
										"KIT", "Am Fasanengarten 5", "67676", "Karlsruhe"), "");
								service.httpPost(request, thisActivity);
						}
					});
		        	  
		           }
		       }, 0, 60, TimeUnit.SECONDS); // execute every 60 seconds
		
		if (nickname == null) {
			showGetNickDialog();
		}
		service = new OrderService(this);
		OrderRequest request = new OrderRequest(2, 1, new DeliveryAddress(
				"KIT", "Am Fasanengarten 5", "67676", "Karlsruhe"));
		service.httpPost(request, this);

	}

	private void showGetNickDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Enter nickname:");

		// Set up the input
		final EditText input = new EditText(this);
		input.setInputType(InputType.TYPE_CLASS_TEXT);
		builder.setView(input);

		// Set up the buttons
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				nickname = input.getText().toString();
				setNickname(nickname);
			}

		});
		builder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
						finish();
					}
				});

		builder.show();

	}

	private void setNickname(String nickname) {
		this.nickname = nickname;
		TextView nickname_view = (TextView) findViewById(R.id.nickname_text);
		nickname_view.setText(nickname);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		int menuId = getMenuId();
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(menuId, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void setqr(String qrData) {
		ImageView iv = (ImageView) findViewById(R.id.link_qr);
		QRCodeWriter qrCodeEncoder = new QRCodeWriter();

		try {
			int colorBack = 0xFF000000;
			int colorFront = 0xFFFFFFFF;
			BitMatrix bitMatrix = qrCodeEncoder.encode(qrData,
					BarcodeFormat.QR_CODE, 500, 500);
			int width = bitMatrix.getWidth();
			int height = bitMatrix.getHeight();
			int[] pixels = new int[width * height];
			for (int y = 0; y < height; y++) {
				int offset = y * width;
				for (int x = 0; x < width; x++) {

					pixels[offset + x] = bitMatrix.get(x, y) ? colorBack
							: colorFront;
				}
			}

			Bitmap bitmap = Bitmap.createBitmap(width, height,
					Bitmap.Config.ARGB_8888);
			bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
			iv.setImageBitmap(bitmap);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_order,
					container, false);
			rootView = setupListView(rootView);
			return rootView;
		}

		private View setupListView(View rootView) {
			ListView listView = (ListView) rootView
					.findViewById(R.id.order_list);

			final List<Product> products = new ArrayList<Product>();
			ArrayAdapter<Product> adapter = new ArrayAdapter<Product>(
					getActivity(), android.R.layout.simple_list_item_2,
					android.R.id.text1, products) {
				@Override
				public View getView(int position, View convertView,
						ViewGroup parent) {
					View view = super.getView(position, convertView, parent);
					TextView text1 = (TextView) view
							.findViewById(android.R.id.text1);
					TextView text2 = (TextView) view
							.findViewById(android.R.id.text2);

					text1.setText(products.get(position).getName());
					text2.setText(products.get(position).getName());
					return view;
				}
			};
			listView.setAdapter(adapter);
			return rootView;
		}
	}

}
