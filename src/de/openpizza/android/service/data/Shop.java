package de.openpizza.android.service.data;

import java.util.List;

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

	public Shop() {
		// Empty constructor needed for gson
	}

	public Shop(int id, String name, String address, String postcode,
			String city, List<String> product_categories) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.postcode = postcode;
		this.city = city;
		this.product_categories = product_categories;
	}

	public List<String> getProduct_categories() {
		return product_categories;
	}

	public void setProduct_categories(List<String> product_categories) {
		this.product_categories = product_categories;
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

}
