package com.ute.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ute.shop.domain.Supplier;

public interface SupplierService {

	void deleteAll();

	Supplier getReferenceById(Integer id);

	void deleteAll(Iterable<? extends Supplier> entities);

	void deleteAllById(Iterable<? extends Integer> ids);

	Supplier getById(Integer id);

	void delete(Supplier entity);

	Supplier getOne(Integer id);

	void deleteById(Integer id);

	void deleteAllInBatch();

	long count();

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteAllInBatch(Iterable<Supplier> entities);

	boolean existsById(Integer id);

	void deleteInBatch(Iterable<Supplier> entities);

	Optional<Supplier> findById(Integer id);

	<S extends Supplier> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Supplier> S saveAndFlush(S entity);

	void flush();

	<S extends Supplier> List<S> saveAll(Iterable<S> entities);

	List<Supplier> findAllById(Iterable<Integer> ids);

	List<Supplier> findAll(Sort sort);

	Page<Supplier> findAll(Pageable pageable);

	List<Supplier> findAll();

	<S extends Supplier> S save(S entity);

}
