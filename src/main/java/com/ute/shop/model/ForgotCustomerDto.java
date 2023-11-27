package com.ute.shop.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgotCustomerDto {
	@Email
	private String email;
	@NotEmpty
	@Length(min = 6)
	private String password;
	@NotEmpty
	@Length(min = 10)
	private String phone;
}
