package de.openpizza.android.ordermodul;

import android.app.Activity;
import android.content.Context;
import de.openpizza.android.service.OrderService;
import de.openpizza.android.service.data.DeliveryAddress;
import de.openpizza.android.service.data.OrderRequest;
import de.openpizza.android.service.data.OrderResponse;
import de.openpizza.android.service.restapi.RESTServiceHandler;

public class CreateOrder implements RESTServiceHandler<OrderResponse> {

	private Context context;
	private OrderBean orderBean;
	private ModelChangedListener listener;

	public CreateOrder(OrderBean bean, Context context) {
		this.orderBean = bean;
		this.context = context;
	}

	public void createOrderOnServer() {
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setShop(orderBean.getShopid());
		OrderService service = new OrderService((Activity) this.context);
		service.httpPost(orderRequest, this);
	}

	public void fetchOrder(String id) {
		OrderService service = new OrderService((Activity) this.context);
		service.httpGet("orders/" + id, "", this);

	}

	public void sendOrderFinal(DeliveryAddress address, ModelChangedListener mcl) {
		this.listener = mcl;
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setShop(orderBean.getShopid());
		orderRequest.setAddress(address);
		orderRequest.setComplete(true);
		OrderService service = new OrderService((Activity) this.context);
		service.httpPut(orderRequest, this);
	}

	@Override
	public void handleGetResponse(OrderResponse response) {
		handleResponse(response);
	}

	@Override
	public void handlePostResponse(OrderResponse response) {
		handleResponse(response);
		OrderFacade.sentProducts();
	}

	private void handleResponse(OrderResponse response) {
		orderBean.setOrderResponse(response);
	}

	@Override
	public void handlePutResponse(OrderResponse response) {
		listener.onModelChanged(orderBean);
	}
}
