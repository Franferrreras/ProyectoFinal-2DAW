package org.iesalixar.servidor.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.iesalixar.servidor.models.ImgVehicle;
import org.iesalixar.servidor.models.Vehicle;
import org.iesalixar.servidor.services.ImgVehicleServiceImpl;
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
@RequestMapping("/car")
public class VehiculoController {

	@Autowired
	VehicleServiceImpl vehiculoService;
	
	@Autowired
	ImgVehicleServiceImpl imgService;
	
	@GetMapping("/add")
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
	
	@PostMapping("/add")
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
			return "redirect:/car/add?error=Ya existe el vehiculo";
		}
		
		return "redirect:/car/add";
	}
	
	@GetMapping("/details")
	public String detailCar(@RequestParam(name="matricula", required=false) String matricula, Model model) {
		
		Vehicle car = vehiculoService.getVehicleByMatrucula(matricula);
		
		model.addAttribute("vehiculo", car);
		
		return "detailVehiculo";
	}
	
	@PostMapping("/details/addIMG")
	public String addIMG(@RequestParam("file") MultipartFile imagen, @RequestParam("matricula") String matricula) {
		
		Vehicle vehiculoDB = vehiculoService.getVehicleByMatrucula(matricula);
		
		if (!imagen.isEmpty() && imgService.findImgByImg(imagen.getOriginalFilename()) == null ) {
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
			vehiculoService.updateVehicle(vehiculoDB);
		}
		return "redirect:/car/details?matricula="+vehiculoDB.getMatricula();
		
	}
	
}
