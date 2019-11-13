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
import com.icia.testShop.entity.*;

import lombok.extern.slf4j.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class VendorDaoTest {
	@Autowired
	private VendorDao dao;
	
	@Transactional
	//@Test
	public void insertAndFindByIdTest() {
		long vendorNo = dao.findMaxVendorNo() + 1;
		Vendor vendor = new Vendor(vendorNo, "테스트 주식회사", "00011112222", 11,0,0);
		dao.insert(vendor);
		Vendor v2 = dao.findById(vendorNo);
		log.info("result", v2);
		assertThat(v2.getVendorNo(), is(vendorNo));
	}
	
	@Transactional
	//@Test
	public void updateAndFindAllAndDeleteTest() {
		long vendorNo = dao.findMaxVendorNo() + 1;
		Vendor vendor = new Vendor(vendorNo, "테스트 주식회사", "00011112222", 11, 0, 0);
		dao.insert(vendor);
		Vendor v2 = new Vendor(vendorNo, "테스트 컴퍼니", "01012345678", 11, 0, 0);
		assertThat(dao.update(vendor), is(1));
		List<Vendor> list = dao.findAll(11);
		assertThat(list.get(0).getSno(), is(11L));
		assertThat(dao.delete(vendorNo), is(1));
	}
	
	@Transactional
	@Test
	public void updateSalesInfoTest() {
		long vendorNo = dao.findMaxVendorNo() + 1;
		Vendor vendor = new Vendor(vendorNo, "테스트 주식회사", "00011112222", 11, 0, 0);
		dao.insert(vendor);
		int result = dao.updateSalesInfo(vendorNo, 10, 100000);
		System.out.println("result : " + result);
		assertThat(result, is(1));
	}
}

