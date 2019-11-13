package com.icia.testShop.controller;

import java.io.*;
import java.net.*;

import javax.validation.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import org.springframework.web.util.*;

import com.icia.testShop.entity.*;
import com.icia.testShop.service.*;

import lombok.*;
import lombok.extern.slf4j.*;

@Slf4j
@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class ProductController {
	@Autowired
	private ProductService service;
	// slf4j -> log.info 사용하려고 쓰는거임(지금은 안써서 노란색줄 그어짐)
	// 남들 보여줄 때 sysout사용하지마셈 log.info 써야함.
	// CrossOrigin -> csrf 누구나 접근할 수 있다.
	
	@PostMapping("/ckupload")
	public ResponseEntity<?> saveCkImage(MultipartFile upload) throws IllegalStateException, IOException {
		System.out.println("===========================");
		System.out.println(upload);
		return ResponseEntity.ok(service.saveCkImage(upload));
	}
	
	// 문제 잇으면 controllerAdvice로 떠넘기기 문제 없으면 service에 저장
	@PostMapping("/products")
	public ResponseEntity<?> insert(@ModelAttribute @Valid Product product, BindingResult results, MultipartFile sajin) throws BindException, IllegalStateException, IOException {
		if(results.hasErrors())
			throw new BindException(results);
		Product result = service.insert(product, sajin);
		
		// 추가했을 때의 Http응답 코드는 201(HttpStatus.CREATED)이 기본이다 (200이 아니라 원래는 201임->created 인데 얘는 주소가 있어야함 따라서 주소랑 입력한 결과 출력한거임)
		// 이 경우 작성한 글 또는 제품을 읽을 수 있는 경로를 헤더에 추가해서 응답한다
		// 응답 형식을 프론트랑 맞춰줘야한대
		
		// UriComponentsBuilder를 이용해 /exampleShop/product/read?productNo=?? 형식의 주소 생성(있어보일라고 쓴거라네 낄낄)queryParam : 뒤에 붙여주려고 쓴것
		URI location = UriComponentsBuilder.newInstance().path("/testShop/product/read").
				queryParam("productNo", product.getProductNo()).build().toUri();
		return ResponseEntity.created(location).body(result); //created(location)은 header에 추가, body(result)는 body에 추가됨
	}
	
	@GetMapping("/products/{productNo}")
	public ResponseEntity<?> findById(@PathVariable long productNo) {
		return ResponseEntity.ok(service.findById(productNo));
	}
	
	@GetMapping("/products/pageno")
	public ResponseEntity<?> findAllByPageNo(@RequestParam(defaultValue="1") int pageno, @RequestParam(defaultValue="10") int pagesize) {
		return ResponseEntity.ok(service.findAllByPageNo(pageno, pagesize));
	}
	

	@GetMapping("/products/category")
	public ResponseEntity<?> findAllByCategory(@NonNull long lno, @RequestParam(defaultValue = "1")int pageno, @RequestParam(defaultValue = "10") int pagesize){
		return ResponseEntity.ok(service.findAllByCategory(lno, pageno, pagesize));
	}
	

	@GetMapping("/products/name")
	public ResponseEntity<?> findAllByName(@NonNull String name, @RequestParam(defaultValue = "1")int pageno, @RequestParam(defaultValue = "10") int pagesize){
		return ResponseEntity.ok(service.findAllByName(name, pageno, pagesize));
	}
}
