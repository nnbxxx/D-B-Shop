package com.ute.shop.service;

import javax.servlet.http.Cookie;

public interface CookieService {

	Cookie remove(String name);

	String getValue(String name);

	Cookie add(String name, String value, int hours);

	
}
