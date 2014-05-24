package de.openpizza.android.dirty;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import de.openpizza.android.service.data.OrderContentResponse;
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

	public static int getOrderMemberCount() {
		return INSTANCE.getProductFormOthers().size();
	}
	
	public static List<OrderContentResponse> getProductFormOthers() {
		return INSTANCE.getProductFormOthers();
	}

	public static String getLink() {
		return INSTANCE.getLink();
	}

	public static void get(String id,Activity context) {
		INSTANCE = new Order(context);
		INSTANCE.get(id);
	}


}
