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

import com.icia.testShop.service.*;

import lombok.extern.slf4j.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class CategoryServiceTest {
	@Autowired
	private CategoryService service;
	
	// DAO의 메소드와 1:1로 대응하는 서비스 메소드는 테스트할 필요가 없다
	@Transactional
	//@Test
	public void insertLargeCategoryTest() {
		Map map = service.insertLargeCategory("테스트 테스트");
		log.info("result", map);
		assertThat(map, is(notNullValue()));
	}
	
	@Transactional
	//@Test
	public void insertSmallCategoryTest() {
		Map map = service.insertSmallCategory("테스트 테스트", 1);
		log.info("result", map);
		assertThat(map, is(notNullValue()));
	}
}










