package org.iesalixar.servidor.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
@Table(name = "customer")
public class Customer extends Usuario implements Serializable{

	/*
	@ManyToOne
	@JoinColumn(name = "comercial_id")
	private Comercial comercial;*/

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<OrderDetail> orderDetails = new HashSet<>();

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}


	//Metodos helpers
	public void assingVehicle(Vehicle vehiculo, Date date, Comercial comercial ) {
		
		OrderDetail orderDetail = new OrderDetail(this, vehiculo, comercial, date);
		this.orderDetails.add(orderDetail);
		vehiculo.getOrderDetails().add(orderDetail);
		comercial.getOrderDetails().add(orderDetail);
	}
	
	public void removeVehicle(Vehicle vehiculo, Comercial comercial) {
		OrderDetail orderDetails = new OrderDetail(this, vehiculo, comercial);
		vehiculo.getOrderDetails().remove(orderDetails);
		comercial.getOrderDetails().remove(orderDetails);
		this.orderDetails.remove(orderDetails);
	}
}
