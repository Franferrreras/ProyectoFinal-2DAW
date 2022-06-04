package org.iesalixar.servidor.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "favorite")
@IdClass(CartId.class)
public class Cart {

	@Id
	@ManyToOne
	@JoinColumn(name = "id_customer", insertable = false, updatable = false)
	private Customer customer;

	@Id
	@ManyToOne
	@JoinColumn(name = "id_vehicle", insertable = false, updatable = false)
	private Vehicle vehicle;

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Cart(Customer customer, Vehicle vehicle) {
		super();
		this.customer = customer;
		this.vehicle = vehicle;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(customer, vehicle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		return Objects.equals(customer, other.customer) && Objects.equals(vehicle, other.vehicle);
	}

	@Override
	public String toString() {
		return "Cart [customer=" + customer + ", vehicle=" + vehicle + "]";
	}
}
