package de.openpizza.android.dirty;

import java.util.ArrayList;
import java.util.List;

import de.openpizza.android.service.data.OrderContentRequest;
import de.openpizza.android.service.data.OrderRequest;
import de.openpizza.android.service.data.Product;

public class Order {

	private List<Product> productList = new ArrayList<Product>();
	private String nickname;
	private int shopid;

	public Order(int shopid) {
		this.shopid = shopid;
	}

	public String getNickname() {
		return nickname;

	}

	public void addProduct(Product product) {
		productList.add(product);
	}

	public void publish() {
		createOrderOnServer();
	}

	private void createOrderOnServer() {
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setShop(shopid);

	}

	public void sendProductList() {
		if (!productList.isEmpty()) {
			OrderContentRequest orderContentRequest = new OrderContentRequest();
			orderContentRequest.setProducts(productList);
			orderContentRequest.setNickname(nickname);
		}
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
