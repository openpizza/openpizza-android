package de.openpizza.android.dirty;

import android.app.Activity;
import android.content.Context;
import de.openpizza.android.service.data.Product;

public class OrderFacade {
	
	private static Order INCTANCE;

	private OrderFacade() {

		
	}
	
	public static void newOrder(int shopid, Activity context) {
		INCTANCE = new Order(shopid, context);
	}
	
	public static void addProduct(Product product, int quantity) {
		product.setQuantity(quantity);
		INCTANCE.addProduct(product);
	}
	
	public static void publish() {
		INCTANCE.publish();
	}
	
	public static void sentProducts() {
		INCTANCE.sendProductList();
	}

	public static void setModeleChangedListener(
			ModelChangedListener mcl) {
		INCTANCE.addListener(mcl);
	}

	public static void setNickname(String nickname) {
		INCTANCE.setNickname(nickname);
	}
	

}
