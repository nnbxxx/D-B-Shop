package com.ute.shop.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
	@NotEmpty
	@Length(min = 6)
	private String username;
	@NotEmpty
	@Length(min = 6)
	private String password;
	
	private Boolean isEdit = false;
}
