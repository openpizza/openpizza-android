package de.openpizza.android.activitys.shopOverview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import de.openpizza.android.R;
import de.openpizza.android.activitys.shopOverview.shopList.ShopListArrayAdapter;
import de.openpizza.android.service.data.Shop;
import de.openpizza.android.views.host.ShopViewHost;


public class ShopOverviewFragment extends Fragment {
	
	
	public ShopOverviewFragment() {
		super();
	}


	private ShopListArrayAdapter listViewAdapter;
	private ShopOverviewCoordinator coordinator;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		this.listViewAdapter = new ShopListArrayAdapter(getActivity(),
				R.layout.fragment_shop_overview);

		Context context = getActivity().getApplicationContext();
		this.coordinator = new ShopOverviewCoordinator(listViewAdapter, context);

		View rootView = inflater.inflate(R.layout.fragment_shop_overview,
				container, false);

		rootView = setupCitySpinner(rootView);

		rootView = setupListView(rootView);

		return rootView;
	}

	private View setupListView(View rootView) {
		ListView listView = (ListView) rootView
				.findViewById(R.id.listView_shops);

		listView.setAdapter(listViewAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Shop shop = (Shop) parent.getItemAtPosition(position);
				openShopView(shop);
			}

		});
		return rootView;
	}

	private void openShopView(Shop shop) {
		// IntentSafe.getInstance().setData(bill.getId(), bill);
		Intent intent = new Intent(getActivity(), ShopViewHost.class);
		intent.putExtra("SHOP_ID", shop.getId());
		startActivity(intent);
	}

	// TODO: #2
	private View setupCitySpinner(View rootView) {
		Spinner citySpinner = (Spinner) rootView
				.findViewById(R.id.spinner_select_city);
		List<City> list = new ArrayList<City>();
		list.add(new City("1", "Karlsruhe"));
		list.add(new City("2", "München"));
		list.add(new City("3", "Berlin"));
		ArrayAdapter<City> dataAdapter = new ArrayAdapter<City>(
				this.getActivity(),
				android.R.layout.simple_spinner_dropdown_item, list);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		citySpinner.setAdapter(dataAdapter);

		OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> _1, View _2, int _3,
					long _4) {
				coordinator.loadShopsForPostal("12345");
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		};
		citySpinner.setOnItemSelectedListener(onItemSelectedListener);

		return rootView;
	}
}