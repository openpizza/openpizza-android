package de.openpizza.android.dirty;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import de.openpizza.android.service.data.OrderContentResponse;
import de.openpizza.android.service.data.OrderResponse;
import de.openpizza.android.service.data.Product;

public class OrderBean {
	
	
	public OrderBean(Order order) {
		this.order = order;
	}
	
	
	private List<Product> productList = new ArrayList<Product>();
	private String nickname;
	private int shopid;
	private List<OrderContentResponse> productFormOthers = new ArrayList<OrderContentResponse>();
	private String host;
	private String shortlink;
	private String id;
	
	
	private Order order;

	public void setOrderResponse(OrderResponse response) {
		setOrderResponseWithoutNotify(response);
		order.fireModelChanged();
	}
	
	public void setOrderResponseWithoutNotify(OrderResponse response) {
		shopid = response.getShop();
		id = response.getId();
		Log.d("SetOrderRespons", response.getId());
		shortlink = response.getShort_link();
		host = response.getHost();
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getShopid() {
		return shopid;
	}

	public void setShopid(int shopid) {
		this.shopid = shopid;
	}

	public List<OrderContentResponse> getProductFormOthers() {
		return productFormOthers;
	}

	public void setProductFormOthers(
			List<OrderContentResponse> productFormOthers) {
		this.productFormOthers = productFormOthers;
		this.order.fireModelChanged();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getShortlink() {
		return shortlink;
	}

	public void setShortlink(String shortlink) {
		this.shortlink = shortlink;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean productListIsEmpty() {
		return productList.isEmpty();
	}

	public void addProduct(Product product, Integer quantity) {
		
		boolean found = false;
		for(Product p: this.productList) {
			if(p.getId() == product.getId()) {
				if(quantity == 0) {
					this.productList.remove(p);
				} else {
					p.setQuantity(quantity);
				}
				found = true;
			}
		}
		if(!found && quantity > 0) {
			Log.d("test", product.getCategory());
			Log.d("se" ,quantity + "");
			product.setQuantity(quantity);
			this.productList.add(product);
		}
		
	}
}
