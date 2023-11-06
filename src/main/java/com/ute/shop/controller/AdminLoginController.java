package com.ute.shop.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ute.shop.domain.Account;
import com.ute.shop.model.AdminLoginDto;
import com.ute.shop.service.AccountService;

@Controller
public class AdminLoginController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private HttpSession session;
	
	@GetMapping("alogin")
	public String login(ModelMap model) {
		model.addAttribute("account", new AdminLoginDto());
		return "/admin/accounts/login";
	}
	@PostMapping("alogin")
	public ModelAndView login(ModelMap model,@Valid @ModelAttribute("account") AdminLoginDto adminLoginDto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ModelAndView("/admin/accounts/login",model);
		}
		Account account = accountService.login(adminLoginDto.getUsername(), adminLoginDto.getPassword());
		if(account == null) {
			model.addAttribute("message","Invalid username or password");
			return new ModelAndView("/admin/accounts/login",model);
		}
		session.setAttribute("username", account.getUsername());
		Object ruri = session.getAttribute("redirect-uri");
		if(ruri != null) {
			session.removeAttribute("redirect-uri");
			return new ModelAndView("redirect:" + ruri);
		}
		
		return new ModelAndView("forward:/admin/categories",model);
				
	}
}
