package de.openpizza.android.views.host;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import de.openpizza.android.R;
import de.openpizza.android.dirty.OrderFacade;
import de.openpizza.android.service.data.OrderResponse;
import de.openpizza.android.views.OrderActivity;
import de.openpizza.android.views.SendOrderActivity;

public class OrderActivityHost extends OrderActivity {

	@Override
	protected int getMenuId() {
		return R.menu.order_host;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.finish_order_button) {
			openSendOrderActivity();
			return true;
		}

		if (id == R.id.edit_order_button) {
			openShopView();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void openSendOrderActivity() {
		Intent intent = new Intent(getApplicationContext(),
				SendOrderActivity.class);
		startActivity(intent);
		OrderFacade.removeAllListener();
	}

	private void openShopView() {
		Intent intent = new Intent(getApplicationContext(),
				ShopViewHostEdit.class);
		startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		OrderFacade.addModeleChangedListener(this);
		OrderFacade.createOrder();
		OrderFacade.startPulling(this);
	}

}
