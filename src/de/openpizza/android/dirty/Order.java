package de.openpizza.android.dirty;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import de.openpizza.android.service.OrderContentService;
import de.openpizza.android.service.OrderService;
import de.openpizza.android.service.data.OrderContentRequest;
import de.openpizza.android.service.data.OrderContentResponse;
import de.openpizza.android.service.data.OrderRequest;
import de.openpizza.android.service.data.OrderResponse;
import de.openpizza.android.service.data.Product;
import de.openpizza.android.service.restapi.RESTServiceHandler;

public class Order implements RESTServiceHandler<OrderResponse> {

	private List<Product> productList = new ArrayList<Product>();
	private String nickname;
	private int shopid;
	private Activity context;
	private List<OrderContentResponse> productFormOthers = new ArrayList<OrderContentResponse>();
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

	public Order(Activity context2) {
		this.context = context2;
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
		OrderContentService contentService = new OrderContentService(context);
		contentService.setNickname(nickname);

		if (!productList.isEmpty()) {
			OrderContentRequest orderContentRequest = new OrderContentRequest();
			orderContentRequest.setProducts(productList);
			contentService.httpPost(orderContentRequest,
					new OrderContentRespondHandler());

		}

		ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
		final OrderContentService service = new OrderContentService(
				(Activity) context);
		final Order order = this;
		exec.scheduleAtFixedRate(new Runnable() {
			public void run() {
				((Activity) context).runOnUiThread(new Runnable() {

					@Override
					public void run() {
						service.httpGet("orders/" + id + "/items", "",
								new OrderContentRespondHandler());
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
		this.host = response.getHost();
		this.shopid = response.getShop();
		this.shortlink = response.getShort_link();
		this.id = response.getId();
		fireModelChanged();
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

	public List<OrderContentResponse> getProductFormOthers() {
		return productFormOthers;
	}

	public void setProductFormOthers(
			List<OrderContentResponse> productFormOthers) {
		this.productFormOthers = productFormOthers;
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

		@Override
		public void handlePutResponse(OrderResponse Response) {
			// TODO Auto-generated method stub
			
		}

	}

	class OrderContentRespondHandler implements
			RESTServiceHandler<List<OrderContentResponse>> {

		@Override
		public void handleGetResponse(List<OrderContentResponse> response) {
			setProductFormOthers(response);
			fireModelChanged();
		}

		@Override
		public void handlePostResponse(List<OrderContentResponse> response) {

		}

		@Override
		public void handlePutResponse(List<OrderContentResponse> Response) {
			// TODO Auto-generated method stub
			
		}

	}

	public String getLink() {
		return this.shortlink;
	}

	public void get(String id2) {
		OrderService os = new OrderService(context);
		os.httpGet("orders/"+id2, "", this);

		
	}

	@Override
	public void handlePutResponse(OrderResponse Response) {
		// TODO Auto-generated method stub
		
	}
}
