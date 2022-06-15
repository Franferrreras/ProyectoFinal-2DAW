package org.iesalixar.servidor.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.iesalixar.servidor.models.Cart;
import org.iesalixar.servidor.models.Customer;
import org.iesalixar.servidor.models.Usuario;
import org.iesalixar.servidor.models.Vehicle;
import org.iesalixar.servidor.services.CartServiceImpl;
import org.iesalixar.servidor.services.CustomerServiceImpl;
import org.iesalixar.servidor.services.UsuarioServiceImpl;
import org.iesalixar.servidor.services.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/profile/favorite")
public class CartController {

	@Autowired
	UsuarioServiceImpl usuarioService;

	@Autowired
	CartServiceImpl cartService;

	@Autowired
	CustomerServiceImpl customerService;

	@Autowired
	VehicleServiceImpl vehiculoService;

	@GetMapping
	public String cartGet(Model model, Authentication auth, HttpSession session) {

		if (auth != null) {
			Usuario user =  (Usuario) session.getAttribute("usuario");
			
			if (user != null) {
				Customer customer = customerService.findCustomerByName(user.getUserName());
				model.addAttribute("carrito", customer.getCart());
			} else {
				return "redirect:/";
			}
			
		} 

//		for (Cart c : customer.getCart()) {
//			
//			System.out.println(c);
//		}
		return "carrito";
	}

	@GetMapping("/addFavorite")
	public String addcartGet(@RequestParam(name = "coche", required = false) String coche, Model model,
			Authentication auth, HttpSession session) {

		String username = auth.getName();

		Customer customer = null;

		if (session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.findUsuarioByUserName(username);
			session.setAttribute("usuario", usuario);
			
			customer = customerService.findCustomerByName(usuario.getUserName());
		} else {
			customer = customerService.findCustomerByName(username);
		}
		
//		Usuario user =  (Usuario) session.getAttribute("usuario");
//		
//		Customer customer = customerService.findCustomerByName(user.getUserName());
//		
//		System.out.println(coche);
//		System.out.println(customer);
//		System.out.println("Holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//		System.out.println(session.getAttribute("usuario"));

		if (coche != null && customer != null) {
			Vehicle vehiculo = vehiculoService.getVehicleByMatrucula(coche);

			if (!customer.getCart().contains(vehiculo)) {

				Cart cart = new Cart();

				cart.setCustomer(customer);
				cart.setVehicle(vehiculo);

				cartService.saveCart(cart);

				return "redirect:/";

			} else {
				return "redirect:/";
			}
		}

		return "redirect:/";
	}

	@GetMapping("/removeFavorite")
	public String removeCarFromCart(@RequestParam(name = "coche", required = false) String coche,@RequestParam(name="cbck", required = false) String back,Authentication auth,
			HttpSession session) {

		if (auth != null) {
			Usuario user =  (Usuario) session.getAttribute("usuario");
			
			Customer customer = customerService.findCustomerByName(user.getUserName());
			
			if (!coche.equals("") && customer != null) {
				Vehicle vehiculo = vehiculoService.getVehicleByMatrucula(coche);

				customer.removeCarFromCart(vehiculo);

				customerService.updateCustomer(customer);
				
				if (back != null) {
					return "redirect:/profile/favorite";
				}

				return "redirect:/";
			}
		}

		return "redirect:/";
	}
	
}
