package de.openpizza.android.service.restapi;

/**
 * Callback Interface um ankommende Daten zu verarbeiten
 * 
 * @author flops
 *
 * @param <T> Response Klasse
 */
public interface RESTServiceHandler<T> {
	
	/**
	 * Callback für eine abgeschlossene Get Anfrage
	 * 
	 * @param response erhaltene Daten
	 */
	public void handleGetResponse(T response);
	
	/**
	 * Callback für eine abgeschlossene Post Anfrage
	 * 
	 * @param response erhaltene Daten
	 */
	public void handlePostResponse(T response);
	
}
