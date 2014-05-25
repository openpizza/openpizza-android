package de.openpizza.android.service;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import de.openpizza.android.service.data.OrderRequest;
import de.openpizza.android.service.data.OrderResponse;
import de.openpizza.android.service.restapi.RESTService;
import de.openpizza.android.service.restapi.RESTServiceCall;
import de.openpizza.android.service.restapi.RESTServiceHandler;

public class OrderService extends RESTService<OrderResponse> implements
		RESTServiceCall<OrderRequest, OrderResponse> {
	Gson gson;
	
	private String id = "0";
	public void setId(String id) {
		this.id = id;
	}

	public OrderService(Activity activity) {
		super(activity);
		gson = new Gson();
	}

	@Override
	public void httpGet(String url, String params,
			RESTServiceHandler<OrderResponse> handler) {
		new GetTask(url, params, handler).execute();
	}

	private class GetTask extends AsyncTask<String, Void, String> {
		private String url;
		private String httpParams;

		public GetTask(String url, String params,
				RESTServiceHandler<OrderResponse> handler) {
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
			Log.d("httpget", result);
			serviceHandler.handleGetResponse(gson.fromJson(result,
					OrderResponse.class));
		}
	}

	@Override
	public void httpPost(OrderRequest data,
			RESTServiceHandler<OrderResponse> handler) {
		String json = gson.toJson(data);
		new PostTask(handler).execute(json);
	}

	private class PostTask extends AsyncTask<String, Void, String> {

		public PostTask(RESTServiceHandler<OrderResponse> handler) {
			serviceHandler = handler;
		}

		@Override
		protected void onPreExecute() {
			dialog.setMessage("Loading...");
			dialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			return postData("orders/", params[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			dialog.dismiss();
			Log.d("httppost", result);
			serviceHandler.handlePostResponse(gson.fromJson(result,
					OrderResponse.class));
		}

	}

	@Override
	public void httpPut(OrderRequest data,
			RESTServiceHandler<OrderResponse> handler) {
		String json = gson.toJson(data);
		new PutTask(handler, id).execute(json);
	}
	private class PutTask extends AsyncTask<String, Void, String> {
		private String id;
		public PutTask(RESTServiceHandler<OrderResponse> handler, String id) {
			serviceHandler = handler;
			this.id = id;
		}

		@Override
		protected void onPreExecute() {
			dialog.setMessage("Loading...");
			dialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			return postData("orders/" + id, params[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			dialog.dismiss();
			Log.d("httpput", result);
			serviceHandler.handlePutResponse(gson.fromJson(result,
					OrderResponse.class));
		}

	}
}
