package org.iesalixar.servidor.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.iesalixar.servidor.models.Customer;
import org.iesalixar.servidor.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class CustomerServiceTest {

	@MockBean
	private CustomerRepository customerRepo;
	
	@Autowired
	private CustomerServiceImpl customerService;
	
	@Test
	void testInsertCustomer() throws Exception {
		
		Customer customer = new Customer();
		
		customer.setActivo(true);
		customer.setAddreessLine("asdfasdf");
		customer.setApellidos("Ferreras Fariñas");
		customer.setDni("21153854H");
		customer.setEmail("ffaf@gmail.com");
		customer.setNombre("Fran");
		customer.setPassword("123454");
		customer.setPhone("626740787");
		customer.setRole("user");
		customer.setUserName("Fran");
		
		when(customerRepo.save(customer)).thenReturn(customer);
		
		assertEquals(customer, customerService.insertCustomer(customer));
		
	}
}
