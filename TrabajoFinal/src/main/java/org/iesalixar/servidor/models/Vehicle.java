package org.iesalixar.servidor.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "vehiculo")
public class Vehicle implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 9, nullable = false)
	private String matricula;

	@Column(nullable = false)
	private String marca;

	@Column(nullable = false)
	private String modelo;

	@Column(nullable = false)
	private String version;

	@Column
	private String transmision;

	@Column
	private String potencia;

	@Column(nullable = false)
	private String combustible;

	@Column(nullable = false)
	private String year;

	@Column(nullable = false)
	private String status;

	@Column(nullable = false)
	private String kilometros;

	@Column(nullable = false)
	private int price;

	@ManyToOne
	@JoinColumn(name = "id_store")
	@JsonIgnore
	private Store store;

	@OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<OrderDetail> orderDetails = new HashSet<>();

	@OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<ImgVehicle> imagenes = new HashSet<>();

	@OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Cart> cart = new HashSet<>();

	public Vehicle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCombustible() {
		return combustible;
	}

	public void setCombustible(String combustible) {
		this.combustible = combustible;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getKilometros() {
		return kilometros;
	}

	public void setKilometros(String kilometros) {
		this.kilometros = kilometros;
	}

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Set<ImgVehicle> getImagenes() {
		return imagenes;
	}

	public void setImagenes(Set<ImgVehicle> imagenes) {
		this.imagenes = imagenes;
	}

	public Set<Cart> getCart() {
		return cart;
	}

	public void setCart(Set<Cart> cart) {
		this.cart = cart;
	}

	public String getTransmision() {
		return transmision;
	}

	public void setTransmision(String transmision) {
		this.transmision = transmision;
	}

	public String getPotencia() {
		return potencia;
	}

	public void setPotencia(String potencia) {
		this.potencia = potencia;
	}

	public String getArrayImagenes(int num) {

		ArrayList<ImgVehicle> list_imgs = null;
		if (this.imagenes.size() > 0) {

			list_imgs = new ArrayList<>();
			for (ImgVehicle i : imagenes) {
				list_imgs.add(i);
			}
			Collections.sort(list_imgs);
			return list_imgs.get(num).getImagen();
		}
		return "nada";
	}

	@JsonIgnore
	public ArrayList<ImgVehicle> getListImagenes() {

		ArrayList<ImgVehicle> list_imgs = new ArrayList<>();
		if (this.imagenes.size() > 0) {

			list_imgs = new ArrayList<>();
			for (ImgVehicle i : imagenes) {

				list_imgs.add(i);
			}
			Collections.sort(list_imgs);
			return list_imgs;
		}
		return null;
	}

	@JsonIgnore
	public int getFinancedPrice() {

		int financiacion = (int) (price * 0.91);

		return financiacion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, matricula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		return Objects.equals(id, other.id) && Objects.equals(matricula, other.matricula);
	}

	// Métodos HELPERs
	public void addImg(ImgVehicle imgv) {
		this.imagenes.add(imgv);
		imgv.setVehicle(this);
	}

	public void removeImg(ImgVehicle imgv) {
		this.imagenes.remove(imgv);
		imgv.setVehicle(null);
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo
				+ ", version=" + version + ", transmision=" + transmision + ", potencia=" + potencia + ", combustible="
				+ combustible + ", year=" + year + ", status=" + status + ", kilometros=" + kilometros + ", price="
				+ price + ", store=" + store + "]";
	}

}
