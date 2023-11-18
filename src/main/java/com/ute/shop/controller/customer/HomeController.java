package com.ute.shop.controller.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ute.shop.domain.Category;
import com.ute.shop.domain.Product;
import com.ute.shop.domain.Supplier;
import com.ute.shop.service.CategoryService;
import com.ute.shop.service.ProductService;
import com.ute.shop.service.ShoppingCartService;
import com.ute.shop.service.StorageService;
import com.ute.shop.service.SupplierService;

@Controller
@RequestMapping
public class HomeController {
	@Autowired
	ProductService productService;
	@Autowired
	StorageService storageService; 
	@Autowired
	CategoryService categoryService;
	@Autowired
	SupplierService supplierService;
	@Autowired
	ShoppingCartService cartService;
	
	@GetMapping("products/images/{fileName:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String fileName){
		Resource file = storageService.loadAsResource(fileName);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	@ModelAttribute("listCategory")
	List<Category> getCategories(){
		return categoryService.findAll();
	}
	@ModelAttribute("listSupplier")
	List<Supplier> getSuppliers(){
		return supplierService.findAll();
	}
	
	@RequestMapping("/")
	public String home(ModelMap model) {
		List<Product> products = productService.findAll();
//		return "/site/home/index";
		model.addAttribute("listProduct",products);
		products = productService.findByStatus((short) 3);
		model.addAttribute("listProductBestSell",products);
		products = productService.findByStatus((short) 1);
		model.addAttribute("listProductOnSale",products);
		model.addAttribute("listSupplier",supplierService.findAll());
		model.addAttribute("tittle","New Product");
		return "/site/content";
	}
	@GetMapping("searchByName")
	public String searchByName(ModelMap model, @RequestParam(name = "name",required = false) String name ) {

		List<Product> products = productService.findByNameContaining(name);
		model.addAttribute("listProduct",products);
		model.addAttribute("tittle","Search Product By name");
		model.addAttribute("message","Find " +products.size()+ " products");
		return "/site/home/searchAndFilter";
	}
	@GetMapping("searchByCategory")
	public String searchByCategory(ModelMap model, @RequestParam(name = "categoryId",required = false) Integer categoryId ) {

		Optional<Category> optionalCategory = categoryService.findById(categoryId);
		if(optionalCategory.isPresent()) {
			List<Product> products = productService.findByCategory(optionalCategory.get());
			model.addAttribute("listProduct",products);
			model.addAttribute("tittle","Search Product By Category: " + optionalCategory.get().getName());
			model.addAttribute("message","Find " +products.size()+ " products");
			return "/site/home/searchAndFilter";
		}
		model.addAttribute("message","Not Found Category");
		return "/site/home/searchAndFilter";
	}
	@GetMapping("searchBySupplier")
	public String searchBySupplier(ModelMap model, @RequestParam(name = "supplierId",required = false) Integer supplierId ) {

		Optional<Supplier> optionalSupplier = supplierService.findById(supplierId);
		if(optionalSupplier.isPresent()) {
			List<Product> products = productService.findBySupplier(optionalSupplier.get());
			model.addAttribute("products",products);
			model.addAttribute("listProduct",products);
			model.addAttribute("tittle","Search Product By Supplier: " + optionalSupplier.get().getName());
			model.addAttribute("message","Find " +products.size()+ " products");
			return "/site/home/searchAndFilter";
		}
		model.addAttribute("message","Not Found Supplier");
		return "/site/home/searchAndFilter";
	}
	@GetMapping("searchByPrice")
	public String searchByPrice(ModelMap model, 
			@RequestParam(name = "min",required = false) Double min,
			@RequestParam(name = "max",required = false) Double  max ) {
		if(max == null) {
			max = Double.MAX_VALUE;
		}
		if(min == null || min <= 0 || min > max) {
			min = 0.0;
		}
		
		List<Product> products = productService.findByUnitPriceBetween(min,max );
		model.addAttribute("tittle","Search Product By Price: " + "Min = " + min + " && " + "Max = " + max);
		model.addAttribute("message","Find " +products.size()+ " products");
		model.addAttribute("listProduct",products);
		return "/site/home/searchAndFilter";
	}
	@GetMapping("sort")
	public String sort(ModelMap model, 
			@RequestParam(name = "name",required = false) String name,
			@RequestParam(name = "direction",required = false) String direction) {
		
		List<Product> products = productService.findAll(Sort.by(direction == "asc" ? Direction.ASC : Direction.DESC, name));
		model.addAttribute("listProduct",products);
		model.addAttribute("tittle","Sort Product By: " + name + " Direction: " + direction);
		return "/site/home/searchAndFilter";
	}
	
	
}
