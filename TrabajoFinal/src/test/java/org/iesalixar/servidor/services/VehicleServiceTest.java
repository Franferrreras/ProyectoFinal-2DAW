package org.iesalixar.servidor.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.iesalixar.servidor.models.Vehicle;
import org.iesalixar.servidor.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class VehicleServiceTest {

	@MockBean
	private VehicleRepository vehicleRepo;
	
	@Autowired
	private VehicleServiceImpl vehicleService;
	
	@Test
	void testGetVehiciloByMatricula() throws Exception {
		
		Vehicle vehiculo = new Vehicle();
		
		vehiculo.setId((long) 2);
		vehiculo.setCombustible("Diesel");
		vehiculo.setKilometros("0");
		vehiculo.setMarca("Mazda");
		vehiculo.setMatricula("3399 LKT");
		vehiculo.setModelo("C-X5");
		vehiculo.setStatus("sold");
		vehiculo.setVersion("L-Skaiyactiv");
		vehiculo.setYear("2021");
		vehiculo.setPrice(36000);
		
		
		when(vehicleRepo.findVehicleByMatricula("3399 LKT")).thenReturn(vehiculo);
		
		assertEquals(vehiculo, vehicleService.getVehicleByMatrucula("3399 LKT"));
	}
}
