package com.ute.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ute.shop.domain.Customer;

public interface CustomerService {

	<S extends Customer> List<S> findAll(Example<S> example, Sort sort);

	void deleteAll();

	Customer getReferenceById(Integer id);

	Customer getById(Integer id);

	void delete(Customer entity);

	Customer getOne(Integer id);

	void deleteById(Integer id);

	void deleteAllInBatch();

	long count();

	boolean existsById(Integer id);

	Optional<Customer> findById(Integer id);

	<S extends Customer> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Customer> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Customer> S saveAndFlush(S entity);

	void flush();

	<S extends Customer> List<S> saveAll(Iterable<S> entities);

	List<Customer> findAllById(Iterable<Integer> ids);

	List<Customer> findAll(Sort sort);

	Page<Customer> findAll(Pageable pageable);

	List<Customer> findAll();

	<S extends Customer> S save(S entity);

	Optional<Customer> findByPhone(String phone);

	Customer login(String phone, String password);


}
