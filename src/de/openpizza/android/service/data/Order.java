package de.openpizza.android.service.data;
/**
 * {
    "id": "2d931510-d99f-494a-8c67-87feb05e1594",
    estimated_participants: 6,
    service: 1,
    delivery_address: {
        "name": "Max Pizzahunger",
        "address": "Karlstrasse 123",
        "postcode": "67676",
        "city": "Karlsruhe"
    }
}
 *
 */
public class Order {
	private String id;
	private int estimated_participants;
	private int service;
	private DeliveryAddress address;
	
	public Order() {
		//empty constructor for gson
	}

	public Order(String id, int estimated_participants, int service,
			DeliveryAddress address) {
		super();
		this.id = id;
		this.estimated_participants = estimated_participants;
		this.service = service;
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getEstimated_participants() {
		return estimated_participants;
	}

	public void setEstimated_participants(int estimated_participants) {
		this.estimated_participants = estimated_participants;
	}

	public int getService() {
		return service;
	}

	public void setService(int service) {
		this.service = service;
	}

	public DeliveryAddress getAddress() {
		return address;
	}

	public void setAddress(DeliveryAddress address) {
		this.address = address;
	}
}
