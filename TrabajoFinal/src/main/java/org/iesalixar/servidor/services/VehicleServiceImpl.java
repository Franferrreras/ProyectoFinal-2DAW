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
		
		if (vehicle != null) {
			return vehicleRepo.save(vehicle);
		}
		
		return null;
	}

	@Override
	public Vehicle updateVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return vehicleRepo.save(vehicle);
	}

	@Override
	public Vehicle deleteVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehicle getVehicleByMatrucula(String matricula) {
		// TODO Auto-generated method stub
		return vehicleRepo.findVehicleByMatricula(matricula);
	}

	@Override
	public List<Vehicle> getFiltredVehicles(String marca, String precio1, String precio2, String year1, String year2,
			String combustible) {
		// TODO Auto-generated method stub
		return vehicleRepo.filtro(marca, precio1, precio2, year1, year2, combustible);
	}

	@Override
	public Vehicle getVehicleById(Long id) {
		// TODO Auto-generated method stub
		return vehicleRepo.findVehicleById(id);
	}


}
