package de.openpizza.android.views;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.view.Menu;
import android.view.MenuItem;
import de.openpizza.android.Category;
import de.openpizza.android.R;
import de.openpizza.android.views.shopview.ShopViewTabsPagerAdapter;

public abstract class ShopView extends FragmentActivity implements ActionBar.TabListener {

	private ViewPager viewPager;
	private ShopViewTabsPagerAdapter mAdapter;


	/** give the Menu to load**/
	protected abstract int getMenuId();
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop_view);

		List<Category> categoryList = new ArrayList<Category>();
		categoryList.add(new Category());
		categoryList.add(new Category());
		categoryList.add(new Category());
		// Initilization
        viewPager = (ViewPager) findViewById(R.id.container);
        mAdapter = new ShopViewTabsPagerAdapter(getSupportFragmentManager(), categoryList );

 
        viewPager.setAdapter(mAdapter);

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
}
