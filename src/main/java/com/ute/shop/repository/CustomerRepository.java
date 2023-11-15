package com.ute.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.shop.domain.Customer;
import java.util.List;
import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	Optional<Customer> findByPhone(String phone);
}
