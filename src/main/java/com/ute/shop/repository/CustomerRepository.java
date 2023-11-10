package com.ute.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.shop.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
