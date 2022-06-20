package org.iesalixar.servidor.repository;

import java.util.Date;
import java.util.List;

import org.iesalixar.servidor.models.OrderDetail;
import org.iesalixar.servidor.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long> {
	
	@Query(value = "INSERT INTO order_detail values( ?, ?, ?, ?, ?, ?, ?)", nativeQuery = true)
	public OrderDetail insertIntoOrderDetail(Long id_customer, Long id_vehiculo, int price, Date date1 ,Date date2 ,Date date3 ,Long id_comercial);

	@Query(value = "SELECT * FROM order_detail WHERE id_customer = ?", nativeQuery = true)
	public List<OrderDetail> getCarsByCustomer(Long id_customer);
	
	public OrderDetail findOrderDetailByVehicle(Vehicle vehicle);
}
