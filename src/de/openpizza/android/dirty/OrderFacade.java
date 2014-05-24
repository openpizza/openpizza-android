package de.openpizza.android.dirty;

import de.openpizza.android.service.data.Product;

public class OrderFacade {
	
	private static Order INCTANCE;

	private OrderFacade() {

		
	}
	
	public static void newOrder(int shopid) {
		INCTANCE = new Order(shopid);
	}
	
	public static void addProduct(String nickname, Product product, int quantity) {
		product.setQuantity(quantity);
		INCTANCE.addProduct(product);
	}
	
	public void publish() {
		INCTANCE.publish();
	}
	
	public void sentProducts() {
		INCTANCE.sendProductList();
	}
	

}
