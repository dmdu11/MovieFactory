package com.icia.testShop.dao.repository;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Repository
public class CategoryDao {
	@Autowired
	private SqlSessionTemplate tpl;
	
	public List<Map> findAllLargeCategory() {
		return tpl.selectList("categoryMapper.findAllLargeCategory");
	}
	
	public List<Map> findAllSmallCategory(long lno) {
		return tpl.selectList("categoryMapper.findAllSmallCategory",lno);
	}

	public long findMaxLno() {
		return tpl.selectOne("categoryMapper.findMaxLno");
	}
	
	public int insertLargeCategory(long lno, String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lno", lno);
		map.put("name",name);
		return tpl.insert("categoryMapper.insertLargeCategory",map);
	}
	
	public Map findByLno(long lno) {
		return tpl.selectOne("categoryMapper.findByLno",lno);
	}

	public long findMaxSno() {
		return tpl.selectOne("categoryMapper.findMaxSno");
	}
	
	public int insertSmallCategory(long sno, String name, long lno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sno", sno);
		map.put("name", name);
		map.put("lno", lno);
		return tpl.insert("categoryMapper.insertSmallCategory",map);
	}
	
	public Map findBySno(long sno) {
		return tpl.selectOne("categoryMapper.findBySno",sno);
	}

	public List<Map> findAll() {
		return tpl.selectList("categoryMapper.findAll");
	}
}
