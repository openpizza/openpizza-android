package de.openpizza.android.service.data;

public class Product {
	private int id;
	private String name;
	private String category;
	private int quantity;
	private double price;
	private String nickname;

	public Product() {
		// empty constructor for gson
	}

	public Product(int id, String name, String category, int quantity,
			double price) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.quantity = quantity;
		this.price = price;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return getName();
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;

	}

	public String getNickname() {
		return nickname;
	}
}
