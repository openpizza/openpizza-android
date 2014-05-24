package de.openpizza.android.service;

import java.lang.reflect.Type;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.openpizza.android.service.data.Shop;
import de.openpizza.android.service.restapi.RESTService;
import de.openpizza.android.service.restapi.RESTServiceCall;
import de.openpizza.android.service.restapi.RESTServiceHandler;

public class ShopsIdService extends RESTService<List<Shop>> implements
		RESTServiceCall<Void, List<Shop>> {
	Gson gson;

	public ShopsIdService(Activity activity) {
		super(activity);
		gson = new Gson();
	}

	@Override
	public void httpGet(String url, String params, RESTServiceHandler<List<Shop>> handler) {
		new GetTask(url, params, handler).execute();
	}

	private class GetTask extends AsyncTask<String, Void, String> {
		private String url;
		private String httpParams;
		public GetTask(String url, String params, RESTServiceHandler<List<Shop>> handler) {
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

		@SuppressWarnings("unchecked")
		@Override
		protected void onPostExecute(String result) {
			dialog.dismiss();
			Type listType = new TypeToken<List<Shop>>() {}.getType();
			serviceHandler
					.handleGetResponse((List<Shop>) gson.fromJson(result, listType));
		}
	}

	@Override
	public void httpPost(Void data, RESTServiceHandler<List<Shop>> handler) {
		// TODO Auto-generated method stub
	}


}
