package org.iesalixar.servidor.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.iesalixar.servidor.models.Cart;
import org.iesalixar.servidor.models.Customer;
import org.iesalixar.servidor.models.OrderDetail;
import org.iesalixar.servidor.models.Usuario;
import org.iesalixar.servidor.services.CustomerService;
import org.iesalixar.servidor.services.OrderDetailService;
import org.iesalixar.servidor.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiRestController {

	@Autowired
	OrderDetailService orderService;

	@Autowired
	CustomerService customerService;

	@Autowired
	UsuarioService usuarioService;

	@GetMapping()
	public ArrayList<OrderDetail> api() {
		ArrayList<OrderDetail> list = orderService.getAllOrderDetails();
		return orderService.getAllOrderDetails();
	}

	@GetMapping("/2")
	public ArrayList<Cart> cart(Authentication auth, HttpSession session) {

		ArrayList<Cart> cart = new ArrayList<>();
		
		if (auth != null) {
			String username = auth.getName();

			Customer customer = null;

			if (session.getAttribute("usuario") == null) {
				Usuario usuario = usuarioService.findUsuarioByUserName(username);
				usuario.setPassword(null);
				session.setAttribute("usuario", usuario);
				customer = customerService.findCustomerByName(usuario.getUserName());
			} 
			
			if (username!=null) {
				customer = customerService.findCustomerByName(username);
				
				if (customer != null) {
					for (Cart c : customer.getCart()) {
						cart.add(c);
					}
					
					return cart;
				}
				
			}

			return cart;
		}

		return cart;
	}
}
