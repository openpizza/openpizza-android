package de.openpizza.android.views.host;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import de.openpizza.android.R;
import de.openpizza.android.dirty.OrderFacade;
import de.openpizza.android.service.data.Shop;
import de.openpizza.android.views.ShopView;

public class ShopViewHost extends ShopView {

	@Override
	protected int getMenuId() {
		return R.menu.shop_view_host;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.create_order_button) {
			openOrderActivity();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	private void openOrderActivity() {
		Intent intent = new Intent(getApplicationContext(),
				OrderActivityHost.class);
		startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		OrderFacade.newOrder(super.getShopId(), this);


	}

	@Override
	public void handlePutResponse(Shop Response) {
		// TODO Auto-generated method stub
		
	}
	
}
