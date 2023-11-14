package com.ute.shop.controller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {
	@RequestMapping("/")
	public String home() {
		return "/site/home/index";
	}
	@RequestMapping("/admin")
	public String admiin() {
		return "/admin/content";
	}

}
