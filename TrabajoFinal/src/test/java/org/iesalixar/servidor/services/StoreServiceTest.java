package org.iesalixar.servidor.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.iesalixar.servidor.models.Store;
import org.iesalixar.servidor.repository.StoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class StoreServiceTest {

	@MockBean
	private StoreRepository storeRepository;
	
	@Autowired
	private StoreServiceImpl storeService;
	
	@Test
	void testInsertStore() throws Exception {
		
		Store store = new Store();
		
		store.setAddressLine("dsagagdd");
		store.setCountry("España");
		store.setPhone("12344566");
		store.setName("Gulp");
		store.setPostalCode("41800");
		store.setState("Andalusia");
		
		when(storeRepository.save(store)).thenReturn(store);
		
		assertEquals(store, storeService.insertStore(store));
	}
}
