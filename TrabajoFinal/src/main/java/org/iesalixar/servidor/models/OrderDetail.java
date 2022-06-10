package org.iesalixar.servidor.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "order_detail")
@IdClass(OrderDetailId.class)
public class OrderDetail implements Serializable {

	@Id
	@ManyToOne
	@JoinColumn(name = "id_customer", insertable = false, updatable = false)
	@JsonIgnore
	private Customer customer;

	@Id
	@ManyToOne
	@JoinColumn(name = "id_vehicle", insertable = false, updatable = false)
	@JsonIgnore
	private Vehicle vehicle;

	@ManyToOne
	@JoinColumn(name = "id_comercial", insertable = false, updatable = false)
	private Comercial comercial;

	private Date reservaDate;

	private Date requiredDate;

	private Date shippedDate;

	private double priceBuy;

	public OrderDetail(Customer customer, Vehicle vehicle, Comercial comercial, Date reservaDate) {
		super();
		this.customer = customer;
		this.vehicle = vehicle;
		this.comercial = comercial;
		this.reservaDate = reservaDate;
	}

	public OrderDetail(Customer customer, Vehicle vehicle, Comercial comercial) {
		super();
		this.customer = customer;
		this.vehicle = vehicle;
		this.comercial = comercial;
	}

	public OrderDetail(Customer customer, Vehicle vehicle, Date reservaDate) {
		super();
		this.customer = customer;
		this.vehicle = vehicle;
		this.reservaDate = reservaDate;
	}

	public OrderDetail() {
		super();
		// TODO Auto-generated constructor stub
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

	public Comercial getComercial() {
		return comercial;
	}

	public void setComercial(Comercial comercial) {
		this.comercial = comercial;
	}

	public Date getReservaDate() {
		return reservaDate;
	}

	public void setReservaDate(Date reservaDate) {
		this.reservaDate = reservaDate;
	}

	public Date getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}

	public Date getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public double getPriceBuy() {
		return priceBuy;
	}

	public void setPriceBuy(double priceBuy) {
		this.priceBuy = priceBuy;
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
		OrderDetail other = (OrderDetail) obj;
		return Objects.equals(customer, other.customer) && Objects.equals(vehicle, other.vehicle);
	}

	@Override
	public String toString() {
		return "OrderDetail [comercial=" + comercial + ", reservaDate=" + reservaDate + ", requiredDate=" + requiredDate
				+ ", shippedDate=" + shippedDate + ", priceBuy=" + priceBuy + "]";
	}

}
