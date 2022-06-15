package org.iesalixar.servidor.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.iesalixar.servidor.models.Customer;
import org.iesalixar.servidor.models.OrderDetail;
import org.iesalixar.servidor.models.Usuario;
import org.iesalixar.servidor.services.CustomerServiceImpl;
import org.iesalixar.servidor.services.OrderDetailServiceImpl;
import org.iesalixar.servidor.services.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	OrderDetailServiceImpl orderdService;
	
	@Autowired
	UsuarioServiceImpl userService;
	
	@Autowired
	CustomerServiceImpl customerService;
	
	@GetMapping("/edit")
	public String editGet(Model model, HttpSession session, Authentication auth) {
		
		
		if (session.getAttribute("usuario") != null && auth != null) {
			
			Usuario user = new Usuario();
			
			model.addAttribute("dto",user);
			
			return "profile";
		} else {
			return "redirect:/";
		}
		
	}
	
	
	@PostMapping("/edit")
	public String editPost(HttpSession session, @ModelAttribute Usuario dto) {
		
		System.out.println(dto);
		
		if (session.getAttribute("usuario") != null) {
			
			Usuario user_aux = (Usuario) session.getAttribute("usuario");
			Customer ctmerDB = customerService.getCustomerByDni(user_aux.getDni());
			
			System.out.println(ctmerDB);
			
			if (!dto.getNombre().isEmpty()) {
				ctmerDB.setNombre(dto.getNombre());
			}
			
			if (!dto.getApellidos().isEmpty()) {
				ctmerDB.setApellidos(dto.getApellidos());
			}
			
			if (!dto.getPhone().isEmpty()) {
				ctmerDB.setPhone(dto.getPhone());
			}
			
			customerService.updateCustomer(ctmerDB);
			ctmerDB.setPassword(null);
			session.setAttribute("usuario", ctmerDB);
			
		}
		
		return "redirect:/profile/edit";
	}
	
	@GetMapping("/orders")
	public String orderGet(Model model, HttpSession session) {
		
		if (session.getAttribute("usuario") != null) {
			
			Usuario userDB = (Usuario) session.getAttribute("usuario");
			
			List<OrderDetail> orders = orderdService.getOrdersByCustomer(userDB.getId());
			
			model.addAttribute("orders", orders);
		}
		
		return "orders";
	}
	
}
