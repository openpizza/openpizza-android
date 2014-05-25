package de.openpizza.android.dirty;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import de.openpizza.android.service.data.DeliveryAddress;
import de.openpizza.android.service.data.Product;

public class Order{

	private Activity context;
	private List<ModelChangedListener> changedListeners = new ArrayList<ModelChangedListener>();
	private OrderBean orderBean;
	private CreateOrder createOrder;
	private SendOrder sendOrder;

	public void addListener(ModelChangedListener l) {
		changedListeners.add(l);
	}

	public Order(int shopid, Activity context) {
		this.context = context;
		this.orderBean = new OrderBean(this);
		this.orderBean.setShopid(shopid);
		this.createOrder = new CreateOrder(orderBean, context, context);
		this.sendOrder = new SendOrder(orderBean, context, context);
	}

	public Order(Activity context) {
		this.context = context;
		this.orderBean = new OrderBean(this);
		this.createOrder = new CreateOrder(orderBean, context, context);
		this.sendOrder = new SendOrder(orderBean, context, context);
	}

	public void createOrder() {
		createOrder.createOrderOnServer();
	}

	public void fetchOrder(String id) {
		createOrder.fetchOrder(id);
	}
	
	public void startPulling(Activity orderActivityHost) {
		sendOrder.startPulling(orderActivityHost);
	}
	
	public void sendProductList() {
		sendOrder.sendProductList();
	}
	
	public void fireModelChanged() {
		for (ModelChangedListener l : this.changedListeners) {
			l.onModelChanged(orderBean);
		}

	}

	public void addProduct(Product product, Integer quantity) {
		
		orderBean.addProduct(product, quantity);
		
	}

	public void setNickname(String nickname) {
		orderBean.setNickname(nickname);
	}

	public List<Product> getProductList() {
		return orderBean.getProductList();
	}

	public void sendOrderFinal(DeliveryAddress address, ModelChangedListener mcl){
		createOrder.sendOrderFinal(address, mcl);
	}

}
