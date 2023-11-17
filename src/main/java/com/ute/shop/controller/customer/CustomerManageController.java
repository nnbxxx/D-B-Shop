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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ute.shop.domain.Customer;
import com.ute.shop.model.CustomerDto;
import com.ute.shop.service.CustomerService;

@Controller
public class CustomerManageController {
	@Autowired
	private HttpSession session;
	@Autowired
	private CustomerService customerService;
	@GetMapping("account")
	public ModelAndView edit(ModelMap model) {
		Integer customerId = (Integer) session.getAttribute("customerId");
		if(customerId == null) {
			return new ModelAndView( "forward:/cregister",model);
		}
		Optional<Customer> optional = customerService.findById(customerId);
		CustomerDto customerDto = new CustomerDto();
		if(optional.isPresent()) {
			Customer entity = optional.get();
			BeanUtils.copyProperties(entity, customerDto);
			customerDto.setPassword("");
			model.addAttribute("customer",customerDto);
			return new ModelAndView("site/accounts/manageAccount",model);
		}
		return new ModelAndView( "forward:/cregister",model);
	}
	@PostMapping("account")
	public ModelAndView save(ModelMap model,
		@Valid @ModelAttribute("customer") CustomerDto customerDto, BindingResult bindingResult) {
		Integer customerId = (Integer) session.getAttribute("customerId");
		if(bindingResult.hasErrors()) {
			return new ModelAndView("site/accounts/manageAccount");
		}
		if(customerId == null) {
			return new ModelAndView( "forward:/cregister",model);
		}
		Optional<Customer> optional = customerService.findById(customerId);
		if(optional.isPresent()) {
			Customer entity = optional.get();
			BeanUtils.copyProperties(entity, customerDto);
			customerService.save(entity);
			model.addAttribute("message", "Customer is Saved !");
		}
		return new ModelAndView( "forward:/",model);
	}
	
}
