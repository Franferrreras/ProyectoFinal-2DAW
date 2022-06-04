package org.iesalixar.servidor.repository;

import java.util.List;

import org.iesalixar.servidor.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	public List<Cart> findCartByCustomer(Cart cart);
}
