package com.ute.shop.controller.customer;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ute.shop.domain.Customer;
import com.ute.shop.model.CustomerDto;
import com.ute.shop.service.CustomerService;
import com.ute.shop.service.ShoppingCartService;
@Controller
public class CustomerOrderController {
	@Autowired
	private HttpSession session;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ShoppingCartService cartService;
	@GetMapping("order")
	public ModelAndView orderPage(ModelMap model) {
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
			return new ModelAndView("site/accounts/order",model);
		}
		return new ModelAndView( "forward:/cregister",model);
	}
	@GetMapping("addToCart/{productId}")
	public String add(RedirectAttributes model,
			@PathVariable("productId") Integer productId) {
		cartService.add(productId);
		model.addFlashAttribute("message", "Add "+ productId +" to Cart Success");
		System.out.println("Count cart = " + cartService.getCount());
		return "redirect:/";
	}
	
}
