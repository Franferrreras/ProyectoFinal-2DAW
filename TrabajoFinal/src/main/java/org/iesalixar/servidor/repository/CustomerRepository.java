package org.iesalixar.servidor.repository;

import org.iesalixar.servidor.models.Customer;
import org.iesalixar.servidor.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Customer findByDni(String dni);
	public Customer findByUserName(String userName);
}
