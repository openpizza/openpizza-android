package de.flopska.android_rest.service.restapi;

/**
 * HTTP Methoden die an den Service geschickt werden können
 * 
 * @author flops
 *
 * @param <T> Request Class
 * @param <S> Response Class
 */
public interface RESTServiceCall<T, S> {
	
	public void httpGet();
	
	public void httpPost(T data, RESTServiceHandler<S> handler);

}
