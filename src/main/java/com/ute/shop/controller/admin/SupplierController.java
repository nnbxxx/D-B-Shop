package com.ute.shop.controller.admin;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.service.internal.ProvidedService;
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
import com.ute.shop.domain.Supplier;
import com.ute.shop.model.CategoryDto;
import com.ute.shop.model.SupplierDto;
import com.ute.shop.service.SupplierService;

@Controller
@RequestMapping("admin/suppliers")
public class SupplierController {
	@Autowired
	SupplierService supplierService;
	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("supplier", new SupplierDto());
		return "admin/suppliers/addOrEdit";
	}
	@GetMapping("edit/{supplierId}")
	public ModelAndView edit(ModelMap model, @PathVariable("supplierId") Integer supplierId) {
		Optional<Supplier> optional = supplierService.findById(supplierId);
		SupplierDto supplierDto = new SupplierDto();
		if(optional.isPresent()) {
			Supplier entity = optional.get();
			BeanUtils.copyProperties(entity, supplierDto);
			supplierDto.setIsEdit(true);
			model.addAttribute("supplier",supplierDto);
			return new ModelAndView("admin/suppliers/addOrEdit",model);
		}
		model.addAttribute("message","Supplier is not exited");
		return new ModelAndView( "forward:/admin/suppliers/",model);
	}
	@GetMapping("delete/{supplierId}")
	public ModelAndView delete(ModelMap model, @PathVariable("supplierId") Integer supplierId) {
		
		supplierService.deleteById(supplierId);
		model.addAttribute("message", "Suplier is Deleted !");
		return new ModelAndView("forward:/admin/suppliers",model);
	}
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model,@Valid @ModelAttribute("supplier") SupplierDto supplierDto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
//			bindingResult.getAllErrors().forEach(item -> System.out.println(item));
			return new ModelAndView("admin/suppliers/addOrEdit");
		}
		Supplier entity = new Supplier();
		BeanUtils.copyProperties(supplierDto, entity);
		supplierService.save(entity);
		model.addAttribute("message", "Supplier is Saved !");
		return new ModelAndView("forward:/admin/suppliers", model);
	}
	@RequestMapping("")
	public String list(ModelMap model) {
		List<Supplier> suppliers = supplierService.findAll();
		//System.out.println("suppliers = " + suppliers.size());
		model.addAttribute("suppliers",suppliers);
		return "admin/suppliers/list";
	}
}
