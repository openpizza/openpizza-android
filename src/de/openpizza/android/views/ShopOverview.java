package de.openpizza.android.views;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import de.openpizza.android.R;
import de.openpizza.android.service.ShopsService;
import de.openpizza.android.service.data.Shop;
import de.openpizza.android.service.restapi.RESTServiceCall;
import de.openpizza.android.service.restapi.RESTServiceHandler;

	public class ShopOverview extends ActionBarActivity implements
			RESTServiceHandler<List<Shop>> {

	private RESTServiceCall<Void, List<Shop>> service;
	private PlaceholderFragment shopListFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop_overview);

		if (savedInstanceState == null) {
			shopListFragment = new PlaceholderFragment();
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, shopListFragment).commit();
		}

		service = new ShopsService(this);
		service.httpGet("shops", "", this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shop_overview, menu);
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

		private ShopListArrayAdapter listViewAdapter;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_shop_overview,
					container, false);

			rootView = setupCitySpinner(rootView);

			rootView = setupListView(rootView);

			return rootView;
		}
		
		public void setData(List<Shop> data) {
			listViewAdapter.setShopList(data);
		}

		private View setupListView(View rootView) {
			ListView listView = (ListView) rootView
					.findViewById(R.id.listView_shops);

			List<Shop> shopList = new ArrayList<Shop>();
			listViewAdapter = new ShopListArrayAdapter(
					getActivity(), R.layout.fragment_shop_overview, shopList);
			listView.setAdapter(listViewAdapter);
			return rootView;
		}

		private View setupCitySpinner(View rootView) {
			Spinner citySpinner = (Spinner) rootView
					.findViewById(R.id.spinner_select_city);
			List<City> list = new ArrayList<City>();
			list.add(new City("1"));
			list.add(new City("2"));
			list.add(new City("3"));
			ArrayAdapter<City> dataAdapter = new ArrayAdapter<City>(
					this.getActivity(),
					android.R.layout.simple_spinner_dropdown_item, list);
			dataAdapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			citySpinner.setAdapter(dataAdapter);
			citySpinner
					.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> arg0,
								View arg1, int arg2, long arg3) {
							// TODO: Add load shops here
							Log.d("item",
									((City) arg0.getAdapter().getItem(arg2)).id);
						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub

						}

					});
			return rootView;
		}
	}

	@Override
	public void handleGetResponse(List<Shop> response) {
		Log.v("Daten sind angekommen", new Gson().toJson(response));
		shopListFragment.setData(response);
	}

	@Override
	public void handlePostResponse(List<Shop> response) {
		// TODO Auto-generated method stub

	}

}

class ShopListArrayAdapter extends ArrayAdapter<Shop> {

	private List<Shop> shopList;

	public ShopListArrayAdapter(Context context, int resource,
			List<Shop> shopList) {
		super(context, resource, shopList);
		this.setShopList(shopList);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Shop shop = getShopList().get(position);
		View row = convertView;
		LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		row = vi.inflate(R.layout.list_item_shop_list, null);
		row.setTag(shop);
		ImageView shopImage = (ImageView) row.findViewById(R.id.shop_image);

		TextView name = (TextView) row.findViewById(R.id.shop_name);
		name.setText(shop.getName());
		return row;
	}



	public List<Shop> getShopList() {
		return shopList;
	}



	public void setShopList(List<Shop> shopList) {
		clear();
		this.shopList = shopList;
		addAll(shopList);
		notifyDataSetChanged();
		Log.v("setShoplist()", "NotifyDataSetChanged()");
	}

}

class City {
	String name = "test";
	String id;

	public City(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return name;
	}
}
