package com.ute.shop.service.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ute.shop.service.CookieService;

@Service
public class CookieServiceImpl implements CookieService{
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Override
	public Cookie add(String name, String value,int hours) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(hours * 60 * 60);
		cookie.setPath("/");
		return cookie;
	}
	
	@Override
	public String getValue(String name) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie: cookies) {
				if(cookie.getName().equalsIgnoreCase(name)) {
					return cookie.getValue();
				}
			}
		}
		return "";
	}
	@Override
	public Cookie remove(String name) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie: cookies) {
				if(cookie.getName().equalsIgnoreCase(name)) {
					cookie.setMaxAge(0);
					return cookie;
				}
			}
		}
		return null;
		
	}
}
