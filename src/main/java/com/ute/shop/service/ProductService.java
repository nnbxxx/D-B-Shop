package com.ute.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ute.shop.domain.Category;
import com.ute.shop.domain.Product;
import com.ute.shop.domain.Supplier;

public interface ProductService {

	void deleteAll();

	Product getReferenceById(Integer id);

	void deleteAll(Iterable<? extends Product> entities);

	void deleteAllById(Iterable<? extends Integer> ids);

	Product getById(Integer id);

	void delete(Optional<Product> optional);

	Product getOne(Integer id);

	void deleteById(Integer id);

	void deleteAllInBatch();

	long count();

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteAllInBatch(Iterable<Product> entities);

	boolean existsById(Integer id);

	void deleteInBatch(Iterable<Product> entities);

	Optional<Product> findById(Integer id);

	<S extends Product> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Product> S saveAndFlush(S entity);

	void flush();

	<S extends Product> List<S> saveAll(Iterable<S> entities);

	List<Product> findAllById(Iterable<Integer> ids);

	List<Product> findAll(Sort sort);

	Page<Product> findAll(Pageable pageable);

	List<Product> findAll();

	<S extends Product> S save(S entity);

	void delete(Product entity);

	List<Product> findByCategory(Category category);

	List<Product> findByNameContaining(String name);

	List<Product> findByUnitPriceBetween(double min, double max);

	List<Product> findBySupplier(Supplier supplier);

	List<Product> findByStatus(short status);
	
}
