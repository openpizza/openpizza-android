package de.openpizza.android.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.media.audiofx.Equalizer;
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
import de.openpizza.android.service.data.Product;
import de.openpizza.android.service.data.Shop;
import de.openpizza.android.service.restapi.RESTServiceCall;
import de.openpizza.android.service.restapi.RESTServiceHandler;
import de.openpizza.android.views.shopview.ShopViewTabsPagerAdapter;

public class ShopView extends FragmentActivity implements
		ActionBar.TabListener, RESTServiceHandler<Shop> {

	private ViewPager viewPager;
	private ShopViewTabsPagerAdapter mAdapter;
	private int shop_id;

	RESTServiceCall<Void, Shop> service;

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

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shop_view, menu);
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
				shop2Categories(response));

		viewPager.setAdapter(mAdapter);
	}

	@Override
	public void handlePostResponse(Shop response) {
		// TODO Auto-generated method stub

	}
}
