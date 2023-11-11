package com.ute.shop.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	@Column(nullable = false)
	private double amount;
	@Column(nullable = false)
	private short status;
	
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;
	
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
	private Set<OrderDetail> orderDetails;
	
	@PrePersist
	public void preCreate() {
		amount = 0f;
		orderDate = new Date();
	}
	@PreUpdate 
	public void preUpdate() {
		orderDate = new Date();
	}
}

