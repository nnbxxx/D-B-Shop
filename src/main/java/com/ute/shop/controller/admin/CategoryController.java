package com.ute.shop.controller.admin;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ute.shop.domain.Category;
import com.ute.shop.model.CategoryDto;
import com.ute.shop.service.CategoryService;


@Controller
@RequestMapping("admin/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("category", new CategoryDto());
		return "admin/categories/addOrEdit";
	}
	@GetMapping("edit/{categoryId}")
	public ModelAndView edit(ModelMap model, @PathVariable("categoryId") Integer categoryId) {
		Optional<Category> optional = categoryService.findById(categoryId);
		CategoryDto categoryDto = new CategoryDto();
		if(optional.isPresent()) {
			Category entity = optional.get();
			BeanUtils.copyProperties(entity, categoryDto);
			categoryDto.setIsEdit(true);
			model.addAttribute("category",categoryDto);
			return new ModelAndView("admin/categories/addOrEdit",model);
		}
		model.addAttribute("message","Category is not exited");
		return new ModelAndView( "forward:/admin/categories/",model);
	}
	@GetMapping("delete/{categoryId}")
	public String delete() {
		return "redirect:/admin/categories";
	}
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model,@Valid @ModelAttribute("category") CategoryDto categoryDto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(item -> System.out.println(item));
			return new ModelAndView("admin/categories/addOrEdit");
		}
		Category entity = new Category();
		BeanUtils.copyProperties(categoryDto, entity);
		categoryService.save(entity);
		model.addAttribute("message", "Category is Saved !");
		return new ModelAndView("forward:/admin/categories", model);
	}
	@RequestMapping("")
	public String list(ModelMap model) {
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories",categories);
		return "admin/categories/list";
	}
	@GetMapping("search")
	public String search() {
		return "admin/categories/search";
	}
}
