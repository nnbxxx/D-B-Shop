package com.ute.shop.controller.admin;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ute.shop.domain.Category;
import com.ute.shop.domain.Customer;
import com.ute.shop.domain.Order;
import com.ute.shop.model.CustomerDto;
import com.ute.shop.model.OrderDto;
import com.ute.shop.service.CustomerService;
import com.ute.shop.service.OrderService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Controller
@RequestMapping("admin/orders")
public class OrderControlller {
	@Autowired
	OrderService orderService;
	@Autowired
	CustomerService customerService;
	@ModelAttribute("customers")
	public List<CustomerDto> getCutomers(){
		return customerService.findAll().stream().map(item ->{
			CustomerDto dto = new CustomerDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}
	
	@GetMapping("add")
	public String add(ModelMap model) {
		OrderDto dto = new OrderDto();
		dto.setIsEdit(true);
		model.addAttribute("order",dto);
		return "admin/orders/addOrEdit";
	}
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model,@Valid @ModelAttribute("order")OrderDto orderDto,BindingResult bindingResult ) {
		if(bindingResult.hasErrors()) {
			return new ModelAndView("admin/orders/addOrEdit");
		}
		Optional<Order> entity = orderService.findById(orderDto.getOrderId());
		if(entity.isPresent()) {
			entity.get().setStatus((short) orderDto.getStatus());
			orderService.save(entity.get());
		}
		else 
		{
			Order entityNew = new Order();
			BeanUtils.copyProperties(orderDto, entity);
			Customer customer = new Customer();
			customer.setCustomerId(orderDto.getCustomerId());
			entityNew.setCustomer(customer);
			orderService.save(entityNew);
		}
		model.addAttribute("message", "Order is Saved !");
		return new ModelAndView("forward:/admin/orders", model);
	}
	@RequestMapping("")
	public String list(ModelMap model) {
		List<Order> list = orderService.findAll();
		model.addAttribute("orders", list);
		return "admin/orders/list";
	}
	@GetMapping("delete/{orderId}")
	public ModelAndView delete(ModelMap model, @PathVariable("orderId") Integer orderId) {
		
		orderService.deleteById(orderId);
		model.addAttribute("message", "Order is Deleted !");
		return new ModelAndView("forward:/admin/orders",model);
	}
	@GetMapping("edit/{orderId}")
	public ModelAndView edit(ModelMap model, @PathVariable("orderId") Integer orderId) {
		Optional<Order> optional = orderService.findById(orderId);
		OrderDto orderDto = new OrderDto();
		if(optional.isPresent()) {
			Order entity = optional.get();
			BeanUtils.copyProperties(entity, orderDto);
			orderDto.setIsEdit(false);
			model.addAttribute("order",orderDto);
			return new ModelAndView("admin/orders/addOrEdit",model);
		}
		model.addAttribute("message","order is not exited");
		return new ModelAndView( "forward:/admin/orders/",model);
	} 
	@RequestMapping("searchByDate")
	public String searhByDate(@ModelAttribute("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@DateTimeFormat(pattern = "yyyy-MM-dd") @ModelAttribute("endDate") Date endDate,ModelMap model) {

		List<Order> list = orderService.findByOrderDateBetween(startDate, endDate);
		model.addAttribute("orders", list);
		return "admin/orders/list";
	}
	
	
}
