package com.ute.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.shop.domain.Supplier;



@Repository
public interface SupplierRepository extends JpaRepository<Supplier,  Integer>{
	
}
