package org.iesalixar.servidor.services;

import java.util.List;

import org.iesalixar.servidor.models.Store;
import org.iesalixar.servidor.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	StoreRepository storeRepository;
	
	@Override
	public List<Store> getAllStores() {
		// TODO Auto-generated method stub
		return storeRepository.findAll();
	}

	@Override
	public Store insertStore(Store store) {
		// TODO Auto-generated method stub
		if (store == null) {
			return null;
		}
		return storeRepository.save(store);
	}

	@Override
	public Store updateStore(Store store) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Store getStoreById(Long id) {
		// TODO Auto-generated method stub
		return storeRepository.findStoreById(id);
	}

	
}
