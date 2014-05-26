package de.openpizza.android.service.data;

import java.util.List;

import android.net.Uri;
import de.openpizza.android.activitys.shop.ShopView;

/**
 * { id: int, name: string, address: string, postcode: string, city: string, }
 * 
 */
public class Shop {
	private int id;
	private String name;
	private String address;
	private String postcode;
	private String city;
	private List<String> product_categories;
	private List<Product> products;
	private String imageUri;

	public Shop() {
		// Empty constructor needed for gson
	}

	public Shop(int id, String name, String address, String postcode,
			String city, List<String> product_categories, List<Product> products) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.postcode = postcode;
		this.city = city;
		this.product_categories = product_categories;
		this.products = products;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<String> getProduct_categories() {
		return product_categories;
	}

	public void setProduct_categories(List<String> product_categories) {
		this.product_categories = product_categories;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void printToView(ShopView shopView) {
		shopView.printName(this.name);
		Uri uri = new Uri.Builder().appendPath(this.imageUri).build();
		shopView.printImage(uri);
		
	}

}
