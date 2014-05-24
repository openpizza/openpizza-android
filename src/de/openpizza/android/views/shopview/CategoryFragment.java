package de.openpizza.android.views.shopview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import de.openpizza.android.Category;
import de.openpizza.android.Product;
import de.openpizza.android.R;
import de.openpizza.android.views.ProductView;

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
		productList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Product product = (Product) view.getTag();
				// IntentSafe.getInstance().setData(bill.getId(), bill);
				Intent intent = new Intent(getActivity(),
						ProductView.class);
				intent.putExtra("productId", product.getId());
				startActivity(intent);
			}
		});
		
		return rootView;
	}

}