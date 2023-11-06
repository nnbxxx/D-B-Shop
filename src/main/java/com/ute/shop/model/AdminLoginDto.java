package com.ute.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminLoginDto {
	@NotEmpty

	private String username;
	@NotEmpty

	private String password;
	
	private Boolean rememberMe = false;
}
