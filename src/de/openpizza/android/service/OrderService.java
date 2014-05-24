package de.openpizza.android.service;

import android.app.Activity;
import android.os.AsyncTask;

import com.google.gson.Gson;

import de.openpizza.android.service.data.Order;
import de.openpizza.android.service.restapi.RESTService;
import de.openpizza.android.service.restapi.RESTServiceCall;
import de.openpizza.android.service.restapi.RESTServiceHandler;

public class OrderService extends RESTService<Order> implements
		RESTServiceCall<Void, Order> {
	Gson gson;

	public OrderService(Activity activity) {
		super(activity);
		gson = new Gson();
	}

	@Override
	public void httpGet(String url, String params,
			RESTServiceHandler<Order> handler) {
		new GetTask(url, params, handler).execute();
	}

	private class GetTask extends AsyncTask<String, Void, String> {
		private String url;
		private String httpParams;

		public GetTask(String url, String params,
				RESTServiceHandler<Order> handler) {
			this.url = url;
			this.httpParams = params;
			serviceHandler = handler;
		}

		@Override
		protected void onPreExecute() {
			dialog.setMessage("Loading...");
			dialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			return getData(url + "?" + httpParams);
		}

		@Override
		protected void onPostExecute(String result) {
			dialog.dismiss();
			serviceHandler
					.handleGetResponse(gson.fromJson(result, Order.class));
		}
	}

	@Override
	public void httpPost(Void data, RESTServiceHandler<Order> handler) {
		new PostTask(handler).execute();
	}

	private class PostTask extends AsyncTask<String, Void, String> {

		public PostTask(RESTServiceHandler<Order> handler) {
			serviceHandler = handler;
		}

		@Override
		protected void onPreExecute() {
			dialog.setMessage("Loading...");
			dialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			return postData("orders", "");
		}

		@Override
		protected void onPostExecute(String result) {
			dialog.dismiss();
			serviceHandler.handlePostResponse(gson
					.fromJson(result, Order.class));
		}

	}

}
