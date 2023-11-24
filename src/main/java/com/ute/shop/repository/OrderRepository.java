package com.ute.shop.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.shop.domain.Order;
import com.ute.shop.domain.Customer;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	List<Order> findByOrderDateBetween(Date startDate, Date endDate);
	List<Order> findByCustomer(Customer customer);
}
