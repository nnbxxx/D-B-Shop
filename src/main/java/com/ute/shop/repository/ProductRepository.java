package com.ute.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.shop.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
}
