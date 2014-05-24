package de.openpizza.android.views.shopview;

import java.util.List;

import de.openpizza.android.Category;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ShopViewTabsPagerAdapter extends FragmentPagerAdapter {

	private static final int fragmentBias = 1;
	private List<Category> categoryList;

	public ShopViewTabsPagerAdapter(FragmentManager fm, List<Category> categoryList) {
		super(fm);
        this.categoryList = categoryList;
	}

	@Override
	public Fragment getItem(int index) {
		if(index == 0)
			return new ShowViewFragment();

		return CategoryFragment.newInstance(categoryList.get(index-fragmentBias));
		
	}

	@Override
	public int getCount() {
		return categoryList.size() + fragmentBias;
	}

}
