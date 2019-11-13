package com.icia.testShop;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;
import org.springframework.transaction.annotation.*;

import com.icia.testShop.dao.repository.*;

import lombok.extern.slf4j.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class CategoryDaoTest {
	@Autowired
	private CategoryDao dao;
	
	//@Test
	public void findAllLargeCategoryTest() {
		List<Map> result = dao.findAllLargeCategory();
		log.info("result", result);
		assertThat( result, is(notNullValue()));
	}
	
	//@Test
	public void findAllSmallCategoryTest() {
		List<Map> result = dao.findAllSmallCategory(1);
		log.info("result", result);
		assertThat( result, is(notNullValue()));
	}
	
	// 테스트에 @Transactional을 사용하면 무조건 rollback
	@Transactional
	//@Test
	public void insertLargeCategoryTest() {
		long newLno = dao.findMaxLno() + 1;
		int result = dao.insertLargeCategory(newLno, "테스트 테스트");
		assertThat(result, is(1));
		Map large = dao.findByLno(newLno);
		log.info("result", large);
	}
	
	@Transactional
	//@Test
	public void insertSmallCategoryTest() {
		long newSno = dao.findMaxSno() + 1;
		int result = dao.insertSmallCategory(newSno, "테스트 테스트", 1);
		assertThat(result, is(1));
		Map small = dao.findBySno(newSno);
		log.info("result", small);
	}
	
	//@Test
	public void findAllTest() {
		List<Map> result = dao.findAll();
		System.out.println(result);
		assertThat(result, is(notNullValue()));
	}
}
