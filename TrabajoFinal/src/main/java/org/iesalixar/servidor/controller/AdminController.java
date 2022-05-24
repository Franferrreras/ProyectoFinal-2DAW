package org.iesalixar.servidor.controller;

import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.iesalixar.servidor.models.Comercial;
import org.iesalixar.servidor.models.Customer;
import org.iesalixar.servidor.models.OrderDetail;
import org.iesalixar.servidor.models.Usuario;
import org.iesalixar.servidor.services.ComercialServiceImpl;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UsuarioServiceImpl usuarioService;
	
	@Autowired
	CustomerServiceImpl customerService;

	@Autowired
	ComercialServiceImpl comercialService;

	@Autowired
	OrderDetailServiceImpl ordertlService;

	@GetMapping
	public String home(@RequestParam(required = false, name = "cmrc") String cmrc, Model model, Authentication auth,
			HttpSession session) {

		// Obtener usuario por session
		String username = auth.getName();

		if (session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.findUsuarioByUserName(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
		}

		List<Comercial> list_comercial = comercialService.getAllComercial();
		Comercial comercial = new Comercial();
		Comercial comercial_selected = null;

		// Obtener lista de clientes de los comerciales
		List<OrderDetail> orderDetail = ordertlService.getAllOrderDetails();
		HashSet<Customer> list_customer = new HashSet<>();

		if (cmrc != null) {
			comercial_selected = comercialService.getComercialById(Long.parseLong(cmrc));

			for (OrderDetail ord : orderDetail) {
				if (ord.getComercial().getId() == Long.parseLong(cmrc)) {
					list_customer.add(ord.getCustomer());
				}
			}

		} else {

			for (OrderDetail ord : orderDetail) {
				if (ord.getComercial().getId() == list_comercial.get(0).getId()) {
					list_customer.add(ord.getCustomer());
				}
			}
		}
		System.out.println(list_customer);

		model.addAttribute("contenido", "INICIO");
		model.addAttribute("comerciales", list_comercial);
		model.addAttribute("comercial", comercial);
		model.addAttribute("cmrsl", comercial_selected);
		model.addAttribute("customers", list_customer);
		return "dashboard";
	}

	@PostMapping
	public String home(@ModelAttribute Comercial comercial, Model model) {

		return "redirect:/admin?cmrc=" + comercial.getId();
	}
}
