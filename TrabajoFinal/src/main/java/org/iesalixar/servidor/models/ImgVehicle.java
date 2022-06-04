package org.iesalixar.servidor.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "imagenes_vehiculo")
public class ImgVehicle implements Serializable, Comparable<ImgVehicle> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String imagen;

	@ManyToOne
	@JoinColumn(name = "id_vehiculo")
	private Vehicle vehicle;

	public ImgVehicle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public String toString() {
		return "id=" + id + ", imagen=" + imagen;
	}

	@Override
	public int compareTo(ImgVehicle o) {
		// TODO Auto-generated method stub
		
		if (o.getId() > id) {
			return -1;
		} else if (o.getId() > id) {
			return 0;
		} else {
			return 1;
		}
	}
}
