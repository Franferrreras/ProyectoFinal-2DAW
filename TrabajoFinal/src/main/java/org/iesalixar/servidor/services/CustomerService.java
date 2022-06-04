package org.iesalixar.servidor.services;

import java.util.List;
import java.util.Optional;

import org.iesalixar.servidor.models.Customer;

public interface CustomerService {

	public List<Customer> getAllCustomers();
	public Customer findCustomerById(Long id);
	public Customer insertCustomer(Customer customer);
	public Customer updateCustomer(Customer customer);
	public Customer getCustomerByDni(String dni);
	public Customer findCustomerByName(String name);
}
