package de.openpizza.android.service.restapi;

/**
 * HTTP Methoden die an den Service geschickt werden können
 * 
 * @author flops
 * 
 * @param <T>
 *            Request Class
 * @param <S>
 *            Response Class
 */
public interface RESTServiceCall<T, S> {

	public void httpGet(String url, String params, RESTServiceHandler<S> handler);

	public void httpPost(T data, RESTServiceHandler<S> handler);

	public void httpPut(T data, RESTServiceHandler<S> handler);

}
