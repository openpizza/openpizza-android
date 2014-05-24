package de.openpizza.android.views.shopview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import de.openpizza.android.Category;
import de.openpizza.android.Product;
import de.openpizza.android.R;

@SuppressLint("NewApi")
public class CategoryFragment extends Fragment {

	private Category category;

	public CategoryFragment() {
	}

	public static final CategoryFragment newInstance(Category category)
	{
		CategoryFragment categoryFragment = new CategoryFragment();
		categoryFragment.category = category;

	    return categoryFragment;
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_shop_view_category,
				container, false);
		
		TextView categoryNameView = (TextView) rootView.findViewById(R.id.category_name);
		categoryNameView.setText(category.getName());

		ListView productList = (ListView) rootView.findViewById(R.id.category_list);
		ArrayAdapter<Product> dataAdapter = new ArrayAdapter<Product>(this.getActivity(),
				android.R.layout.simple_list_item_1, category.getProductList());
		
		productList.setAdapter(dataAdapter);
		
		return rootView;
	}

}