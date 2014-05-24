package de.openpizza.android.service.data;
/**
 */
public class OrderRequest {
	private int estimated_participants;
	private int shop;
	private DeliveryAddress address;
	
	public OrderRequest() {
		//empty constructor for gson
	}

	public OrderRequest(int estimated_participants, int shop,
			DeliveryAddress address) {
		super();
		this.estimated_participants = estimated_participants;
		this.shop = shop;
		this.address = address;
	}

	public int getEstimated_participants() {
		return estimated_participants;
	}

	public void setEstimated_participants(int estimated_participants) {
		this.estimated_participants = estimated_participants;
	}

	public int getShop() {
		return shop;
	}

	public void setShop(int shop) {
		this.shop = shop;
	}

	public DeliveryAddress getAddress() {
		return address;
	}

	public void setAddress(DeliveryAddress address) {
		this.address = address;
	}

}
