package de.openpizza.android.views;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import de.openpizza.android.R;
import de.openpizza.android.dirty.ModelChangedListener;
import de.openpizza.android.dirty.OrderBean;
import de.openpizza.android.dirty.OrderFacade;
import de.openpizza.android.service.data.DeliveryAddress;

public class SendOrderActivity extends ActionBarActivity implements
		ModelChangedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_order);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.send_order, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.send_button) {
			sendOrder();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void sendOrder() {
		DeliveryAddress deaddress = new DeliveryAddress();

		EditText address_et = (EditText) findViewById(R.id.street_text);
		EditText postal_et = (EditText) findViewById(R.id.postal_code_text);
		EditText city_et = (EditText) findViewById(R.id.city_text);
		EditText name_et = (EditText) findViewById(R.id.name);

		String address = address_et.getText().toString().trim();
		String city = city_et.getText().toString().trim();
		String name = name_et.getText().toString().trim();
		String postcode = postal_et.getText().toString().trim();

		if (address.length() != 0 && city.length() != 0 && name.length() != 0
				&& postcode.length() != 0) {

			deaddress.setAddress(address);
			deaddress.setCity(city);
			deaddress.setName(name);
			deaddress.setPostcode(postcode);

			OrderFacade.sendOrderFinal(deaddress, this);
		}

	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_send_order,
					container, false);
			return rootView;
		}
	}

	@Override
	public void onModelChanged(OrderBean orderBean) {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Vielen Dank für die Aufmerksamkeit :P");
		builder.show();

	}

}
