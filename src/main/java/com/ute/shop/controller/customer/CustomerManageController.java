package com.ute.shop.controller.customer;

import java.util.List;
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
import com.ute.shop.domain.Order;
import com.ute.shop.model.CustomerDto;
import com.ute.shop.model.ForgotCustomerDto;
import com.ute.shop.service.CustomerService;
import com.ute.shop.service.OrderService;
import com.ute.shop.service.ShoppingCartService;

@Controller
public class CustomerManageController {
	@Autowired
	private HttpSession session;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ShoppingCartService cartService;
	@Autowired 
	private OrderService orderService;
	@ModelAttribute("count")
	int getCount() {
		return cartService.getCount();
	}
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
			List<Order> list = orderService.findByCustomer(entity);
			model.addAttribute("orders", list);
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
	@GetMapping("forgotPassword")
	public String forgotPassword(ModelMap model) {
		ForgotCustomerDto entity = new ForgotCustomerDto();
		model.addAttribute("customer", entity);
		return "site/accounts/forgotPassword";
				
	}
	@PostMapping("forgotPassword")
	public ModelAndView saveforgotPassword(ModelMap model,
			@Valid @ModelAttribute("customer") ForgotCustomerDto customerDto, 
			BindingResult bindingResult) {
		//ForgotCustomerDto entity = new ForgotCustomerDto();'
		if(bindingResult.hasErrors()) {
			return new ModelAndView("site/accounts/forgotPassword");
		}
		Optional<Customer> entity = customerService.findByPhoneAndEmail(customerDto.getPhone(),customerDto.getEmail());
		if(entity.isPresent()) {
			entity.get().setStatus((short) 0);
			entity.get().setPassword(customerDto.getPassword());
			customerService.save(entity.get());
			session.setAttribute("customer", entity.get().getPhone());
			session.setAttribute("customerId", entity.get().getCustomerId());
			model.addAttribute("message","Change your password successful");
			return new ModelAndView( "forward:/",model);
		}
		model.addAttribute("message","Customer not exited");
		return new ModelAndView("site/accounts/forgotPassword",model);
				
	}
	@RequestMapping("/cancelOrder/{orderId}")
	public ModelAndView cancel(ModelMap model,@PathVariable("orderId") Integer orderId) {
		Optional<Order> optional = orderService.findById(orderId);
		if(optional.isPresent()) {
			Order order = optional.get();
			order.setStatus((short) 5);
			orderService.save(order);
			model.addAttribute("message","Cancel order Successful !");
			return new ModelAndView( "forward:/account",model);
		}
		return new ModelAndView( "forward:/account",model);
	}
	
}
