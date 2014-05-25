package de.openpizza.android.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import de.openpizza.android.R;
import de.openpizza.android.dirty.ModelChangedListener;
import de.openpizza.android.dirty.OrderBean;
import de.openpizza.android.service.data.OrderContentResponse;
import de.openpizza.android.service.data.Product;

public abstract class OrderActivity extends ActionBarActivity implements
		 ModelChangedListener {

	Timer t;
	private OrderBean orderBean;

	@Override
	public void onModelChanged(OrderBean orderBean) {
		setProductList(orderBean);
		setMember(orderBean.getProductFormOthers().size());
		setLinks(orderBean.getShortlink());
		setNickname(orderBean.getNickname());
	}

	private void setLinks(String str_link) {

		TextView link = (TextView) findViewById(R.id.link_text);
		link.setText(str_link);
		setqr(str_link);

	}

	private void setMember(int i) {
		TextView memberReady = (TextView) findViewById(R.id.finish_member);
		memberReady.setText(i + "");
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

	private void setupListView(final List<Product> products) {
		ListView listView = (ListView) findViewById(R.id.order_list);

		ArrayAdapter<Product> adapter = new ArrayAdapter<Product>(
				getApplicationContext(), android.R.layout.simple_list_item_2,
				android.R.id.text1, products) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = super.getView(position, convertView, parent);
				TextView text1 = (TextView) view
						.findViewById(android.R.id.text1);
				text1.setTextColor(Color.BLACK);
				TextView text2 = (TextView) view
						.findViewById(android.R.id.text2);
				text2.setTextColor(Color.BLACK);

				text1.setText(products.get(position).getName());
				text2.setText(products.get(position).getNickname());
				return view;
			}
		};
		listView.setAdapter(adapter);
	}

	private void setProductList(OrderBean orderBean) {

		List<OrderContentResponse> productFormOthers = orderBean.getProductFormOthers();
		List<Product> list = new ArrayList<Product>();

		for (OrderContentResponse cr : productFormOthers) {
			for (Product p : cr.getProducts()) {
				Log.d("test", cr.getNickname());
				p.setNickname(cr.getNickname());
				list.add(p);
			}
		}

		setupListView(list);
	}

	protected abstract int getMenuId();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
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

	public void setNickname(String nickname) {
		TextView nickname_view = (TextView) this.findViewById(R.id.nickname_text);
		nickname_view.setText(nickname);
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
			return rootView;
		}

	}

}
