package de.openpizza.android;

import java.util.List;

import de.openpizza.android.service.data.Product;

public class Category {
	private String name;
	private List<Product> products;

	public Category(String name, List<Product> products) {
		super();
		this.name = name;
		this.products = products;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
