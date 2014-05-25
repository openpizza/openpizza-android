package de.openpizza.android.views.shopview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import de.openpizza.android.R;
import de.openpizza.android.service.data.Shop;

@SuppressLint("ValidFragment")
public class ShowViewFragment extends Fragment {
	private TextView shop_name;
	private TextView street;
	private TextView plz;
	private TextView city;
	private ImageView image;
	private Shop shop;

	public ShowViewFragment() {
		super();
	}

	public ShowViewFragment(Shop shop) {
		super();
		this.shop = shop;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_shop_view,
				container, false);

		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

		shop_name = (TextView) getView().findViewById(R.id.shop_name);
		street = (TextView) getView().findViewById(R.id.shop_street);
		plz = (TextView) getView().findViewById(R.id.shop_postcode);
		city = (TextView) getView().findViewById(R.id.shop_city);

		shop_name.setText(shop.getName());
		street.setText(shop.getAddress());
		plz.setText(shop.getPostcode());
		city.setText(shop.getCity());

		image = (ImageView) getView().findViewById(R.id.shop_image);
		image.setImageResource(R.drawable.pizza5);
	}

}
