package de.flopska.android_rest.service;

import android.app.Activity;
import android.os.AsyncTask;

import com.google.gson.Gson;

import de.flopska.android_rest.service.data.RegisterRequest;
import de.flopska.android_rest.service.data.RegisterResponse;
import de.flopska.android_rest.service.restapi.RESTService;
import de.flopska.android_rest.service.restapi.RESTServiceCall;
import de.flopska.android_rest.service.restapi.RESTServiceHandler;

/**
 * Schnittstelle für den JSON register service des whatscash-webservices
 * 
 * @author flops
 *
 */
public class RegisterService extends RESTService<RegisterResponse> implements RESTServiceCall<RegisterRequest, RegisterResponse> {

	Gson gson;
	
	public RegisterService(Activity activity) {
		super(activity);
		gson = new Gson();
	}

	@Override
	public void httpGet() {
		// service doesn't provide get
	}

	@Override
	public void httpPost(RegisterRequest data,
			RESTServiceHandler<RegisterResponse> handler) {
		String json = gson.toJson(data);
		new PostTask(handler).execute(json);
	}
	
	private class PostTask extends AsyncTask<String, Void, String> {
		public PostTask(RESTServiceHandler<RegisterResponse> handler) {
			serviceHandler = handler;
		}

		@Override
		protected void onPreExecute() {
			dialog.setMessage("Loading...");
	        dialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			return postData("register", params[0]);
		}
		
		@Override
		protected void onPostExecute(String result) {
			dialog.dismiss();
			serviceHandler.handlePostResponse(gson.fromJson(result, RegisterResponse.class));
		}
	}
	
}
