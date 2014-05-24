package de.openpizza.android.views;

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
import de.openpizza.android.dirty.OrderFacade;
import de.openpizza.android.service.data.Product;

public class ProductView extends ActionBarActivity {


	private Product product;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_view);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.product_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.finish_edit_button) {
			saveItem();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void saveItem() {
		EditText quanEditText = (EditText) findViewById(R.id.product_count);

		// TODO: Update product if it dose allready exist
		Integer quantity = 0;
		try {
			quantity = Integer.parseInt(quanEditText.getText().toString());
			if (quantity != 0) {
				OrderFacade.addProduct(this.product, quantity);
			}
		} catch (Exception e) {

		}
		finish();

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
			View rootView = inflater.inflate(R.layout.fragment_product_view,
					container, false);
			return rootView;
		}
	}

}
