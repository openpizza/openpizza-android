package de.openpizza.android.views.antihost;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import de.openpizza.android.R;
import de.openpizza.android.dirty.OrderFacade;
import de.openpizza.android.service.data.OrderResponse;
import de.openpizza.android.views.OrderActivity;

public class OrderActivityAntihost extends OrderActivity {

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
	}

	private void openShopView() {
		Intent intent = new Intent(getApplicationContext(),
				ShopViewAntihost.class);
		startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		OrderFacade.setModeleChangedListener(this);
		super.showGetNickDialog();
	}

	@Override
	public void setNickname(String nickname) {
		super.setNickname(nickname);
		OrderFacade.get("sssssssss", this.getParent());

	}

	@Override
	public void handlePutResponse(OrderResponse Response) {
		// TODO Auto-generated method stub
		
	}

}
