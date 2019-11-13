package com.icia.testShop.dao.repository;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.icia.testShop.entity.*;

@Repository
public class VendorDao {
	@Autowired
	private SqlSessionTemplate tpl;
	
	public long findMaxVendorNo() {
		return tpl.selectOne("vendorMapper.findMaxVendorNo");
	}
	
	public int insert(Vendor vendor) {
		return tpl.insert("vendorMapper.insert",vendor);
	}
	
	public Vendor findById(long vendorNo) {
		return tpl.selectOne("vendorMapper.findById", vendorNo);
	}
	
	public List<Vendor> findAll(long sno) {
		return tpl.selectList("vendorMapper.findAll",sno);
	}
	
	public int update(Vendor vendor) {
		return tpl.update("vendorMapper.update", vendor);
	}
	
	public int delete(long vendorNo) {
		return tpl.delete("vendorMapper.delete", vendorNo);
	}
	
	public int updateSalesInfo(long vendorNo, long totalSalesCnt, long totalSalesAmount) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("vendorNo", vendorNo);
		map.put("totalSalesCnt", totalSalesCnt);
		map.put("totalSalesAmount", totalSalesAmount);
		return tpl.update("vendorMapper.updateSalesInfo",map);
	}
	
	
}
