package de.openpizza.android.service;

import android.app.Activity;
import android.os.AsyncTask;

import com.google.gson.Gson;

import de.openpizza.android.service.data.Shop;
import de.openpizza.android.service.restapi.RESTService;
import de.openpizza.android.service.restapi.RESTServiceCall;
import de.openpizza.android.service.restapi.RESTServiceHandler;

public class ShopIdService extends RESTService<Shop> implements
		RESTServiceCall<Void, Shop> {
	Gson gson;

	public ShopIdService(Activity activity) {
		super(activity);
		gson = new Gson();
	}

	@Override
	public void httpGet(String url, String params,
			RESTServiceHandler<Shop> handler) {
		new GetTask(url, params, handler).execute();
	}

	private class GetTask extends AsyncTask<String, Void, String> {
		private String url;
		private String httpParams;

		public GetTask(String url, String params,
				RESTServiceHandler<Shop> handler) {
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
			serviceHandler.handleGetResponse(gson.fromJson(result, Shop.class));
		}
	}

	@Override
	public void httpPost(Void data, RESTServiceHandler<Shop> handler) {
		// TODO Auto-generated method stub
	}

}
