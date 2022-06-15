package org.iesalixar.servidor.repository;

import org.iesalixar.servidor.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>{

	public Store findStoreById(Long id);
}
