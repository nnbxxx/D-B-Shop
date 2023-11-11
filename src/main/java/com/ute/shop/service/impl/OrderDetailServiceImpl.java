package com.ute.shop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ute.shop.domain.OrderDetail;
import com.ute.shop.repository.OrderDetailRepository;
import com.ute.shop.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	@Autowired
	OrderDetailRepository detailRepository;

	@Override
	public <S extends OrderDetail> S save(S entity) {
		return detailRepository.save(entity);
	}

	@Override
	public List<OrderDetail> findAll() {
		return detailRepository.findAll();
	}

	@Override
	public Page<OrderDetail> findAll(Pageable pageable) {
		return detailRepository.findAll(pageable);
	}

	@Override
	public List<OrderDetail> findAll(Sort sort) {
		return detailRepository.findAll(sort);
	}

	@Override
	public List<OrderDetail> findAllById(Iterable<Integer> ids) {
		return detailRepository.findAllById(ids);
	}

	@Override
	public <S extends OrderDetail> List<S> saveAll(Iterable<S> entities) {
		return detailRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		detailRepository.flush();
	}

	@Override
	public <S extends OrderDetail> S saveAndFlush(S entity) {
		return detailRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends OrderDetail> List<S> saveAllAndFlush(Iterable<S> entities) {
		return detailRepository.saveAllAndFlush(entities);
	}

	@Override
	public Optional<OrderDetail> findById(Integer id) {
		return detailRepository.findById(id);
	}

	@Override
	public void deleteInBatch(Iterable<OrderDetail> entities) {
		detailRepository.deleteInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return detailRepository.existsById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<OrderDetail> entities) {
		detailRepository.deleteAllInBatch(entities);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		detailRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public long count() {
		return detailRepository.count();
	}

	@Override
	public void deleteAllInBatch() {
		detailRepository.deleteAllInBatch();
	}

	@Override
	public void deleteById(Integer id) {
		detailRepository.deleteById(id);
	}

	@Override
	public OrderDetail getOne(Integer id) {
		return detailRepository.getOne(id);
	}

	@Override
	public void delete(OrderDetail entity) {
		detailRepository.delete(entity);
	}

	@Override
	public OrderDetail getById(Integer id) {
		return detailRepository.getById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		detailRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends OrderDetail> entities) {
		detailRepository.deleteAll(entities);
	}

	@Override
	public OrderDetail getReferenceById(Integer id) {
		return detailRepository.getReferenceById(id);
	}

	@Override
	public void deleteAll() {
		detailRepository.deleteAll();
	}

	@Override
	public List<OrderDetail> searchByOrderIdandProductId(Integer orderId, Integer productId) {
		return detailRepository.searchByOrderIdandProductId(orderId, productId);
	}

	@Override
	public List<OrderDetail> searchByOrderId(Integer orderId) {
		return detailRepository.searchByOrderId(orderId);
	}
	
	
}
