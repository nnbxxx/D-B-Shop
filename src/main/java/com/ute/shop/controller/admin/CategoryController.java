package com.ute.shop.controller.admin;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ute.shop.domain.Category;
import com.ute.shop.model.CategoryDto;
import com.ute.shop.service.CategoryService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("category", new Category());
		return "admin/categories/addOrEdit";
	}
	@GetMapping("edit/{categoryId}")
	public String edit() {
		return "admin/categories/addOrEdit";
	}
	@GetMapping("delete/{categoryId}")
	public String delete() {
		return "redirect:/admin/categories";
	}
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, CategoryDto categoryDto) {
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
