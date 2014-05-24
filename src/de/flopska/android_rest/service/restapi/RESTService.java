package de.flopska.android_rest.service.restapi;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
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
 * @param <T> class of callback handler
 */
public abstract class RESTService<T> {
	
	protected RESTServiceHandler<T> serviceHandler;
	protected Context context;
	protected ProgressDialog dialog;
	
	public RESTService(Activity activity) {
		this.context = activity;
		this.dialog = new ProgressDialog(activity);
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
	    HttpPost httpPost = new HttpPost("http://192.168.1.15:8080/whatscash/" + identifier);

	    try {
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
	    	if( httpClient != null) {
	    		httpClient.getConnectionManager().shutdown();
	    	}
	    }
	    return null;
	} 
}
