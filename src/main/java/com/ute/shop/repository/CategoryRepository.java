package com.ute.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.shop.domain.Category;
import java.util.List;



@Repository
public interface CategoryRepository extends JpaRepository<Category,  Integer>{
	List<Category> findByNameContaining(String name);
	Page<Category> findByNameContaining(String name, Pageable pageable);

}
