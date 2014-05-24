package de.openpizza.android.views.shopview;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import de.openpizza.android.Category;
import de.openpizza.android.service.data.Shop;

public class ShopViewTabsPagerAdapter extends FragmentPagerAdapter {

	private static final int fragmentBias = 1;
	private List<Category> categoryList;
	private Shop shop;

	public ShopViewTabsPagerAdapter(FragmentManager fm, List<Category> categoryList, Shop shop) {
		super(fm);
        this.categoryList = categoryList;
        this.shop = shop;
	}

	@Override
	public Fragment getItem(int index) {
		if(index == 0)
			return new ShowViewFragment(shop);

		return CategoryFragment.newInstance(categoryList.get(index-fragmentBias));
	}

	@Override
	public int getCount() {
		return categoryList.size() + fragmentBias;
	}

}
