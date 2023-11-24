package com.ute.shop.controller.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.ute.shop.model.ProductDto;
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
	
	@ModelAttribute("listCategory")
	List<Category> getCategories(){
		return categoryService.findAll();
	}
	@ModelAttribute("listSupplier")
	List<Supplier> getSuppliers(){
		return supplierService.findAll();
	}
	@ModelAttribute("count")
	int getCount() {
		return cartService.getCount();
	}
	@ModelAttribute("listProductBestSell")
	List<Product> getlistProductBestSell(){
		return productService.findByStatus((short) 3);
	}
	@ModelAttribute("listProductOnSale")
	List<Product> getlistProductOnSell(){
		return productService.findByStatus((short) 1);
	}
	@RequestMapping("/")
	public String home(ModelMap model, @RequestParam(name = "page",required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "pagesize",required = false, defaultValue = "12") Integer pagesize) {
		Pageable pageable = PageRequest.of(page,pagesize);
		Page<Product> products = productService.findAll(pageable);
		model.addAttribute("listProduct",products);
		model.addAttribute("currentPage",page);
		model.addAttribute("pageSize",pagesize);
		model.addAttribute("totalPage",products.getTotalPages());
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
	@GetMapping("/viewProduct/{productId}")
	public String viewProduct(ModelMap model,@PathVariable("productId") Integer productId) {
		Optional<Product> optional = productService.findById(productId);
		ProductDto productDto = new ProductDto();
		if(optional.isPresent()) {
			Product entity = optional.get();
			BeanUtils.copyProperties(entity, productDto);
			productDto.setCategoryId(entity.getCategory().getCategoryId());
			productDto.setIsEdit(false);
			model.addAttribute("product",productDto);
			List<Product> products = productService.findByCategory(optional.get().getCategory());
			model.addAttribute("listProduct",products);
		}
		return "/site/home/Detail";
	}
	
	
	
}
