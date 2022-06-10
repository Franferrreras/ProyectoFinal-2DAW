package org.iesalixar.servidor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/how-work")
@Controller
public class ServiceController {

	
	@GetMapping
	public String serviceGet(Model model) {
		
		return "howork";
	}
}
