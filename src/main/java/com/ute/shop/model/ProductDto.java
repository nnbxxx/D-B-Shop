package com.ute.shop.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

	private Integer productId;
	private String name;
	private int quantity;
	private double unitPrice;
	private String image;
	private MultipartFile imageFile;
	private String description;
	private Double discount;
	private Date enteredDate;
	private short status;
	private Integer categoryId;
	private Integer supplierId;
	
	private Boolean isEdit;
	
}