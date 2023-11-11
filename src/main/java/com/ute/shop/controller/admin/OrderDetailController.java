package com.ute.shop.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ute.shop.domain.Order;
import com.ute.shop.domain.OrderDetail;
import com.ute.shop.domain.Product;
import com.ute.shop.service.OrderDetailService;
import com.ute.shop.service.OrderService;
import com.ute.shop.service.ProductService;

@Controller
@RequestMapping("admin/orderdetails")
public class OrderDetailController {
	@Autowired
	ProductService productService;
	@Autowired
	OrderDetailService detailService;
	@Autowired
	OrderService orderService;
	@ModelAttribute("products")
	public List<Product> getProducts() {
		return productService.findAll();
	}

	@GetMapping("edit/{orderId}")
	public ModelAndView edit(ModelMap model, @PathVariable("orderId") Integer orderId) {
		model.addAttribute("orderId", orderId);
		List<OrderDetail> details = detailService.searchByOrderId(orderId);
		if (!details.isEmpty()) {
			model.addAttribute("orderdetails", details);
		}
		return new ModelAndView("admin/orderdetails/addOrEdit", model);
	}

	@GetMapping(value = "edit/{orderId}", params = "update")
	public ModelAndView add(ModelMap model, @RequestParam("productId") Integer productId,
			@PathVariable("orderId") Integer orderId) {
		Order order = new Order();
		order.setOrderId(orderId);

		Product product = new Product();
		product.setProductId(productId);
		List<OrderDetail> details = detailService.searchByOrderIdandProductId(orderId, productId);
		if (!details.isEmpty()) {
			details.get(0).setQuantity(details.get(0).getQuantity() + 1);
			detailService.save(details.get(0));
			model.addAttribute("message", "Add successful product");
			return edit(model, orderId);
		}
		OrderDetail entity = new OrderDetail();
		entity.setOrder(order);
		entity.setProduct(product);
		entity.setUnitPrice(productService.findById(productId).get().getUnitPrice());
		detailService.save(entity);
		model.addAttribute("message", "Add successful product");
		return edit(model, orderId);

	}

	@GetMapping(value = "edit/{orderId}", params = "delete")
	public ModelAndView delete(ModelMap model, @RequestParam("orderDetailId") Integer orderDetailId,
			@PathVariable("orderId") Integer orderId) {
		detailService.deleteById(orderDetailId);
		model.addAttribute("message", "Remove successful product");
		return edit(model, orderId);

	}

	@GetMapping(value = "edit/{orderId}", params = "save")
	public String save(RedirectAttributes model, @PathVariable("orderId") Integer orderId) {
		// detailService.deleteById(orderDetailId);
		Optional<Order> order = orderService.findById(orderId);
		if(order.isPresent()) {
			List<OrderDetail> details = detailService.searchByOrderId(orderId);
			double amount = details.stream()
					.mapToDouble(item -> item.getQuantity() * item.getUnitPrice() * item.getProduct()
					.getDiscount() * 0.01f).sum();
			order.get().setAmount( Math.round(amount * 1000.0) / 1000.0);
			orderService.save(order.get());
		}
		model.addFlashAttribute("message", "Save successful order");
		return "redirect:/admin/orders";

	}

}
