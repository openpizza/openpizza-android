package de.flopska.android_rest.service.data;

public class RegisterRequest {
	private String from;
	private String vrkey;
	private String password;

	public RegisterRequest(String from, String vrkey, String password) {
		super();
		this.from = from;
		this.vrkey = vrkey;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
