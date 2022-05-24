package org.iesalixar.servidor.controller;

import java.util.ArrayList;

import org.iesalixar.servidor.models.OrderDetail;
import org.iesalixar.servidor.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiRestController {
	
	@Autowired
	OrderDetailService orderService;
	
	@GetMapping()
	public ArrayList<OrderDetail> api() {
		ArrayList<OrderDetail> list = orderService.getAllOrderDetails();
		return orderService.getAllOrderDetails();
	}
}
