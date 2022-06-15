package org.iesalixar.servidor.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.iesalixar.servidor.dto.UsuarioDTO;
import org.iesalixar.servidor.models.Comercial;
import org.iesalixar.servidor.models.Customer;
import org.iesalixar.servidor.models.OrderDetail;
import org.iesalixar.servidor.models.Usuario;
import org.iesalixar.servidor.models.Vehicle;
import org.iesalixar.servidor.services.ComercialServiceImpl;
import org.iesalixar.servidor.services.CustomerServiceImpl;
import org.iesalixar.servidor.services.OrderDetailServiceImpl;
import org.iesalixar.servidor.services.UsuarioServiceImpl;
import org.iesalixar.servidor.services.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	@Autowired
	UsuarioServiceImpl usuarioService;
	
	@Autowired
	CustomerServiceImpl customerService;
	
	@Autowired
	VehicleServiceImpl vehicleService;

	@RequestMapping("/")
	public String inicio(Model model, Authentication auth, HttpSession session, @RequestParam(name = "marca", required = false) String marca,
			@RequestParam(name="precio1", required = false) String precio1, @RequestParam(name="precio2", required = false) String precio2,
			@RequestParam(name="year1", required = false) String year1, @RequestParam(name="year2", required = false) String year2,
			@RequestParam(name="combustible", required = false) String combustible) {		
		
		if (auth != null) {
			String username = auth.getName();

			if (session.getAttribute("usuario") == null) {
				Usuario usuario = usuarioService.findUsuarioByUserName(username);
				usuario.setPassword(null);
				session.setAttribute("usuario", usuario);
			}
		}
		
		if (marca != null && precio1 != null && year1 != null || combustible != null) {
			
			System.out.println(marca);
			System.out.println(combustible);
			List<Vehicle> list_filtred = vehicleService.getFiltredVehicles(marca, precio1, precio2, year1, year2, combustible);
			model.addAttribute("vehiculos", list_filtred);
			
			return "inicio";
		}
		
		
		List<Vehicle> list_vehicle = vehicleService.getAllVehicles();
		
		
		
		for (int i = 0; i < list_vehicle.size(); i++) {
			
			if (list_vehicle.get(i).getStatus().equals("reserved")) {
				
				Vehicle aux = new Vehicle();
				aux = list_vehicle.get(i);
				
				list_vehicle.remove(list_vehicle.get(i));
				
				
				list_vehicle.add(list_vehicle.size(), aux);

			}
			
		}
		
		for (int i = 0; i < list_vehicle.size(); i++) {
			if (list_vehicle.get(i).getStatus().equals("SOLD")) {
				Vehicle aux = new Vehicle();
				aux = list_vehicle.get(i);
				
				list_vehicle.remove(list_vehicle.get(i));
				
				
				list_vehicle.add(list_vehicle.size(), aux);
			}
		}
		
		
		model.addAttribute("vehiculos", list_vehicle);
		return "inicio";
	}
	

	@GetMapping("/register")
	public String registerGet(Model model) {

		UsuarioDTO userDTO = new UsuarioDTO();
		model.addAttribute("usuario", userDTO);

		return "register";
	}

	@PostMapping("/register")
	public String registerPost(@ModelAttribute UsuarioDTO usuario) {

		Customer cstomerDB = new Customer();
		cstomerDB.setActivo(true);
		cstomerDB.setAddreessLine(usuario.getAddressLine());
		cstomerDB.setApellidos(usuario.getApellidos());
		cstomerDB.setDni(usuario.getDni());
		cstomerDB.setEmail(usuario.getEmail());
		cstomerDB.setNombre(usuario.getNombre());
		cstomerDB.setPassword(new BCryptPasswordEncoder(15).encode(usuario.getPassword()));
		cstomerDB.setPhone("626740787");
		cstomerDB.setUserName(usuario.getUsuario());
		
		if (usuario.getRole() != null) {
			cstomerDB.setRole("ROLE_EMPLOYEE");
		} else {
			cstomerDB.setRole("user");
		}

		cstomerDB = customerService.insertCustomer(cstomerDB);

		if (cstomerDB == null) {
			return "redirect:/register";
		}

		return "redirect:/";
	}

}
