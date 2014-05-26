package de.openpizza.android.activitys.shopOverview.shopList;

import java.util.ArrayList;
import java.util.List;

import android.widget.ArrayAdapter;
import de.openpizza.android.service.data.Shop;

public class ShopList {
	
	private List<Shop> shopList = new ArrayList<Shop> ();

	public ShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}

	public void printOnArrayAdapter(ArrayAdapter<Shop> arrayAdapter) {
		arrayAdapter.addAll(shopList);
	}


}
