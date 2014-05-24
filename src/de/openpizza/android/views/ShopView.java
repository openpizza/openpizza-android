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
import android.view.MenuItem;

import com.google.gson.Gson;

import de.openpizza.android.Category;
import de.openpizza.android.R;
import de.openpizza.android.service.ShopIdService;
import de.openpizza.android.service.data.Shop;
import de.openpizza.android.service.restapi.RESTServiceCall;
import de.openpizza.android.service.restapi.RESTServiceHandler;
import de.openpizza.android.views.shopview.ShopViewTabsPagerAdapter;

public abstract class ShopView extends FragmentActivity implements ActionBar.TabListener {

	private ViewPager viewPager;
	private ShopViewTabsPagerAdapter mAdapter;
	private int shop_id;

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

	private static List<Category> product_categories2Categories(
			List<String> categories) {
		List<Category> categoryList = new ArrayList<Category>();
		for (String string : categories) {
			categoryList.add(new Category(string));
		}
		return categoryList;
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
				product_categories2Categories(response.getProduct_categories()));

		viewPager.setAdapter(mAdapter);
	}

	@Override
	public void handlePostResponse(Shop response) {
		// TODO Auto-generated method stub

	}
}
