package de.openpizza.android.service;

import java.lang.reflect.Type;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.openpizza.android.service.data.OrderContentRequest;
import de.openpizza.android.service.data.OrderContentResponse;
import de.openpizza.android.service.restapi.RESTService;
import de.openpizza.android.service.restapi.RESTServiceCall;
import de.openpizza.android.service.restapi.RESTServiceHandler;

public class OrderContentService extends
		RESTService<List<OrderContentResponse>> implements
		RESTServiceCall<OrderContentRequest, List<OrderContentResponse>> {
	Gson gson;

	public OrderContentService(Activity activity) {
		super(activity);
		gson = new Gson();
	}

	@Override
	public void httpGet(String url, String params,
			RESTServiceHandler<List<OrderContentResponse>> handler) {
		new GetTask(url, params, handler).execute();
	}

	private class GetTask extends AsyncTask<String, Void, String> {
		private String url;
		private String httpParams;

		public GetTask(String url, String params,
				RESTServiceHandler<List<OrderContentResponse>> handler) {
			this.url = url;
			this.httpParams = params;
			Log.d("OrderContentService", url);
			Log.d("OrderContentService", params);

			serviceHandler = handler;
		}

		@Override
		protected void onPreExecute() {
//			dialog.setMessage("Loading...");
			// dialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			return getData(url + "?" + httpParams);
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void onPostExecute(String result) {
			// dialog.dismiss();
			Log.d("OrderContentService", result);
			Type listType = new TypeToken<List<OrderContentResponse>>() {
			}.getType();
			serviceHandler.handleGetResponse((List<OrderContentResponse>) gson
					.fromJson(result, listType));

		}
	}

	private String id = "0";
	private String nickname = "none";

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void httpPost(OrderContentRequest data,
			RESTServiceHandler<List<OrderContentResponse>> handler) {
		String json = gson.toJson(data);
		new PostTask(handler, id, nickname).execute(json);
	}

	private class PostTask extends AsyncTask<String, Void, String> {
		private String id;
		private String nickname;

		public PostTask(RESTServiceHandler<List<OrderContentResponse>> handler,
				String id, String nickname) {
			serviceHandler = handler;
			this.id = id;
			this.nickname = nickname;
		}

		@Override
		protected void onPreExecute() {
//			dialog.setMessage("Loading...");
//			dialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			Log.d("OrderContentService.nickname", nickname);
			Log.d("OrderContentService.parms", params[0]);
			Log.d("OrderContentService.id", id);
			return postData("orders/" + id + "/items/" + nickname, params[0]);
		}

		@Override
		protected void onPostExecute(String result) {
//			dialog.dismiss();
			// serviceHandler.handlePostResponse((List<OrderContentResponse>)
			// gson.fromJson(result, listType));
		}

	}

	@Override
	public void httpPut(OrderContentRequest data,
			RESTServiceHandler<List<OrderContentResponse>> handler) {
		// TODO Auto-generated method stub

	}

}
