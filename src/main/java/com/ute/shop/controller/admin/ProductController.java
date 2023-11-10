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
import com.ute.shop.model.CategoryDto;
import com.ute.shop.model.ProductDto;
import com.ute.shop.service.CategoryService;
import com.ute.shop.service.ProductService;
import com.ute.shop.service.StorageService;


@Controller
@RequestMapping("admin/products")
public class ProductController {
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	StorageService storageService; 
	
	@ModelAttribute("categories")
	public List<CategoryDto> getCategories(){
		return categoryService.findAll().stream().map(item->{
			CategoryDto dto = new CategoryDto();
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
			productDto.setIsEdit(true);
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
		Product entity = new Product();
		BeanUtils.copyProperties(productDto, entity);
		
		Category category = new Category();
		category.setCategoryId(productDto.getCategoryId());
		entity.setCategory(category);
		
		if(!productDto.getImageFile().isEmpty()) {
			UUID uuid = UUID.randomUUID();
			String uuString = uuid.toString();
			entity.setImage(storageService.getStoredFileName(productDto.getImageFile(), uuString));
			storageService.store(productDto.getImageFile(), entity.getImage());
		}
		
		productService.save(entity);
		model.addAttribute("message", "Product is Saved !");
		return new ModelAndView("forward:/admin/products", model);
	}
	@RequestMapping("")
	public String list(ModelMap model) {
		List<Product> products = productService.findAll();
		model.addAttribute("products",products);
		return "admin/products/list";
	}
	@GetMapping("search")
	public String search(ModelMap model, @RequestParam(name = "name",required = false) String name ) {
		List<Category> list = null;
		if(StringUtils.hasText(name)) {
			list = categoryService.findByNameContaining(name);
		}
		else {
			list = categoryService.findAll();
		}
		model.addAttribute("products",list);
		return "admin/products/search";
	}
	@GetMapping("searchpaginated")
	public String search(ModelMap model, @RequestParam(name = "name",required = false) String name,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size
			) {
		
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(currentPage, pageSize,Sort.by("name"));
		Page<Category> resultPage = null;
		
		if(StringUtils.hasText(name)) {
			resultPage = categoryService.findByNameContaining(name,pageable);
			model.addAttribute("name",name);
		}
		else {
			resultPage = categoryService.findAll(pageable);
		}
		int totalPages = resultPage.getTotalPages();
		if(totalPages > 0) {
			int start = Math.max(1, currentPage-2);
			int end = Math.min(totalPages, currentPage+2);
			if(totalPages > 5) {
				if(end == totalPages) {
					start = end - 5;
				}
				else if(start == 1) {
					end = start + 5;
				}
					
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("categoryPage",resultPage);
		return "admin/products/searchpaginated";
	}
}
