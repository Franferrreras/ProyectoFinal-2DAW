package org.iesalixar.servidor.services;

import java.util.List;

import org.iesalixar.servidor.models.Vehicle;

public interface VehicleService {

	public List<Vehicle> getAllVehicles();
	public Vehicle insertVehicle(Vehicle vehicle);
	public Vehicle updateVehicle(Vehicle vehicle);
	public Vehicle deleteVehicle(Vehicle vehicle);
}
