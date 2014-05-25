package de.openpizza.android.views;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.google.gson.Gson;

import de.openpizza.android.Category;
import de.openpizza.android.R;
import de.openpizza.android.service.ShopIdService;
import de.openpizza.android.service.data.Product;
import de.openpizza.android.service.data.Shop;
import de.openpizza.android.service.restapi.RESTServiceCall;
import de.openpizza.android.service.restapi.RESTServiceHandler;
import de.openpizza.android.views.shopview.ShopViewTabsPagerAdapter;

public abstract class ShopView extends FragmentActivity implements ActionBar.TabListener, RESTServiceHandler<Shop> {

	private ViewPager viewPager;
	private ShopViewTabsPagerAdapter mAdapter;
	private int shop_id;
	private TextView shop_name;
	private TextView street;
	private TextView plz;
	private TextView city;

	RESTServiceCall<Void, Shop> service;

	/** give the Menu to load**/
	protected abstract int getMenuId();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop_view);

		shop_id = getIntent().getIntExtra("SHOP_ID", 1);
		service = new ShopIdService(this);
		service.httpGet("shops/" + shop_id, "", this);
		// Initilization
		viewPager = (ViewPager) findViewById(R.id.container);
	}

	private static List<Category> shop2Categories(Shop shop) {
		List<Category> categories = new ArrayList<Category>();

		for (String category : shop.getProduct_categories()) {
			categories.add(new Category(category, new ArrayList<Product>()));
		}

		for (Product product : shop.getProducts()) {
			for(Category cat : categories) {
				if (cat.getName().equals(product.getCategory())) {
					cat.getProducts().add(product);
				}
			}
		}
		return categories;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		int menuId = getMenuId();
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(menuId, menu);
		return true;
	}


	

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleGetResponse(Shop response) {
		Log.v("ShopView.java hat Daten erhalten", new Gson().toJson(response));
		mAdapter = new ShopViewTabsPagerAdapter(getSupportFragmentManager(),
				shop2Categories(response), response);

		viewPager.setAdapter(mAdapter);
		
		shop_name = (TextView) findViewById(R.id.shop_name);
		street = (TextView) findViewById(R.id.shop_street);
		plz = (TextView) findViewById(R.id.shop_postcode);
		city  = (TextView) findViewById(R.id.shop_city);
		
		shop_name.setText(response.getName());
		street.setText(response.getAddress());
		plz.setText(response.getPostcode());
		city.setText(response.getCity());
	}

	@Override
	public void handlePostResponse(Shop response) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void handlePutResponse(Shop response) {
		// TODO Auto-generated method stub

	}


	public int getShopId() {
		return this.shop_id;
	}
}
