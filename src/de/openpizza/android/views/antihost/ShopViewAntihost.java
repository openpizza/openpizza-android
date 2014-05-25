package de.openpizza.android.views.antihost;

import android.view.MenuItem;
import de.openpizza.android.service.data.Shop;
import de.openpizza.android.views.ShopView;
import de.openpizza.android.R;

public class ShopViewAntihost extends ShopView {

	@Override
	protected int getMenuId() {
		return R.menu.shop_view_antihost; 
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
	public void handlePutResponse(Shop Response) {
		// TODO Auto-generated method stub
		
	}
}
