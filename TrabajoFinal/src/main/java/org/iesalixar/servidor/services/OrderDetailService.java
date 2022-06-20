package org.iesalixar.servidor.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.iesalixar.servidor.models.OrderDetail;
import org.iesalixar.servidor.models.Vehicle;

public interface OrderDetailService {

	public ArrayList<OrderDetail> getAllOrderDetails();
	public OrderDetail updateOrderDetail(OrderDetail ord);
	public OrderDetail insertOrderDetail(OrderDetail ord);
	public OrderDetail insertOrderDetail2(Long id_customer, Long id_vehiculo, int price, Date date1, Date date2, Date date3, Long id_comercial);
	public List<OrderDetail> getOrdersByCustomer(Long id_customer);
	public Boolean removeOrderDetail(OrderDetail ord);
	public OrderDetail getOrderDetailByVehicle(Vehicle vehicle);
}
