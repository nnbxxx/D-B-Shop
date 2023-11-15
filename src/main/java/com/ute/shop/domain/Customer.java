package com.ute.shop.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "customers")
public class Customer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	@Column(columnDefinition = "nvarchar(50) not null")
	private String name;
	@Column(columnDefinition = "nvarchar(100) not null")
	private String email;
	@Column(length = 100, nullable = false,unique = true)
	private String password;
	@Column(length = 20,unique = true)
	private String phone;
	@Temporal(TemporalType.DATE)
	private Date registerDate;
	@Column(nullable = false)
	private short status;
	
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	private Set<Order> orders;
	@PrePersist
	public void preRegisterDate() {
		registerDate = new Date();
	}
	@PreUpdate
	public void preUpdate() {
		registerDate = new Date();
	}
}