package org.iesalixar.servidor.controller;

import java.util.ArrayList;
import java.util.HashSet;
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
	public String inicio(Model model, Authentication auth, HttpSession session) {		
		
		if (auth != null) {
			String username = auth.getName();

			if (session.getAttribute("usuario") == null) {
				Usuario usuario = usuarioService.findUsuarioByUserName(username);
				usuario.setPassword(null);
				session.setAttribute("usuario", usuario);
			}
		}
		
		
		List<Vehicle> list_vehicle = vehicleService.getAllVehicles();
		model.addAttribute("vehiculos", list_vehicle);
		System.out.println(list_vehicle.get(6).getArrayImagenes(0));
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
		cstomerDB.setAddreessLine("C/ Cardenal Spínola");
		cstomerDB.setApellidos(usuario.getApellidos());
		cstomerDB.setDni(usuario.getDni());
		cstomerDB.setEmail(usuario.getEmail());
		cstomerDB.setNombre(usuario.getNombre());
		cstomerDB.setPassword(new BCryptPasswordEncoder(15).encode(usuario.getPassword()));
		cstomerDB.setPhone("626740787");
		cstomerDB.setRole("user");
		cstomerDB.setUserName(usuario.getUsuario());

		cstomerDB = customerService.insertCustomer(cstomerDB);

		if (cstomerDB == null) {
			return "redirect:/register";
		}

		return "redirect:/";
	}

}
