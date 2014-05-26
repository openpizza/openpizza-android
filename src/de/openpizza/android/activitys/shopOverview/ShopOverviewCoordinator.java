package de.openpizza.android.activitys.shopOverview;

import java.util.List;

import android.content.Context;
import de.openpizza.android.activitys.shopOverview.shopList.ShopList;
import de.openpizza.android.activitys.shopOverview.shopList.ShopListView;
import de.openpizza.android.service.ShopsService;
import de.openpizza.android.service.data.Shop;
import de.openpizza.android.service.restapi.RESTServiceHandler;

public class ShopOverviewCoordinator implements RESTServiceHandler<List<Shop>> {

	private ShopsService service;
	private ShopList shopList;
	private ShopListView shopListView;

	public ShopOverviewCoordinator(ShopListView shopListView, Context context) {
		this.shopListView = shopListView;
		service = new ShopsService(context);
		service.httpGet("shops", "", this);
	}

	@Override
	public void handleGetResponse(List<Shop> response) {

		this.shopList = new ShopList(response);
		shopListView.printShopList(this.shopList);
		
	}

	@Override
	public void handlePostResponse(List<Shop> response) {

		this.shopList = new ShopList(response);
		shopListView.printShopList(this.shopList);
		
	}

	@Override
	public void handlePutResponse(List<Shop> Response) {
		
	}

	public void loadShopsForPostal(String postcode) {
		service.httpGet("shops", "postcode=" + postcode, this);
	}
}
