package com.ute.shop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ute.shop.domain.Account;
import com.ute.shop.repository.AccountRepository;
import com.ute.shop.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Account login(String username, String password) {
		Optional<Account> optionalAccount = findById(username);
		if(optionalAccount.isPresent() && bCryptPasswordEncoder.matches(password, optionalAccount.get().getPassword())) {
			optionalAccount.get().setPassword("");
			return optionalAccount.get();
		}
		return null;
	}
	
	@Override
	public <S extends Account> S save(S entity) {
		Optional<Account> optional = accountRepository.findById(entity.getUsername());
		if(optional.isPresent()) {
			if(StringUtils.isEmpty(entity.getPassword())) {
				entity.setPassword(optional.get().getPassword());
			}
			else {
				entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
			}
		}
		entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
		return accountRepository.save(entity);
	}

	@Override
	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	public Page<Account> findAll(Pageable pageable) {
		return accountRepository.findAll(pageable);
	}

	@Override
	public List<Account> findAll(Sort sort) {
		return accountRepository.findAll(sort);
	}

	@Override
	public List<Account> findAllById(Iterable<String> ids) {
		return accountRepository.findAllById(ids);
	}

	@Override
	public <S extends Account> List<S> saveAll(Iterable<S> entities) {
		return accountRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		accountRepository.flush();
	}

	@Override
	public <S extends Account> S saveAndFlush(S entity) {
		return accountRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Account> List<S> saveAllAndFlush(Iterable<S> entities) {
		return accountRepository.saveAllAndFlush(entities);
	}

	@Override
	public Optional<Account> findById(String id) {
		return accountRepository.findById(id);
	}

	@Override
	public void deleteInBatch(Iterable<Account> entities) {
		accountRepository.deleteInBatch(entities);
	}

	@Override
	public boolean existsById(String id) {
		return accountRepository.existsById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Account> entities) {
		accountRepository.deleteAllInBatch(entities);
	}

	@Override
	public <S extends Account> boolean exists(Example<S> example) {
		return accountRepository.exists(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<String> ids) {
		accountRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public long count() {
		return accountRepository.count();
	}

	@Override
	public void deleteAllInBatch() {
		accountRepository.deleteAllInBatch();
	}

	@Override
	public void deleteById(String id) {
		accountRepository.deleteById(id);
	}

	@Override
	public Account getOne(String id) {
		return accountRepository.getOne(id);
	}

	@Override
	public void delete(Account entity) {
		accountRepository.delete(entity);
	}

	@Override
	public Account getById(String id) {
		return accountRepository.getById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		accountRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Account> entities) {
		accountRepository.deleteAll(entities);
	}

	@Override
	public Account getReferenceById(String id) {
		return accountRepository.getReferenceById(id);
	}

	@Override
	public void deleteAll() {
		accountRepository.deleteAll();
	}

	@Override
	public <S extends Account> List<S> findAll(Example<S> example) {
		return accountRepository.findAll(example);
	}
	
}
