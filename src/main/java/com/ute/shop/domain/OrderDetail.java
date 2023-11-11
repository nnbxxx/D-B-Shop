package com.ute.shop.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orderdetails")
public class OrderDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderDetailId;
	@Column(nullable = false)
	private int quantity;
	@Column(nullable = false)
	private double unitPrice;
	@ManyToOne
	@JoinColumn(name="productId")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "orderId")
	private Order order;
	
	@PrePersist
	public void preCreate() {
		quantity = 1;
	}

}
