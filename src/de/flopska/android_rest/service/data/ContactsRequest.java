package de.flopska.android_rest.service.data;

import java.util.List;

public class ContactsRequest {
	private String from;
	private String vrkey;
	private List<String> contacts;
	
	public ContactsRequest(String from, String vrkey, List<String> contacts) {
		this.from = from;
		this.vrkey = vrkey;
		this.contacts = contacts;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getVrkey() {
		return vrkey;
	}
	public void setVrkey(String vrkey) {
		this.vrkey = vrkey;
	}
	public List<String> getContacts() {
		return contacts;
	}
	public void setContacts(List<String> contacts) {
		this.contacts = contacts;
	}
}
