package de.openpizza.android.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import de.openpizza.android.R;
import de.openpizza.android.service.OrderService;
import de.openpizza.android.service.data.DeliveryAddress;
import de.openpizza.android.service.data.OrderRequest;
import de.openpizza.android.service.data.OrderResponse;
import de.openpizza.android.service.restapi.RESTServiceCall;
import de.openpizza.android.service.restapi.RESTServiceHandler;

public abstract class OrderActivity extends ActionBarActivity implements RESTServiceHandler<OrderResponse> {

	private String nickname;
	private RESTServiceCall<OrderRequest, OrderResponse> service;

	protected abstract int getMenuId();
	
	@Override
	public void handlePostResponse(OrderResponse response) {
		Log.v("handlePostResponse", new Gson().toJson(response));
		TextView link = (TextView) findViewById(R.id.link_text);
		link.setText(response.getShort_link());
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

		nickname = "florian";
		service = new OrderService(this);
		OrderRequest request = new OrderRequest(2, 1, new DeliveryAddress(
				"KIT", "Am Fasanengarten 5", "67676", "Karlsruhe"));
		service.httpPost(request, this);
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
