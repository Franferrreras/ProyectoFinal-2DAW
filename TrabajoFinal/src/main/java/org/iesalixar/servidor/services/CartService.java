package org.iesalixar.servidor.services;

import java.util.List;

import org.iesalixar.servidor.models.Cart;
import org.iesalixar.servidor.models.Customer;

public interface CartService {

	public List<Cart> getAllCart();
	public List<Cart> getCartByCustomer(Customer customer);
}
