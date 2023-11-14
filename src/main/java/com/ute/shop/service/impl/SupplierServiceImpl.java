package com.ute.shop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ute.shop.domain.Supplier;
import com.ute.shop.repository.CategoryRepository;
import com.ute.shop.repository.SupplierRepository;
import com.ute.shop.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService{

	private SupplierRepository supplierRepository;
	public SupplierServiceImpl(SupplierRepository supplierRepository) {
		this.supplierRepository = supplierRepository;
	}
	
	@Override
	public <S extends Supplier> S save(S entity) {
		return supplierRepository.save(entity);
	}

	@Override
	public List<Supplier> findAll() {
		return supplierRepository.findAll();
	}

	@Override
	public Page<Supplier> findAll(Pageable pageable) {
		return supplierRepository.findAll(pageable);
	}

	@Override
	public List<Supplier> findAll(Sort sort) {
		return supplierRepository.findAll(sort);
	}

	@Override
	public List<Supplier> findAllById(Iterable<Integer> ids) {
		return supplierRepository.findAllById(ids);
	}

	@Override
	public <S extends Supplier> List<S> saveAll(Iterable<S> entities) {
		return supplierRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		supplierRepository.flush();
	}

	@Override
	public <S extends Supplier> S saveAndFlush(S entity) {
		return supplierRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Supplier> List<S> saveAllAndFlush(Iterable<S> entities) {
		return supplierRepository.saveAllAndFlush(entities);
	}

	@Override
	public Optional<Supplier> findById(Integer id) {
		return supplierRepository.findById(id);
	}

	@Override
	public void deleteInBatch(Iterable<Supplier> entities) {
		supplierRepository.deleteInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return supplierRepository.existsById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Supplier> entities) {
		supplierRepository.deleteAllInBatch(entities);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		supplierRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public long count() {
		return supplierRepository.count();
	}

	@Override
	public void deleteAllInBatch() {
		supplierRepository.deleteAllInBatch();
	}

	@Override
	public void deleteById(Integer id) {
		supplierRepository.deleteById(id);
	}

	@Override
	public Supplier getOne(Integer id) {
		return supplierRepository.getOne(id);
	}

	@Override
	public void delete(Supplier entity) {
		supplierRepository.delete(entity);
	}

	@Override
	public Supplier getById(Integer id) {
		return supplierRepository.getById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		supplierRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Supplier> entities) {
		supplierRepository.deleteAll(entities);
	}

	@Override
	public Supplier getReferenceById(Integer id) {
		return supplierRepository.getReferenceById(id);
	}

	@Override
	public void deleteAll() {
		supplierRepository.deleteAll();
	}
	
	
	
}
