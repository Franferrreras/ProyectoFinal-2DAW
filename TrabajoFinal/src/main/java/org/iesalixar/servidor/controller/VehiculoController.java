package org.iesalixar.servidor.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.iesalixar.servidor.models.ImgVehicle;
import org.iesalixar.servidor.models.Vehicle;
import org.iesalixar.servidor.services.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/addVehiculo")
public class VehiculoController {

	@Autowired
	VehicleServiceImpl vehiculoService;
	
	
	
	@GetMapping
	public String addVehiculo(Model model) {
		
		Vehicle vehiculo = new Vehicle();
		
		ArrayList<String> marcas = new ArrayList<>();
		ArrayList<String> combustible = new ArrayList<>();
		
		marcas.add("Mazda");
		marcas.add("Audi");
		marcas.add("Mercedes");
		marcas.add("Peugeot");
		
		combustible.add("Gasolina");
		combustible.add("Diesel");
		combustible.add("GLP");
		combustible.add("Electrico");
		combustible.add("Hibrido");
		
		model.addAttribute("vehiculo", vehiculo);
		model.addAttribute("marcas", marcas);
		model.addAttribute("combustibles", combustible);
		
		return "addVehiculo";
	}
	
	@PostMapping
	public String addVehiculo(@ModelAttribute Vehicle vehiculo ,@RequestParam("file") MultipartFile imagen) {
		
		
		Vehicle vehiculoDB = new Vehicle();
		
		vehiculoDB.setCombustible(vehiculo.getCombustible());
		vehiculoDB.setKilometros(vehiculo.getKilometros());
		vehiculoDB.setMarca(vehiculo.getMarca());
		vehiculoDB.setMatricula(vehiculo.getMatricula());
		vehiculoDB.setModelo(vehiculo.getModelo());
		vehiculoDB.setPrice(vehiculo.getPrice());
		vehiculoDB.setStatus("venta");
		vehiculoDB.setYear(vehiculo.getYear());
		vehiculoDB.setVersion(vehiculo.getVersion());
		vehiculoDB.setStore(null);
		
		if (!imagen.isEmpty()) {
			Path directorioImg = Paths.get("src//main//resources//static/img");
			String rutaAbsoluta = directorioImg.toFile().getAbsolutePath();
			ImgVehicle imgV = new ImgVehicle();
			
			try {
				
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
				Files.write(rutaCompleta,bytesImg);
				
				imgV.setImagen(imagen.getOriginalFilename());
				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			vehiculoDB.addImg(imgV);
		}
		
		if (vehiculoService.insertVehicle(vehiculoDB) == null) {
			return "redirect:/addVehiculo?error=Ya existe el vehicul";
		}
		
		return "redirect:/addVehiculo";
	}
}
