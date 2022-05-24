package org.iesalixar.servidor.services;

import java.util.ArrayList;
import java.util.List;

import org.iesalixar.servidor.models.OrderDetail;
import org.iesalixar.servidor.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	OrderDetailRepository orderdetailRepo;
	
	@Override
	public ArrayList<OrderDetail> getAllOrderDetails() {
		// TODO Auto-generated method stub
		return (ArrayList<OrderDetail>) orderdetailRepo.findAll();
	}

	public OrderDetail guardarrOrder(OrderDetail orderDetail) {
		
		return orderdetailRepo.save(orderDetail);
	}
}
