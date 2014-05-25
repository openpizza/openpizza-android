package de.openpizza.android.service.data;

/**
 * delivery_address: { "name": "Max Pizzahunger", "address": "Karlstrasse 123",
 * "postcode": "67676", "city": "Karlsruhe" }
 * 
 * @author flops
 * 
 */
public class DeliveryAddress {
	private String name;
	private String address;
	private String postcode;
	private String city;

	public DeliveryAddress() {
		// empty constructor for gson
	}

	public DeliveryAddress(String name, String address, String postcode,
			String city) {
		super();
		this.setName(name);
		this.setAddress(address);
		this.setPostcode(postcode);
		this.setCity(city);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
