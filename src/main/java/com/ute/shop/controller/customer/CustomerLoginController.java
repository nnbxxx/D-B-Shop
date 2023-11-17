package com.ute.shop.controller.customer;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ute.shop.domain.Customer;
import com.ute.shop.model.CustomerDto;
import com.ute.shop.model.CustomerLoginDto;
import com.ute.shop.service.CustomerService;

@Controller

public class CustomerLoginController {
	@Autowired
	private HttpSession session;
	@Autowired
	private CustomerService customerService;
		
	@GetMapping(value = {"clogin"})
	public String login(ModelMap model) {
		model.addAttribute("customer", new CustomerLoginDto());
		return "/site/accounts/login";
	}
	@GetMapping(value = {"cregister"})
	public String register(ModelMap model) {
		CustomerDto dto = new CustomerDto();
		dto.setIsEdit(true);
		model.addAttribute("customer",dto);
		return "/site/accounts/register";
	}
	@PostMapping("cregister")
	public ModelAndView register(ModelMap model,@Valid @ModelAttribute(value = "customer") CustomerDto customerDto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ModelAndView("/site/accounts/register");
		}
		Customer entity = new Customer();
		BeanUtils.copyProperties(customerDto, entity);
		entity.setStatus((short) 0);
		customerService.save(entity);
		session.setAttribute("customer", entity.getPhone());
		session.setAttribute("customerId", entity.getCustomerId());
		return new ModelAndView("/site/content", model);
	}
	@PostMapping("clogin")
	public ModelAndView login(ModelMap model,@Valid @ModelAttribute("customer") CustomerLoginDto customerLoginDto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("/site/accounts/login", model);
		}
		Customer customer = customerService.login(customerLoginDto.getPhone(), customerLoginDto.getPassword());
		if(customer == null) {
			model.addAttribute("message", "Invalid phone or password or Account not Active");
			return new ModelAndView("/site/accounts/login", model);
		}
	
		session.setAttribute("customer", customerLoginDto.getPhone());
		session.setAttribute("customerId", customer.getCustomerId());
		Object ruri = session.getAttribute("redirect-uri");
		if (ruri != null) {
			session.removeAttribute("redirect-uri");
			return new ModelAndView("redirect:" + ruri);
		}
		model.addAttribute("customer", new CustomerLoginDto());
		model.addAttribute("message", "Loging Customer succesfull");
		return new ModelAndView("/site/content", model);
	}
	
	@RequestMapping("clogout")
	public String logout(ModelMap model) {
		model.addAttribute("customer", new CustomerLoginDto());
		CustomerDto dto = new CustomerDto();
		dto.setIsEdit(true);
		model.addAttribute("customerAdd",dto);
		String phone = (String) session.getAttribute("customer");
		if (phone != null) {
			session.removeAttribute("customer");
			session.removeAttribute("customerId");
		}
		model.addAttribute("customer", new CustomerLoginDto());
		return "/site/accounts/login";
	}
	
}
