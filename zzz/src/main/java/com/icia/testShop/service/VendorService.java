package com.icia.testShop.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.icia.testShop.dao.repository.*;
import com.icia.testShop.entity.*;

@Service
public class VendorService {
	@Autowired
	private VendorDao dao;
	
	// vendorNo를 가져올 때 새로 +1을 해서 가져옴. insert하면 번호가 증가하는거니까.
	// 그 후 vendor를 변경하고 그것은 insert해서 넣음. 근데 왜 return에 findById가 들어감?.
	// controller에서 써주려고 갖고온다는데 ,,,쩝
	// insert한 부분을 확인해주려고,? insert한 것은 몇번이다. 이렇게 알려주려고?
	public Vendor insert(Vendor vendor) {
		long vendorNo = dao.findMaxVendorNo()+1;
		vendor.setVendorNo(vendorNo);
		dao.insert(vendor);
		return dao.findById(vendorNo);
	}
	
	public List<Vendor> findAll(long sno) {
		return dao.findAll(sno);
	}
	
	public Vendor update(Vendor vendor) {
		dao.update(vendor);
		return dao.findById(vendor.getVendorNo());
	}
	// 여기는 왜 return 타입이 null이래.? -> 삭제하면 정보가 없으니까.....래..
	// 왜 void가 아니라 Void인걸까 전에 했던것 같은데
	public Void delete(long vendorNo) {
		dao.delete(vendorNo);
		return null;
	}
	
	
	public Vendor updateSalesInfo(long vendorNo, long totalSalesCnt, long totalSalesAmount) {
		dao.updateSalesInfo(vendorNo, totalSalesCnt, totalSalesAmount);
		return dao.findById(vendorNo);
	}
}
