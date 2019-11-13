package com.icia.testShop.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.icia.testShop.service.*;

import lombok.*;
import lombok.extern.slf4j.*;

@Slf4j
@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class CategoryController {
	@Autowired
	private CategoryService service;
	
	@GetMapping("/categories/large")
	public ResponseEntity<?> findAllLargeCategory() {
		return ResponseEntity.ok(service.findAllLargeCategory());
	}
	
	@GetMapping("/categories/small")
	public ResponseEntity<?> findAllSmallCategory(@NonNull Long lno) {
		return ResponseEntity.ok(service.findAllSmallCategory(lno));
	}
	
	@PostMapping("/categories/large")
	public ResponseEntity<?> insertLargeCategory(@NonNull String name) {
		return ResponseEntity.ok(service.insertLargeCategory(name));
	}
	
	@PostMapping("/categories/small")
	public ResponseEntity<?> insertSmallCategory(@NonNull Long lno, @NonNull String name) {
		return ResponseEntity.ok(service.insertSmallCategory(name, lno));
	}
	
	@GetMapping("/categories")
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
}
