package org.iesalixar.servidor.services;

import java.util.List;

import org.iesalixar.servidor.models.Vehicle;
import org.iesalixar.servidor.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	VehicleRepository vehicleRepo;
	
	@Override
	public List<Vehicle> getAllVehicles() {
		// TODO Auto-generated method stub
		return vehicleRepo.findAll();
	}

	@Override
	public Vehicle insertVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehicle updateVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehicle deleteVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return null;
	}

}
