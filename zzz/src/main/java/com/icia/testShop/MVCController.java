package com.icia.testShop;

import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class MVCController {
	@GetMapping("/vendor/add")
	public String addVendor(Model model) {
		model.addAttribute("viewName", "vendor/add.jsp");
		return "main";
	}
	@GetMapping("/product/add")
	public String addProduct(Model model) {
		model.addAttribute("viewName", "product/add.jsp");
		return "main";
	}
	@GetMapping("/product/read")
	public String readProduct(Model model) {
		model.addAttribute("viewName", "product/read.jsp");
		return "main";
	}
	@GetMapping("/product/list")
	public String listProduct(Model model) {
		model.addAttribute("viewName", "product/list.jsp");
		return "main";
	}
}
