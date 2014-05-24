package de.openpizza.android.dirty;

import android.app.Activity;
import android.content.Context;
import de.openpizza.android.service.data.Product;

public class OrderFacade {
	
	private static Order INSTANCE;

	private OrderFacade() {

		
	}
	
	public static void newOrder(int shopid, Activity context) {
		INSTANCE = new Order(shopid, context);
	}
	
	public static void addProduct(Product product, int quantity) {
		product.setQuantity(quantity);
		INSTANCE.addProduct(product);
	}
	
	public static void publish() {
		INSTANCE.publish();
	}
	
	public static void sentProducts() {
		INSTANCE.sendProductList();
	}

	public static void setModeleChangedListener(
			ModelChangedListener mcl) {
		INSTANCE.addListener(mcl);
	}

	public static void setNickname(String nickname) {
		INSTANCE.setNickname(nickname);
	}
	

}
