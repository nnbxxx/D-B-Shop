package com.ute.shop.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ute.shop.domain.CartItem;
import com.ute.shop.domain.Product;
import com.ute.shop.model.SupplierDto;
import com.ute.shop.service.ProductService;
import com.ute.shop.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
	@Autowired
	ProductService productService;
	
	Map<Integer, CartItem> map = new HashMap<>();
	public List<Product> getProducts(){
		return productService.findAll();
	}
	public Map<Integer, CartItem> getDBItems(){
		Map<Integer, CartItem> items = new HashMap<>();
		List<Product>  listProducts = this.getProducts();
		items = listProducts.stream().
				collect(Collectors.toMap(Product::getProductId, 
				obj-> new CartItem(obj.getProductId(),obj.getName(),
						obj.getQuantity(),obj.getUnitPrice() * (100 - obj.getDiscount())*0.01)));
		return items;
	}
	public CartItem add(Integer id) {
		Set<Integer> set = this.getDBItems().keySet();
		for(Object key:set) {
			if(this.getDBItems().get(key).getProductId() == id) {
				map.put((Integer) key, this.getDBItems().get(key));
				map.get(key).setQuantity(1);
				return this.getDBItems().get(key);
			}
		}
		return null;
	} 
	public CartItem update(Integer id, int quantity) {
		Set<Integer> set = this.getDBItems().keySet();
		for(Object key:set) {
			if(this.getDBItems().get(key).getProductId() == id) {
				map.get(key).setQuantity(quantity);
				map.replace(id, map.get(key));
				return this.getDBItems().get(key);
			}
		}
		return null;
	}
	public void remove(Integer id) {
		Set<Integer> set = this.getDBItems().keySet();
		for(Object key:set) {
			if(this.getDBItems().get(key).getProductId() == id) {
				map.remove(key);
				break;
			}
		}
		
	}
	public void clear() {
		map.clear();
	}
	public Collection<CartItem> getItems(){
		return map.values();
	}
	public int getCount() {
		int count = 0;
		Set<Integer> set = map.keySet();
		for(Object key:set) {
			count += map.get(key).getQuantity();
		}
		return count;
	}
	public double getAmount() {
		double amount = 0;
		Set<Integer> set = map.keySet();
		for(Object key:set) {
			amount += map.get(key).getQuantity() * map.get(key).getUnitPrice();
		}
		return amount;
	}
	
}