package org.iesalixar.servidor.services;

import java.util.List;

import org.iesalixar.servidor.models.Store;

public interface StoreService {

	public List<Store> getAllStores();
	public Store insertStore(Store store);
	public Store updateStore(Store store);
	public Store getStoreById(Long id);
}
