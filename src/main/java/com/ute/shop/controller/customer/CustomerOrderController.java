package com.ute.shop.controller.customer;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ute.shop.domain.Customer;
import com.ute.shop.domain.Order;
import com.ute.shop.domain.OrderDetail;
import com.ute.shop.domain.Product;
import com.ute.shop.model.CustomerDto;
import com.ute.shop.model.OrderDto;
import com.ute.shop.service.CustomerService;
import com.ute.shop.service.OrderDetailService;
import com.ute.shop.service.OrderService;
import com.ute.shop.service.ShoppingCartService;
import com.ute.shop.service.StorageService;
@Controller
public class CustomerOrderController {
	@Autowired
	private HttpSession session;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ShoppingCartService cartService;
	@Autowired 
	private StorageService storageService;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderDetailService detailService;
	@ModelAttribute("count")
	int getCount() {
		return cartService.getCount();
	}
	@ModelAttribute("amount")
	double getAmount() {
		return cartService.getAmount();
	}
	@GetMapping("products/images/{fileName:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String fileName){
		Resource file = storageService.loadAsResource(fileName);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
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
			model.addAttribute("listCart", cartService.getItems().toArray());
			return new ModelAndView("site/accounts/order",model);
		}
		return new ModelAndView( "forward:/cregister",model);
	}
	@GetMapping("addToCart/{productId}")
	public String add(RedirectAttributes model,
			@PathVariable("productId") Integer productId) {
		cartService.add(productId);
		model.addFlashAttribute("message", "Add "+ productId +" to Cart Success");
		return "redirect:/";
	}
	@GetMapping("updateToCart/{productId}/{quantity}")
	public String update(RedirectAttributes model,
			@PathVariable("productId") Integer productId,
			@PathVariable("quantity")Integer quantity) {
		cartService.update(productId,quantity);
		return "redirect:/order";
	}
	
	@GetMapping("clearCart")
	public String clear() {
		cartService.clear();
		return "redirect:/order";
	}
	@GetMapping("removeToCart/{productId}")
	public String remove(@PathVariable(value =  "productId") Integer productId) {
		cartService.remove(productId);
		return "redirect:/order";
	}
	@GetMapping("checkout")
	public String checkout(RedirectAttributes model,@ModelAttribute("address")String address) {

		if(cartService.getCount() <= 0) {
			model.addFlashAttribute("message", "Not product to check out !");
			return "redirect:/";
		}
		Integer customerId = (Integer) session.getAttribute("customerId");
		Order order = new Order();
		order.setAddress(address);
		order.setStatus((short) 0);
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		order.setCustomer(customer);
		order = orderService.save(order);
		order.setAmount(cartService.getAmount());
		int orderId = order.getOrderId();
		cartService.getItems().stream().forEach(item -> {
			OrderDetail orderDetail = new OrderDetail();
			Product product = new Product();
			Order order1 = new Order();
			order1.setOrderId(orderId);
			product.setProductId(item.getProductId());
			orderDetail.setQuantity(item.getQuantity());
			orderDetail.setUnitPrice(item.getUnitPrice());
			orderDetail.setProduct(product);
			orderDetail.setOrder(order1);
			detailService.save(orderDetail);
		});
		
		model.addFlashAttribute("message", "Check Out Successful !");
		cartService.clear();
		return "redirect:/";
	}
	
}
