package de.openpizza.android.dirty;

import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Context;
import de.openpizza.android.service.OrderContentService;
import de.openpizza.android.service.data.OrderContentRequest;
import de.openpizza.android.service.data.OrderContentResponse;
import de.openpizza.android.service.restapi.RESTServiceHandler;

public class SendOrder implements
		RESTServiceHandler<List<OrderContentResponse>> {
	private Context context;
	private OrderBean orderBean;
	private Activity activity;

	public SendOrder(OrderBean bean, Context context, Activity activity) {
		this.orderBean = bean;
		this.context = context;
		this.activity = activity;
	}

	public void sendProductList() {
		OrderContentService contentService = new OrderContentService(
				(Activity) context);
		contentService.setNickname(orderBean.getNickname());
		contentService.setId(orderBean.getId());

		if (!orderBean.productListIsEmpty()) {
			OrderContentRequest orderContentRequest = new OrderContentRequest();
			orderContentRequest.setProducts(orderBean.getProductList());
			contentService.httpPost(orderContentRequest, this);

		}
	}

	public void startPulling(Activity activity) {
		this.activity = activity;
		ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
		final OrderContentService service = new OrderContentService(activity);
		service.setId(orderBean.getId());
		final SendOrder sendOrder = this;
		exec.scheduleAtFixedRate(new Runnable() {
			public void run() {
				((Activity) context).runOnUiThread(new Runnable() {

					@Override
					public void run() {
						service.httpGet("orders/" + orderBean.getId()
								+ "/items", "", sendOrder);
					}
				});

			}
		}, 0, 5, TimeUnit.SECONDS); // execute every 60 seconds
	}

	

	@Override
	public void handleGetResponse(List<OrderContentResponse> response) {
		orderBean.setProductFormOthers(response);
	}

	@Override
	public void handlePostResponse(List<OrderContentResponse> response) {
		// TODO: Ist im service aus kommentiert
		// orderBean.setProductFormOthers(response);
	}

	@Override
	public void handlePutResponse(List<OrderContentResponse> Response) {
		// TODO Auto-generated method stub

	}
}
