package com.ute.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.shop.domain.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category,  Integer>{

}
