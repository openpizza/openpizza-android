package de.flopska.android_rest.service;

import android.app.Activity;
import android.os.AsyncTask;

import com.google.gson.Gson;

import de.flopska.android_rest.service.data.ContactsRequest;
import de.flopska.android_rest.service.data.ContactsResponse;
import de.flopska.android_rest.service.restapi.RESTService;
import de.flopska.android_rest.service.restapi.RESTServiceCall;
import de.flopska.android_rest.service.restapi.RESTServiceHandler;

public class ContactsService extends RESTService<ContactsResponse> implements RESTServiceCall<ContactsRequest, ContactsResponse> {

	Gson gson;
	
	public ContactsService(Activity activity) {
		super(activity);
		gson = new Gson();
	}

	@Override
	public void httpGet() {
		// TODO Auto-generated method stub
	}

	@Override
	public void httpPost(ContactsRequest data,
			RESTServiceHandler<ContactsResponse> handler) {
		String json = gson.toJson(data);
		new PostTask(handler).execute(json);
	}
	
	private class PostTask extends AsyncTask<String, Void, String> {
		public PostTask(RESTServiceHandler<ContactsResponse> handler) {
			serviceHandler = handler;
		}

		@Override
		protected void onPreExecute() {
			dialog.setMessage("Loading...");
	        dialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			return postData("contacts", params[0]);
		}
		
		@Override
		protected void onPostExecute(String result) {
			dialog.dismiss();
			serviceHandler.handlePostResponse(gson.fromJson(result, ContactsResponse.class));
		}
	}

}
