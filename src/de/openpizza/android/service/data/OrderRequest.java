package de.openpizza.android.service.data;

/**
 */
public class OrderRequest {
	private int estimated_participants;
	private int shop;
	private DeliveryAddress delivery_address;
	private String comment;
	private boolean complete;

	public OrderRequest() {
		// empty constructor for gson
	}

	public OrderRequest(int estimated_participants, int shop,
			DeliveryAddress address, String comment) {
		super();
		this.estimated_participants = estimated_participants;
		this.shop = shop;
		this.delivery_address = address;
		this.comment = comment;
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

	public boolean getComplete() {
		return complete;
	}

	public void setShop(int shop) {
		this.shop = shop;
	}

	public DeliveryAddress getAddress() {
		return delivery_address;
	}

	public void setAddress(DeliveryAddress address) {
		this.delivery_address = address;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setComplete(boolean b) {
		this.complete = b;

	}

}
