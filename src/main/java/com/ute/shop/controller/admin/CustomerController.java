package com.ute.shop.controller.admin;

import java.util.List;
import java.util.Optional;

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

import com.ute.shop.domain.Account;
import com.ute.shop.domain.Customer;
import com.ute.shop.model.AccountDto;
import com.ute.shop.model.CustomerDto;
import com.ute.shop.service.CustomerService;

@Controller
@RequestMapping("admin/customers")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	@GetMapping("add")
	public String add(ModelMap model) {
		CustomerDto dto = new CustomerDto();
		dto.setIsEdit(true);
		model.addAttribute("customer",dto);
		return "admin/customers/addOrEdit";
	}
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model,
			@Valid @ModelAttribute("customer") CustomerDto customerDto, 
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ModelAndView("admin/customers/addOrEdit");
		}
		Customer entity = new Customer();
		BeanUtils.copyProperties(customerDto, entity);
		customerService.save(entity);
		model.addAttribute("message", "Customer is Saved !");
		return new ModelAndView("forward:/admin/customers", model);
	}
	@RequestMapping("")
	public String list(ModelMap model) {
		List<Customer> list = customerService.findAll();
		model.addAttribute("customers",list);
		return "admin/customers/list";
	}
	@GetMapping("delete/{customerId}")
	public ModelAndView delete(ModelMap model, @PathVariable("customerId") Integer customerId) {
		
		customerService.deleteById(customerId);
		model.addAttribute("message", "Customer is Deleted !");
		return new ModelAndView("forward:/admin/customers",model);
	}
	@GetMapping("edit/{customerId}")
	public ModelAndView edit(ModelMap model, @PathVariable("customerId") Integer customerId) {
		Optional<Customer> optional = customerService.findById(customerId);
		CustomerDto customerDto = new CustomerDto();
		if(optional.isPresent()) {
			Customer entity = optional.get();
			BeanUtils.copyProperties(entity, customerDto);
			customerDto.setIsEdit(false);
			customerDto.setPassword("");
			model.addAttribute("customer",customerDto);
			System.out.println("Phone: " + customerDto.getPhone());
			return new ModelAndView("admin/customers/addOrEdit",model);
		}
		model.addAttribute("message","customer is not exited");
		return new ModelAndView( "forward:/admin/customers/",model);
	}
}
