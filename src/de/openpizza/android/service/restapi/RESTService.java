package de.openpizza.android.service.restapi;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

/**
 * Abstakte Basisklasse für die Implementierung von Service Schnittstellen
 * 
 * @author flops
 * 
 * @param <T>
 *            class of callback handler
 */
public abstract class RESTService<T> {

	private static final String API_URL = "http://openpizza.herokuapp.com/";
	protected RESTServiceHandler<T> serviceHandler;
	protected Context context;

	public RESTService(Context context) {
		this.context = context;
	}

	/**
	 * Sende get request an den Server
	 * 
	 * @return Antwort des Servers bei Erfolg, sonst null
	 */
	public String getData(String suburl) {
		HttpClient httpClient = null;
		try {
			// Create a new HttpClient and Post Header
			httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(API_URL + suburl);
			httpGet.addHeader("Accept", "application/json");
			HttpResponse response = httpClient.execute(httpGet);

			return EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		} finally {
			if (httpClient != null) {
				httpClient.getConnectionManager().shutdown();
			}
		}
		return null;
	}

	/**
	 * Sende post request an den Server
	 * 
	 * @param identifier
	 * @param data
	 * @return Antwort des Servers bei Erfolg, sonst null
	 */
	public String postData(String identifier, String data) {
		// Create a new HttpClient and Post Header
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(API_URL + identifier);

		try {
			httpPost.addHeader("Accept", "application/json");
			httpPost.addHeader("Content-Type", "application/json");

			StringEntity entity = new StringEntity(data);
			httpPost.setEntity(entity);

			// Execute HTTP Post Request
			HttpResponse response = httpClient.execute(httpPost);
			return EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		} finally {
			if (httpClient != null) {
				httpClient.getConnectionManager().shutdown();
			}
		}
		return null;
	}

	/**
	 * Sende put request an den Server
	 * 
	 * @param identifier
	 * @param data
	 * @return Antwort des Servers bei Erfolg, sonst null
	 */
	public String putData(String identifier, String data) {
		// Create a new HttpClient and Post Header
		HttpClient httpClient = new DefaultHttpClient();
		HttpPut httpPut = new HttpPut(API_URL + identifier);

		try {
			httpPut.addHeader("Accept", "application/json");
			httpPut.addHeader("Content-Type", "application/json");

			StringEntity entity = new StringEntity(data);
			httpPut.setEntity(entity);

			// Execute HTTP Post Request
			HttpResponse response = httpClient.execute(httpPut);
			return EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		} finally {
			if (httpClient != null) {
				httpClient.getConnectionManager().shutdown();
			}
		}
		return null;
	}
}
