package de.openpizza.android.activitys.shopOverview.shopList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import de.openpizza.android.activitys.shop.ShopView;
import de.openpizza.android.service.data.Shop;


public class ShopListArrayAdapter extends ArrayAdapter<Shop> implements ShopListView {

	public ShopListArrayAdapter(Context context, int resource) {
		super(context, resource);
	}

	@Override
	public View getView(int position, View shopView, ViewGroup parent) {

		if (shopView == null || !(shopView instanceof ShopView)) {
			shopView = new ShopListItem(getContext());
		}

		Shop shop = this.getItem(position);
		shop.printToView((ShopView) shopView);

		return shopView;
	}

	@Override
	public void printShopList(ShopList shopList) {
		clear();
		shopList.printOnArrayAdapter(this);
		notifyDataSetChanged();
	}

}