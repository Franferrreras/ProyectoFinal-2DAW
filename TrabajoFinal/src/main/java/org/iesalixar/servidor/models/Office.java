package org.iesalixar.servidor.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "office")
public class Office {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String addressLine;

	@Column
	private String phone;

	@Column
	private String postalCode;

	@Column(unique = true)
	private String name;

	@OneToMany(mappedBy = "office", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Comercial> comerciales = new HashSet<>();

	public Office() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Comercial> getComerciales() {
		return comerciales;
	}

	public void setComerciales(Set<Comercial> comerciales) {
		this.comerciales = comerciales;
	}

	@Override
	public int hashCode() {
		return Objects.hash(addressLine, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Office other = (Office) obj;
		return Objects.equals(addressLine, other.addressLine) && Objects.equals(name, other.name);
	}
}
