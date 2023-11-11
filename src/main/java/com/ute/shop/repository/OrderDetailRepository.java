package com.ute.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ute.shop.domain.OrderDetail;
import java.util.List;


@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{
//	@Query("SELECT o FROM OrderDetail o WHERE o.order_id = ?1 and o.product_id = ?2")
	@Query(value = "select * from orderdetails o where o.order_id = ?1 and o.product_id = ?2", nativeQuery = true)
	List<OrderDetail> searchByOrderIdandProductId(Integer orderId, Integer productId);
	@Query(value = "select * from orderdetails o where o.order_id = ?1", nativeQuery = true)
	List<OrderDetail>searchByOrderId(Integer orderId);
}
 