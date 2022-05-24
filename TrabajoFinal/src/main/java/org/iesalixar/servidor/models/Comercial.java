package org.iesalixar.servidor.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
@Table(name = "comercial")
public class Comercial extends Usuario{

	@Column(nullable = false)
	private String jobTitle;

	/*
	@OneToMany(mappedBy = "comercial", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Customer> customers = new HashSet<>();*/
	
	@OneToMany(mappedBy = "comercial", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<OrderDetail> orderDetails = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "id_office")
	private Office office;
	
	public Comercial() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
}
