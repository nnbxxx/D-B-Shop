package com.ute.shop.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ute.shop.domain.Order;

public interface OrderService {

	void deleteAll();

	Order getReferenceById(Integer id);

	void deleteAll(Iterable<? extends Order> entities);

	void deleteAllById(Iterable<? extends Integer> ids);

	Order getById(Integer id);

	void delete(Order entity);

	Order getOne(Integer id);

	void deleteById(Integer id);

	void deleteAllInBatch();

	long count();

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteAllInBatch(Iterable<Order> entities);

	boolean existsById(Integer id);

	void deleteInBatch(Iterable<Order> entities);

	Optional<Order> findById(Integer id);


	<S extends Order> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Order> S saveAndFlush(S entity);

	void flush();

	<S extends Order> List<S> saveAll(Iterable<S> entities);

	List<Order> findAllById(Iterable<Integer> ids);

	List<Order> findAll(Sort sort);

	Page<Order> findAll(Pageable pageable);

	List<Order> findAll();

	<S extends Order> S save(S entity);

	List<Order> findByOrderDateBetween(Date startDate, Date endDate);

}
