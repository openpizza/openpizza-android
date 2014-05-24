package de.openpizza.android;

import java.util.ArrayList;
import java.util.List;

public class Category {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category(String name) {
		super();
		this.name = name;
	}

	public List<Product> getProductList() {
		List<Product> test = new ArrayList<Product>();
		test.add(new Product());
		test.add(new Product());
		test.add(new Product());
		test.add(new Product());
		test.add(new Product());
		test.add(new Product());
		test.add(new Product());
		test.add(new Product());
		test.add(new Product());
		test.add(new Product());
		test.add(new Product());
		test.add(new Product());
		test.add(new Product());

		return test;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

}
