package com.ute.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ute.shop.domain.Category;

public interface CategoryService {

	void deleteAll();

	Category getReferenceById(Integer id);

	void deleteAll(Iterable<? extends Category> entities);

	void deleteAllById(Iterable<? extends Integer> ids);

	Category getById(Integer id);

	void delete(Category entity);

	Category getOne(Integer id);

	void deleteById(Integer id);

	void deleteAllInBatch();

	long count();

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteAllInBatch(Iterable<Category> entities);

	boolean existsById(Integer id);

	void deleteInBatch(Iterable<Category> entities);

	Optional<Category> findById(Integer id);

	<S extends Category> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Category> S saveAndFlush(S entity);

	void flush();

	<S extends Category> List<S> saveAll(Iterable<S> entities);

	List<Category> findAllById(Iterable<Integer> ids);

	List<Category> findAll(Sort sort);

	Page<Category> findAll(Pageable pageable);

	List<Category> findAll();

	<S extends Category> S save(S entity);

	List<Category> findByNameContaining(String name);

	Page<Category> findByNameContaining(String name, Pageable pageable);

	


	

}
