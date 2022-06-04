package org.iesalixar.servidor.services;

import java.util.List;

import org.iesalixar.servidor.models.Cart;
import org.iesalixar.servidor.models.Customer;
import org.iesalixar.servidor.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository cartRepo;
	
	@Override
	public List<Cart> getAllCart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cart> getCartByCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;	
	
	}
	
	public Cart saveCart(Cart cart) {
		return cartRepo.save(cart);
	}

	public boolean deleteCart(Cart cart) {
		
		cartRepo.delete(cart);
		
		return true;
	}
}
