package org.iesalixar.servidor.models;

import javax.annotation.ManagedBean;

import org.iesalixar.servidor.services.StoreService;
import org.iesalixar.servidor.services.StoreServiceImpl;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
 
		StoreService storeService = new StoreServiceImpl();
		
		Store store = new Store();
		
		store.setAddressLine("dsagagdd");
		store.setCountry("España");
		store.setPhone("12344566");
		store.setName("Gulp");
		store.setPostalCode("41800");
		store.setState("Andalusia");
		
		storeService.insertStore(store);
	}


}
