package org.iesalixar.servidor.services;

import java.util.List;

import org.iesalixar.servidor.models.Vehicle;

public interface VehicleService {

	public List<Vehicle> getAllVehicles();
	public Vehicle insertVehicle(Vehicle vehicle);
	public Vehicle updateVehicle(Vehicle vehicle);
	public Vehicle deleteVehicle(Vehicle vehicle);
	public Vehicle getVehicleById(Long id);
	public Vehicle getVehicleByMatrucula(String matricula);
	public List<Vehicle> getFiltredVehicles(String marca, String precio1, String precio2, String year1, String year2, String combustible);
}
