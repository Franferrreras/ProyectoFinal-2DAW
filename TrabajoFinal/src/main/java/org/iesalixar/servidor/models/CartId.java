package org.iesalixar.servidor.models;

import java.io.Serializable;

public class CartId implements Serializable {

	private Long customer;
	private Long vehicle;

	public CartId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getCustomer() {
		return customer;
	}

	public void setCustomer(Long customer) {
		this.customer = customer;
	}

	public Long getVehicle() {
		return vehicle;
	}

	public void setVehicle(Long vehicle) {
		this.vehicle = vehicle;
	}

}
