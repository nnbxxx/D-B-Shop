package com.ute.shop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ute.shop.domain.Category;
import com.ute.shop.repository.CategoryRepository;
import com.ute.shop.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{
	private CategoryRepository categoryRepository;
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	@Override
	public <S extends Category> S save(S entity) {
		return categoryRepository.save(entity);
	}
	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}
	@Override
	public Page<Category> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}
	@Override
	public List<Category> findAll(Sort sort) {
		return categoryRepository.findAll(sort);
	}
	@Override
	public List<Category> findAllById(Iterable<Integer> ids) {
		return categoryRepository.findAllById(ids);
	}
	
	@Override
	public List<Category> findByNameContaining(String name) {
		return categoryRepository.findByNameContaining(name);
	}
	
	@Override
	public Page<Category> findByNameContaining(String name, Pageable pageable) {
		return categoryRepository.findByNameContaining(name, pageable);
	}

	@Override
	public <S extends Category> List<S> saveAll(Iterable<S> entities) {
		return categoryRepository.saveAll(entities);
	}
	@Override
	public void flush() {
		categoryRepository.flush();
	}
	@Override
	public <S extends Category> S saveAndFlush(S entity) {
		return categoryRepository.saveAndFlush(entity);
	}
	@Override
	public <S extends Category> List<S> saveAllAndFlush(Iterable<S> entities) {
		return categoryRepository.saveAllAndFlush(entities);
	}
	@Override
	public Optional<Category> findById(Integer id) {
		return categoryRepository.findById(id);
	}
	@Override
	public void deleteInBatch(Iterable<Category> entities) {
		categoryRepository.deleteInBatch(entities);
	}
	@Override
	public boolean existsById(Integer id) {
		return categoryRepository.existsById(id);
	}
	@Override
	public void deleteAllInBatch(Iterable<Category> entities) {
		categoryRepository.deleteAllInBatch(entities);
	}
	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		categoryRepository.deleteAllByIdInBatch(ids);
	}
	@Override
	public long count() {
		return categoryRepository.count();
	}
	@Override
	public void deleteAllInBatch() {
		categoryRepository.deleteAllInBatch();
	}
	@Override
	public void deleteById(Integer id) {
		categoryRepository.deleteById(id);
	}
	@Override
	public Category getOne(Integer id) {
		return categoryRepository.getOne(id);
	}
	@Override
	public void delete(Category entity) {
		categoryRepository.delete(entity);
	}
	@Override
	public Category getById(Integer id) {
		return categoryRepository.getById(id);
	}
	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		categoryRepository.deleteAllById(ids);
	}
	@Override
	public void deleteAll(Iterable<? extends Category> entities) {
		categoryRepository.deleteAll(entities);
	}
	@Override
	public Category getReferenceById(Integer id) {
		return categoryRepository.getReferenceById(id);
	}
	@Override
	public void deleteAll() {
		categoryRepository.deleteAll();
	}
	
	
	
}
