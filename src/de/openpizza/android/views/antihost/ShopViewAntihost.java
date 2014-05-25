package de.openpizza.android.views.antihost;

import android.view.MenuItem;
import de.openpizza.android.R;
import de.openpizza.android.dirty.OrderFacade;
import de.openpizza.android.service.data.Shop;
import de.openpizza.android.views.ShopView;

public class ShopViewAntihost extends ShopView {

	@Override
	protected int getMenuId() {
		return R.menu.shop_view_host_edit;
	}

	private void returnToOrderActivity() {
		OrderFacade.sentProducts();
		finish();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.finish_edit_button) {
			returnToOrderActivity();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void handlePutResponse(Shop Response) {
		// TODO Auto-generated method stub
		
	}
}
