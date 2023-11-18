package com.ute.shop.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.ute.shop.domain.CartItem;
import com.ute.shop.domain.Product;

public interface ShoppingCartService {

	double getAmount();

	int getCount();

	Collection<CartItem> getItems();

	void clear();

	void remove(Integer id);

	CartItem update(Integer id, int quantity);

	CartItem add(Integer id) ;

	Map<Integer, CartItem> getDBItems();

	List<Product> getProducts();


}
