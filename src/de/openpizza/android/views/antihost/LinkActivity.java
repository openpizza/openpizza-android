package de.openpizza.android.views.antihost;

import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import de.openpizza.android.R;
import de.openpizza.android.dirty.NicknameHandler;
import de.openpizza.android.dirty.OrderFacade;

public class LinkActivity extends ActionBarActivity implements NicknameHandler {
	private String orderId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_link);

		Uri data = getIntent().getData();
		List<String> params = data.getPathSegments();
		orderId = params.get(1); // "id"

		OrderFacade.fetchOrder(orderId, this);

		if (savedInstanceState == null) {
			PlaceholderFragment placeholderFragment = new PlaceholderFragment();
			Bundle bundle = new Bundle();
			bundle.putString("id", orderId);
			placeholderFragment.setArguments(bundle);
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, placeholderFragment).commit();
		}

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
	public void getNickname(String nickname) {
		OrderFacade.setNickname(nickname);
		Intent intent = new Intent(this, OrderActivityAntihost.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.link, menu);
		return true;
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_link, container,
					false);
			return rootView;
		}

		@Override
		public void onViewCreated(View view, Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onViewCreated(view, savedInstanceState);
			TextView link_view = (TextView) getView().findViewById(
					R.id.link_text_view);
			Bundle bundle = getArguments();
			String orderId = bundle.getString("id");
			link_view.setText("Deine Order ID ist: " + orderId);
		}
	}

}
