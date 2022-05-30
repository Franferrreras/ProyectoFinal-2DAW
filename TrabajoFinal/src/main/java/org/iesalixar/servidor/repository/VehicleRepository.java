package org.iesalixar.servidor.repository;

import org.iesalixar.servidor.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>{
	
	public Vehicle findByMatricula(String matricula);
}
