package com.ute.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ute.shop.domain.OrderDetail;

public interface OrderDetailService {

	void deleteAll();

	OrderDetail getReferenceById(Integer id);

	void deleteAll(Iterable<? extends OrderDetail> entities);

	void deleteAllById(Iterable<? extends Integer> ids);

	OrderDetail getById(Integer id);

	void delete(OrderDetail entity);

	OrderDetail getOne(Integer id);

	void deleteById(Integer id);

	void deleteAllInBatch();

	long count();

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteAllInBatch(Iterable<OrderDetail> entities);

	boolean existsById(Integer id);

	void deleteInBatch(Iterable<OrderDetail> entities);

	Optional<OrderDetail> findById(Integer id);

	<S extends OrderDetail> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends OrderDetail> S saveAndFlush(S entity);

	void flush();

	<S extends OrderDetail> List<S> saveAll(Iterable<S> entities);

	List<OrderDetail> findAllById(Iterable<Integer> ids);

	List<OrderDetail> findAll(Sort sort);

	Page<OrderDetail> findAll(Pageable pageable);

	List<OrderDetail> findAll();
 
	<S extends OrderDetail> S save(S entity);

	List<OrderDetail> searchByOrderIdandProductId(Integer orderId, Integer productId);

	List<OrderDetail> searchByOrderId(Integer orderId);
		

}
