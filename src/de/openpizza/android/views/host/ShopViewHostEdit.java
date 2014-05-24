package de.openpizza.android.views.host;

import android.content.Intent;
import android.view.MenuItem;
import de.openpizza.android.views.ShopView;
import de.openpizza.android.R;

public class ShopViewHostEdit extends ShopView {

	@Override
	protected int getMenuId() {
		return R.menu.shop_view_host_edit;
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


	private void returnToOrderActivity() {
		finish();
	}

}
