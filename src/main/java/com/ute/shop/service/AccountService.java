package com.ute.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ute.shop.domain.Account;

public interface AccountService {

	<S extends Account> List<S> findAll(Example<S> example);

	void deleteAll();

	Account getReferenceById(String id);

	void deleteAll(Iterable<? extends Account> entities);

	void deleteAllById(Iterable<? extends String> ids);

	Account getById(String id);

	void delete(Account entity);

	Account getOne(String id);

	void deleteById(String id);

	void deleteAllInBatch();

	long count();

	void deleteAllByIdInBatch(Iterable<String> ids);

	<S extends Account> boolean exists(Example<S> example);

	void deleteAllInBatch(Iterable<Account> entities);

	boolean existsById(String id);

	void deleteInBatch(Iterable<Account> entities);

	Optional<Account> findById(String id);

	<S extends Account> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Account> S saveAndFlush(S entity);

	void flush();

	<S extends Account> List<S> saveAll(Iterable<S> entities);

	List<Account> findAllById(Iterable<String> ids);

	List<Account> findAll(Sort sort);

	Page<Account> findAll(Pageable pageable);

	List<Account> findAll();

	<S extends Account> S save(S entity);

	Account login(String username, String password);

}
