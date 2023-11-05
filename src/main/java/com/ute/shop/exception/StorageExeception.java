package com.ute.shop.exception;

public class StorageExeception extends RuntimeException{

	public StorageExeception(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public StorageExeception(String message, Exception e) {
		// TODO Auto-generated constructor stub
		super(message,e);
	}
	
}
