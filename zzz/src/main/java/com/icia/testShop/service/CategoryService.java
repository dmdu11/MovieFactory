package com.icia.testShop.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.icia.testShop.dao.repository.*;

@Service
public class CategoryService {
	@Autowired
	private CategoryDao dao;
	
	public List<Map> findAllLargeCategory() {
		return dao.findAllLargeCategory();
	}
	
	public List<Map> findAllSmallCategory(long lno) {
		return dao.findAllSmallCategory(lno);
	}
	
	public Map insertLargeCategory(String name) {
		long lno = dao.findMaxLno()+1;
		dao.insertLargeCategory(lno, name);
		return dao.findByLno(lno);
	}
	
	public Map insertSmallCategory(String name, long lno) {
		long sno = dao.findMaxSno()+1;
		dao.insertSmallCategory(sno, name, lno);
		return dao.findBySno(sno);
	}

	public List<Map> findAll() {
		return dao.findAll();
	}
}
