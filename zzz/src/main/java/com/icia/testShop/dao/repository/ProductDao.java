package com.icia.testShop.dao.repository;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.icia.testShop.entity.*;

@Repository
public class ProductDao {
	@Autowired
	private SqlSessionTemplate tpl;
	
	public int insert(Product product) {
		return tpl.insert("productMapper.insert",product);
	}
	
	public Product findById(long productNo) {
		return tpl.selectOne("productMapper.findById",productNo);
	}
	
	public int update(Product product) {
		return tpl.update("productMapper.update",product);
	}
	
	public int updateSalesInfo(long productNo, long count, long amount) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("productNo", productNo);
		map.put("count", count);
		map.put("amount", amount);
		return tpl.update("productMapper.updateSalesInfo",map);
	}
	
	public int updateRating(long productNo, int rating) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productNo", productNo);
		map.put("rating", rating);
		return tpl.update("productMapper.updateRating",map);
	}
	
	public int deleteById(long productNo) {
		return tpl.delete("productMapper.deleteById",productNo);
	}
	
	public int deleteByVendorNo(long vendorNo) {
		return tpl.delete("productMapper.deleteByVendor",vendorNo);
	}
	
	public int findCountByName(String name) {
		return tpl.selectOne("productMapper.findCountByName",name);
	}
	
	public List<Product> findAllByName(String name, int startRowNum, int endRowNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("startRowNum", startRowNum);
		map.put("endRowNum", endRowNum);
		return tpl.selectList("productMapper.findAllByName",map);
	}
	
	public int findCountByCategory (long lno) {
		return tpl.selectOne("productMapper.findCountByCategory",lno);
	}
	
	public List<Product> findAllByCategory(long lno, int startRowNum, int endRowNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lno", lno);
		map.put("startRowNum", startRowNum);
		map.put("endRowNum", endRowNum);
		return tpl.selectList("productMapper.findAllByCategory",map);
	}
	
	public int findCount() {
		return tpl.selectOne("productMapper.findCount");
	}

	public List<Product> findAllByPageNo(int startRowNum, int endRowNum) {
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("startRowNum", startRowNum);
		map.put("endRowNum", endRowNum);
		return tpl.selectList("productMapper.findAllByPageNo", map);
	}
	
}
