package com.ute.shop.model;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDto  implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer supplierId;
	@NotEmpty
	@Length(min = 5)
	private String name;
	
	private Boolean isEdit = false;
	
}
