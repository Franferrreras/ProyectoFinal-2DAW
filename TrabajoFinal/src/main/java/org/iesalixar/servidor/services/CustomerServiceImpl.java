package org.iesalixar.servidor.services;

import java.util.List;

import org.iesalixar.servidor.models.Customer;
import org.iesalixar.servidor.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepo;

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepo.findAll();
	}

	@Override
	public Customer findCustomerById(Long id) {
		// TODO Auto-generated method stub
		return customerRepo.findById(id).get();
	}
	
	@Override
	public Customer getCustomerByDni(String dni) {
		// TODO Auto-generated method stub
		return customerRepo.findByDni(dni);
	}

	@Override
	public Customer insertCustomer(Customer customer) {
		// TODO Auto-generated method stub
		if(customer != null) {
			Customer cstmer = customerRepo.save(customer);
			return cstmer;
		}
		
		return null;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
		if (customer == null || customer.getId() == null || customer.getDni() == null) {
			return null;
		}
		
		return customerRepo.save(customer);
	}

	@Override
	public Customer findCustomerByName(String name) {
		// TODO Auto-generated method stub
		return customerRepo.findByUserName(name);
	}
	
}
