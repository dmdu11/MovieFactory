package com.icia.testShop.controller;

import javax.validation.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import com.icia.testShop.entity.*;
import com.icia.testShop.service.*;

import lombok.*;
import lombok.extern.slf4j.*;

@Slf4j
@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class VendorController {
	@Autowired
	private VendorService service;
	
	@PostMapping("/vendors")
	public ResponseEntity<?> insert(@ModelAttribute @Valid Vendor vendor, BindingResult results) throws BindException{
		if(results.hasErrors())
			throw new BindException(results);
		return ResponseEntity.ok(service.insert(vendor));
	}

	// null인지 아닌지 구분하려면 null을 포함하는 '객체'로 주어야함
	@GetMapping("/vendors")
	public ResponseEntity<?> findAll(@NonNull Long sno) {
		return ResponseEntity.ok(service.findAll(sno));
	}

	@PutMapping("/vendors")
	public ResponseEntity<?> update(@ModelAttribute @Valid Vendor vendor, BindingResult results) throws BindException{
		if(results.hasErrors())
			throw new BindException(results);
		return ResponseEntity.ok(service.update(vendor));
	}
	
	// 나는 파라미터 부분을 @PathVariable로 줬움 왜 @NonNull인가?
	// null인지 아닌지 구분하려면 null을 포함하는 '객체'로 주어야함
	@DeleteMapping("/vendors")
	public ResponseEntity<?> delete(@NonNull Long vendorNo){
		return ResponseEntity.ok(service.delete(vendorNo));
	}
	
	@PutMapping("/vendors/sales_info")
	public ResponseEntity<?> updateSalesInfo(@RequestParam long vendorNo, @RequestParam long totalSalesCnt, @RequestParam long totalSalesAmount){
		return ResponseEntity.ok(service.updateSalesInfo(vendorNo, totalSalesCnt, totalSalesAmount));
	}
	
}
