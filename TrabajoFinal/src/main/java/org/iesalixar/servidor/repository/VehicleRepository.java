package org.iesalixar.servidor.repository;

import java.util.List;

import org.iesalixar.servidor.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>{
	
	public Vehicle findVehicleByMatricula(String matricula);
	
	@Query(value = "SELECT * FROM vehiculo WHERE marca = ?", nativeQuery = true)
	public List<Vehicle> getCarsByMarca(String marca);
	
	@Query(value = "SELECT * FROM vehiculo WHERE marca like ? OR modelo like ? OR year like ? OR combustible like ?", nativeQuery = true)
	public List<Vehicle> filtro(String marca, String modelo, int precio, int year);
}
