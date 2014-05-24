package de.openpizza.android.service.data;

import java.util.List;

public class OrderContentRequest {
	private List<Product> products;
	private String comment;
	
	public OrderContentRequest() {
		// empty constructor for gson
	}

	public OrderContentRequest(List<Product> products, String comment) {
		super();
		this.products = products;
		this.comment = comment;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
