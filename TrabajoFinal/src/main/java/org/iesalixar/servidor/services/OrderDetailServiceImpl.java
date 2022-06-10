package org.iesalixar.servidor.services;

import java.util.ArrayList;
import java.util.Date;
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

	@Override
	public OrderDetail updateOrderDetail(OrderDetail ord) {
		// TODO Auto-generated method stub
		return orderdetailRepo.save(ord);
	}

	@Override
	public OrderDetail insertOrderDetail(OrderDetail ord) {
		// TODO Auto-generated method stub
		return orderdetailRepo.save(ord);
	}

	@Override
	public OrderDetail insertOrderDetail2(Long id_customer, Long id_vehiculo, int price, Date date1, Date date2,
			Date date3, Long id_comercial) {
		// TODO Auto-generated method stub
		return orderdetailRepo.insertIntoOrderDetail(id_customer, id_vehiculo, price , date1, date2, date3, id_comercial);
	}

	@Override
	public List<OrderDetail> getOrdersByCustomer(Long id_customer) {
		// TODO Auto-generated method stub
		return orderdetailRepo.getCarsByCustomer(id_customer);
	}

}
