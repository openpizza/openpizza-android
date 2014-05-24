package de.openpizza.android.dirty;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Context;
import de.openpizza.android.service.OrderService;
import de.openpizza.android.service.data.DeliveryAddress;
import de.openpizza.android.service.data.OrderContentRequest;
import de.openpizza.android.service.data.OrderRequest;
import de.openpizza.android.service.data.OrderResponse;
import de.openpizza.android.service.data.Product;
import de.openpizza.android.service.restapi.RESTServiceHandler;

public class Order implements RESTServiceHandler<OrderResponse> {

	private List<Product> productList = new ArrayList<Product>();
	private String nickname;
	private int shopid;
	private Activity context;
	private List<ModelChangedListener> changedListeners = new ArrayList<ModelChangedListener>();
	private String host;
	private String shortlink;
	private String id;

	public void addListener(ModelChangedListener l) {
		changedListeners.add(l);
	}

	public Order(int shopid, Activity context) {
		this.context = context;
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
		// Down Cast
		OrderService service = new OrderService((Activity) this.context);
		service.httpPost(orderRequest, this);

	}

	public void sendProductList() {
		if (!productList.isEmpty()) {
			OrderContentRequest orderContentRequest = new OrderContentRequest();
			orderContentRequest.setProducts(productList);
		}

		ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
		final OrderService service = new OrderService((Activity) context);
		final Order order = this;
		exec.scheduleAtFixedRate(new Runnable() {
			public void run() {
				((Activity) context).runOnUiThread(new Runnable() {

					@Override
					public void run() {
						service.httpGet("orders/"+ id + "/items", "", new PullRespondHandler()  );
					}
				});

			}
		}, 0, 60, TimeUnit.SECONDS); // execute every 60 seconds
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public void handleGetResponse(OrderResponse response) {
	}

	private void fireModelChanged() {
		for (ModelChangedListener l : this.changedListeners) {
			l.onModelChanged();
		}

	}

	@Override
	public void handlePostResponse(OrderResponse response) {

		this.host = response.getHost();
		this.shopid = response.getShop();
		this.shortlink = response.getShort_link();
		this.id = response.getId();
		fireModelChanged();
	}
	
	class PullRespondHandler implements RESTServiceHandler<OrderResponse> {

		@Override
		public void handleGetResponse(OrderResponse response) {
			fireModelChanged();
		}

		@Override
		public void handlePostResponse(OrderResponse response) {
			
		}
		
	}

}
