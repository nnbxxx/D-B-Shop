package com.ute.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.shop.domain.Category;
import com.ute.shop.domain.Product;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	List<Product> findByCategory(Category category);
	List<Product> findByNameContaining(String name);
	List<Product> findByUnitPriceBetween(double min,double max);
}
