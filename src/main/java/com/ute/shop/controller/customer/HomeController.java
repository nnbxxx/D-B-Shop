package com.ute.shop.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ute.shop.domain.Product;
import com.ute.shop.service.ProductService;
import com.ute.shop.service.StorageService;

@Controller
@RequestMapping
public class HomeController {
	@Autowired
	ProductService productService;
	@Autowired
	StorageService storageService; 
	
	@GetMapping("products/images/{fileName:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String fileName){
		Resource file = storageService.loadAsResource(fileName);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	
	@RequestMapping("/")
	public String home(ModelMap model) {
		List<Product> products = productService.findAll();
//		return "/site/home/index";
		model.addAttribute("listProduct",products);
		model.addAttribute("tittle","New Product");
		return "/site/content";
	}
	@GetMapping("searchByName")
	public String searchByName(ModelMap model, @RequestParam(name = "name",required = false) String name ) {

		List<Product> products = productService.findByNameContaining(name);
		model.addAttribute("listProduct",products);
		model.addAttribute("tittle","Search Product By name");
		model.addAttribute("message","Find " +products.size()+ " products");
		return "/site/home/searchByName";
	}
	@RequestMapping("/checkout")
	public String checkout() {
		return "/site/accounts/checkout";
	}
	
}
