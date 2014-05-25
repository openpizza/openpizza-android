package de.openpizza.android.views.host;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.widget.EditText;
import de.openpizza.android.R;
import de.openpizza.android.ordermodul.NicknameHandler;
import de.openpizza.android.ordermodul.OrderFacade;
import de.openpizza.android.views.ShopView;

public class ShopViewHost extends ShopView implements NicknameHandler {

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
		showGetNickDialog(this);
	}

	protected void showGetNickDialog(final NicknameHandler nh) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Enter nickname:");

		// Set up the input
		final EditText input = new EditText(this);
		input.setInputType(InputType.TYPE_CLASS_TEXT);
		builder.setView(input);

		// Set up the buttons
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String nickname = input.getText().toString();
				nh.getNickname(nickname);
			}

		});
		builder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
						finish();
					}
				});

		builder.show();

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		OrderFacade.newOrder(super.getShopId(), this);

	}

	@Override
	public void getNickname(String nickname) {
		OrderFacade.setNickname(nickname);
		Intent intent = new Intent(getApplicationContext(),
				OrderActivityHost.class);
		startActivity(intent);
	}

}
