package com.ute.shop.model;

import java.util.Date;

import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
	private int orderId;
	private Date orderDate;
	@Min(value = 0)
	private double amount;
	private short status;
	private int customerId;
	private Boolean isEdit = false;
}
