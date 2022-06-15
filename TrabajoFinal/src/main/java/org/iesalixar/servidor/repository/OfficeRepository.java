package org.iesalixar.servidor.repository;

import org.iesalixar.servidor.models.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long>{

	public Office findOfficeById(Long id);
}
