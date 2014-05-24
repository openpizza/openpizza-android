package de.openpizza.android.service;

import android.app.Activity;
import android.os.AsyncTask;

import com.google.gson.Gson;

import de.openpizza.android.service.data.OrderContentRequest;
import de.openpizza.android.service.data.OrderContentResponse;
import de.openpizza.android.service.restapi.RESTService;
import de.openpizza.android.service.restapi.RESTServiceCall;
import de.openpizza.android.service.restapi.RESTServiceHandler;

public class OrderContentService extends RESTService<OrderContentResponse> implements
		RESTServiceCall<OrderContentRequest, OrderContentResponse> {
	Gson gson;

	public OrderContentService(Activity activity) {
		super(activity);
		gson = new Gson();
	}

	@Override
	public void httpGet(String url, String params,
			RESTServiceHandler<OrderContentResponse> handler) {
		new GetTask(url, params, handler).execute();
	}

	private class GetTask extends AsyncTask<String, Void, String> {
		private String url;
		private String httpParams;

		public GetTask(String url, String params,
				RESTServiceHandler<OrderContentResponse> handler) {
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
			serviceHandler.handleGetResponse(gson.fromJson(result,
					OrderContentResponse.class));
		}
	}

	@Override
	public void httpPost(OrderContentRequest data,
			RESTServiceHandler<OrderContentResponse> handler) {
		String json = gson.toJson(data);
		new PostTask(handler, 1, "florian").execute(json);
	}

	private class PostTask extends AsyncTask<String, Void, String> {
		private int id;
		private String nickname;
		
		public PostTask(RESTServiceHandler<OrderContentResponse> handler, int id, String nickname) {
			serviceHandler = handler;
			this.id = id;
			this.nickname = nickname;
		}

		@Override
		protected void onPreExecute() {
			dialog.setMessage("Loading...");
			dialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			return postData("orders/" + id + "/items/" + nickname, params[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			dialog.dismiss();
			serviceHandler.handlePostResponse(gson.fromJson(result,
					OrderContentResponse.class));
		}

	}

}
