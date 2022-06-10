package org.iesalixar.servidor.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.iesalixar.servidor.models.OrderDetail;
import org.iesalixar.servidor.models.Usuario;
import org.iesalixar.servidor.services.OrderDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	OrderDetailServiceImpl orderdService;
	
	@GetMapping("/edit")
	public String editGet(Model model, HttpSession session) {
		
		if (session.getAttribute("usuario") != null) {
			
			Usuario user = (Usuario) session.getAttribute("usuario");
			
			model.addAttribute(user);
		}
		
		return "profile";
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
