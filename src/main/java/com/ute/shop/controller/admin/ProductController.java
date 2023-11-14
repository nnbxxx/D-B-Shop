package com.ute.shop.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ute.shop.domain.Category;
import com.ute.shop.domain.Product;
import com.ute.shop.domain.Supplier;
import com.ute.shop.model.CategoryDto;
import com.ute.shop.model.ProductDto;
import com.ute.shop.model.SupplierDto;
import com.ute.shop.service.CategoryService;
import com.ute.shop.service.ProductService;
import com.ute.shop.service.StorageService;
import com.ute.shop.service.SupplierService;


@Controller
@RequestMapping("admin/products")
public class ProductController {
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	StorageService storageService; 
	@Autowired
	SupplierService supplierService;
	@ModelAttribute("categories")
	public List<CategoryDto> getCategories(){
		return categoryService.findAll().stream().map(item->{
			CategoryDto dto = new CategoryDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}
	@ModelAttribute("suppliers")
	public List<SupplierDto> getSuppliers(){
		return supplierService.findAll().stream().map(item->{
			SupplierDto dto = new SupplierDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}
	
	@GetMapping("add")
	public String add(Model model) {
		ProductDto dto = new ProductDto();
		dto.setIsEdit(true);
		model.addAttribute("product", dto);
		return "admin/products/addOrEdit";
	}
	@GetMapping("edit/{productId}")
	public ModelAndView edit(ModelMap model, @PathVariable("productId") Integer productId) {
		Optional<Product> optional = productService.findById(productId);
		ProductDto productDto = new ProductDto();
		if(optional.isPresent()) {
			Product entity = optional.get();
			BeanUtils.copyProperties(entity, productDto);
			productDto.setCategoryId(entity.getCategory().getCategoryId());
			productDto.setIsEdit(false);
			model.addAttribute("product",productDto);
			return new ModelAndView("admin/products/addOrEdit",model);
		}
		model.addAttribute("message","Product is not exited");
		return new ModelAndView( "forward:/admin/products/",model);
	}
	@GetMapping("/images/{fileName:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String fileName){
		Resource file = storageService.loadAsResource(fileName);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	
	@GetMapping("delete/{productId}")
	public ModelAndView delete(ModelMap model, @PathVariable("productId") Integer productId) throws IOException {
		Optional<Product> optional = productService.findById(productId);
		if(optional.isPresent()) {
			if(!StringUtils.isEmpty(optional.get().getImage()) ) {
				storageService.delete(optional.get().getImage());
			}
			productService.deleteById(productId);
			model.addAttribute("message", "Product is Deleted !");
		}
		else {
			model.addAttribute("message", "Product is not Found !");
		}
		
		return new ModelAndView("forward:/admin/products",model);
	}
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model,@Valid @ModelAttribute("product") ProductDto productDto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ModelAndView("admin/products/addOrEdit");
		}
		if(productDto.getProductId() != null) {
			Optional<Product> entity = productService.findById(productDto.getProductId());
			if(entity.isPresent()) {
				BeanUtils.copyProperties(productDto, entity);
				Category category = new Category();
				Supplier supplier = new Supplier();
				category.setCategoryId(productDto.getCategoryId());
				supplier.setSupplierId(productDto.getSupplierId());
				entity.get().setCategory(category);
				entity.get().setSupplier(supplier);
				
				if(!productDto.getImageFile().isEmpty()) {
					UUID uuid = UUID.randomUUID();
					String uuString = uuid.toString();
					entity.get().setImage(storageService.getStoredFileName(productDto.getImageFile(), uuString));
					storageService.store(productDto.getImageFile(), entity.get().getImage());
				}
				productService.save(entity.get());
			}
		}
		else {
			Product entityNew = new Product();
			BeanUtils.copyProperties(productDto, entityNew);
			Category category = new Category();
			Supplier supplier = new Supplier();
			category.setCategoryId(productDto.getCategoryId());
			supplier.setSupplierId(productDto.getSupplierId());
			entityNew.setSupplier(supplier);
			entityNew.setCategory(category);
			if(!productDto.getImageFile().isEmpty()) {
				UUID uuid = UUID.randomUUID();
				String uuString = uuid.toString();
				entityNew.setImage(storageService.getStoredFileName(productDto.getImageFile(), uuString));
				storageService.store(productDto.getImageFile(), entityNew.getImage());
			}
			productService.save(entityNew);
		}

		model.addAttribute("message", "Product is Saved !");
		return new ModelAndView("forward:/admin/products", model);
	}
	@RequestMapping("")
	public String list(ModelMap model) {
		List<Product> products = productService.findAll();
		model.addAttribute("products",products);
		model.addAttribute("totalPage",1);
		model.addAttribute("currentPage",0);
		model.addAttribute("pageSize",999999);
		return "admin/products/list";
	}
	@GetMapping("searchByCategory")
	public String searchByCategory(ModelMap model, @RequestParam(name = "categoryId",required = false) Integer categoryId ) {

		Optional<Category> optionalCategory = categoryService.findById(categoryId);
		if(optionalCategory.isPresent()) {
			List<Product> products = productService.findByCategory(optionalCategory.get());
			model.addAttribute("products",products);
		}
		return "admin/products/list";
	}
	@GetMapping("searchBySupplier")
	public String searchBySupplier(ModelMap model, @RequestParam(name = "supplierId",required = false) Integer supplierId ) {

		Optional<Supplier> optionalSupplier = supplierService.findById(supplierId);
		if(optionalSupplier.isPresent()) {
			List<Product> products = productService.findBySupplier(optionalSupplier.get());
			model.addAttribute("products",products);
		}
		return "admin/products/list";
	}
	@GetMapping("searchByStatus")
	public String searchByStatus(ModelMap model, @RequestParam(name = "statusId",required = false) Short statusId ) {

		List<Product> products = productService.findByStatus(statusId);
		model.addAttribute("products",products);
		return "admin/products/list";
	}
	@GetMapping("searchByName")
	public String searchByName(ModelMap model, @RequestParam(name = "name",required = false) String name ) {

		List<Product> products = productService.findByNameContaining(name);
		model.addAttribute("products",products);
		return "admin/products/list";
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
		model.addAttribute("products",products);
		return "admin/products/list";
	}
	@GetMapping("sort")
	public String sort(ModelMap model, 
			@RequestParam(name = "name",required = false) String name,
			@RequestParam(name = "direction",required = false) String direction) {
		List<Product> products = productService.findAll(Sort.by(direction == "asc" ? Direction.ASC : Direction.DESC, name));
		model.addAttribute("products",products);
		return "admin/products/list";
	}
	@GetMapping("paginate")
	public String paginate(ModelMap model, 
			@RequestParam(name = "page",required = false) Integer page,
			@RequestParam(name = "pagesize",required = false) Integer pagesize) {
		Pageable pageable = PageRequest.of(page,pagesize);
		Page<Product> products = productService.findAll(pageable);
		model.addAttribute("products",products);
		model.addAttribute("currentPage",page);
		model.addAttribute("pageSize",pagesize);
		model.addAttribute("totalPage",products.getTotalPages());
		return "admin/products/list";
	}

}
