package de.openpizza.android.activitys.shopOverview.shopList;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import de.openpizza.android.R;
import de.openpizza.android.activitys.shop.ShopView;

public class ShopListItem extends RelativeLayout implements ShopView {

	private ImageView shopImage;
	private TextView nameView;

	public ShopListItem(Context context) {
		super(context);
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.list_item_shop_list, this, true);
		
		shopImage = (ImageView) this.findViewById(R.id.shop_image);
		nameView = (TextView) this.findViewById(R.id.shop_name);

	}


	@Override
	public void printName(String name) {
		nameView.setText(name);
	}

	@Override
	public void printImage(Uri uri) {
		shopImage.setImageURI(uri);
	}
}
