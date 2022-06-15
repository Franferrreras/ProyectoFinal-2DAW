package org.iesalixar.servidor.repository;

import java.util.List;

import org.iesalixar.servidor.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>{
	
	public Vehicle findVehicleByMatricula(String matricula);
	
	public Vehicle findVehicleById(Long id);
	
	@Query(value = "SELECT * FROM vehiculo WHERE marca = ?", nativeQuery = true)
	public List<Vehicle> getCarsByMarca(String marca);
	
	@Query(value = "SELECT * FROM vehiculo WHERE marca = ?1 and price BETWEEN ?2 and ?3 and year BETWEEN ?4 and ?5 and combustible like %?6%", nativeQuery = true)
	public List<Vehicle> filtro(String marca, String precio1, String precio2, String year1, String year2, String combustible);
}
