package de.openpizza.android.service.data;

public class OrderResponse {
	private String id;
	private String short_link;
	private String host;
	private int estimated_participants;
	private int shop;
	private DeliveryAddress delivery_address;

	public OrderResponse() {
		// empty constructor for gson
	}

	public OrderResponse(String id, String short_link,
			int estimated_participants, String host, int shop,
			DeliveryAddress delivery_address) {
		super();
		this.id = id;
		this.short_link = short_link;
		this.estimated_participants = estimated_participants;
		this.host = host;
		this.shop = shop;
		this.delivery_address = delivery_address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShort_link() {
		return short_link;
	}

	public void setShort_link(String short_link) {
		this.short_link = short_link;
	}

	public int getEstimated_participants() {
		return estimated_participants;
	}

	public void setEstimated_participants(int estimated_participants) {
		this.estimated_participants = estimated_participants;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getShop() {
		return shop;
	}

	public void setShop(int shop) {
		this.shop = shop;
	}

	public DeliveryAddress getDelivery_address() {
		return delivery_address;
	}

	public void setDelivery_address(DeliveryAddress delivery_address) {
		this.delivery_address = delivery_address;
	}

}
