package de.openpizza.android.service.data;

import java.util.List;

public class OrderContentResponse {
	private String nickname;
	private List<Product> products;
	private String comment;
	private double price;

	public OrderContentResponse() {
		// empty constructor for gson
	}

	public OrderContentResponse(String nickname, List<Product> products,
			String comment, double price) {
		super();
		this.nickname = nickname;
		this.products = products;
		this.comment = comment;
		this.price = price;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
