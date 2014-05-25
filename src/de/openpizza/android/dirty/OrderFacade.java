package de.openpizza.android.dirty;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import de.openpizza.android.service.data.DeliveryAddress;
import de.openpizza.android.service.data.OrderContentResponse;
import de.openpizza.android.service.data.Product;
import de.openpizza.android.views.host.OrderActivityHost;

public class OrderFacade {
	
	private static Order INSTANCE;

	private OrderFacade() {

		
	}
	
	public static void newOrder(int shopid, Activity context) {
		INSTANCE = new Order(shopid, context);
	}
	
	public static void sentProducts() {
		INSTANCE.sendProductList();
	}
	
	public static void createOrder() {
		INSTANCE.createOrder();
	}
	
	public static void fetchOrder(String id, Activity context) {
		INSTANCE = new Order(context);
		INSTANCE.fetchOrder(id);
	}

	public static void startPulling(Activity orderActivityHost) {
		INSTANCE.startPulling(orderActivityHost);
	}

	public static void addModeleChangedListener(
			ModelChangedListener mcl) {
		INSTANCE.addListener(mcl);
	}
	
	public static void removeAllListener() {
		INSTANCE.removeAllListener();
		
	}

	public static void addProduct(Product product, Integer quantity) {
		INSTANCE.addProduct(product, quantity);
		
	}

	public static void setNickname(String nickname) {
		INSTANCE.setNickname(nickname);
		
	}

	public static List<Product> getProductList() {
		return INSTANCE.getProductList();
	}

	public static void sendOrderFinal(DeliveryAddress address, ModelChangedListener mcl){
		INSTANCE.sendOrderFinal(address, mcl);
	}

}
