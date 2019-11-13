package com.icia.testShop.service;

import java.io.*;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import com.fasterxml.jackson.databind.*;
import com.icia.testShop.dao.repository.*;
import com.icia.testShop.dto.*;
import com.icia.testShop.entity.*;

@Service
public class ProductService {
	@Autowired
	ProductDao dao;
	@Value("http://localhost:8081/upload")
	private String ckuploadUri;
	@Value("http://localhost:8081/image")
	private String imageUri;
	@Value("d:/data/ckupload")
	private String ckUpload;
	@Value("d:/data/image")
	private String imageFolder;
	public String saveCkImage(MultipartFile upload) throws IllegalStateException, IOException {
		Map<String, String> map	 = new HashMap<String, String>();
		if(upload==null) {
			// 올라오는 사진이 없을 때.
			if(upload.getContentType().toLowerCase().startsWith("image/")) {
				// 업로드한 사진의 타입이 너무 짧거나 image/로 시작할 때?
				String imageName = UUID.randomUUID().toString() +".jpg";
				// 이미지 이름을 랜덤한 글자로 바꾸고 .jpg로 해라.
				File file = new File(ckUpload, imageName);
				// 파일을 ckupload에 이미지네임으로 바꿔라?
				upload.transferTo(file);
				// 업로드에 file로 바꿔라.
				String fileUrl = ckuploadUri + imageName;
				// 파일 주소 = ckuploadUri 에다가 이미지 네임을 붙여라
				map.put("uploaded", "1");
				map.put("fileName", imageName);
				map.put("url", fileUrl);
				// Jackson을 이용해 Json String, Map 변환이라는데 뭔 소리지..?
				// ObjectMapper가 Jackson lobrary를 이용한거임.
				return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(map);
			}
		}
		return null;
		
	}
	
	public Product insert(Product product, MultipartFile image) throws IllegalStateException, IOException {
		String imageName = product.getName() + "jpg";
		File file = new File(imageFolder, imageName);
		image.transferTo(file);
		String fileUrl = ckuploadUri + imageName;
		product.setImage(fileUrl);
		dao.insert(product);
		return dao.findById(product.getProductNo());
		/* 순서
		 * 1. 이미지 이름 만들기 -> product에서 이름가져와 jpg로 
		 * 2. 파일 생성자 (이미지가 들어있는 주소, 이미지이름)
		 * 3. 파일주소 =  업로드한 이미지 주소 + 이미지이름
		 * 4. 이미지를 그 파일주소로 바꾸기.
		 * 5. dao.insert
		 * 6. return 아이디로 찾아서 보여주기.
		 * */
	}
	
	public Product findById(long productNo) {
		return dao.findById(productNo);
	}
	
	public Page findAllByPageNo(int pageno, int pagesize) {
		int count = dao.findCount();
		// pageno	pagesize	startRowNum		endRowNum
		//		1		10			1				10
		//		2		10			11				20
		//		3		10			21				30
		int startRowNum = (pageno-1)*pagesize+1;
		int endRowNum  = pageno*pagesize;
		if(endRowNum >= count)
			endRowNum=count;
		List<Product> products = dao.findAllByPageNo(startRowNum, endRowNum);
		return new Page().builder().pageno(pageno).pagesize(pagesize).totalcount(count).products(products).build();		
	}
	
	// 밑에 대분류페이징이랑 제품명으로 페이징하는거 작성해야함. 일단은 좀있다가.
	// 한다고 해놓고서는 겨우겨우 11월 9일에 시작합니다...
	
	public Page findAllByCategory (long lno, int pagesize, int pageno) {
		int count = dao.findCountByCategory(lno);
		int startRowNum = pagesize*(pageno-1)+1;
		int endRowNum = pagesize*pageno;
		if(count<=endRowNum)
			count=endRowNum;
		List<Product> products = dao.findAllByCategory(lno, startRowNum, endRowNum);
		return new Page().builder().pageno(pageno).pagesize(pagesize).totalcount(count).products(products).build();
	}
	
	public Page findAllByName(String name, int pagesize, int pageno) {
		int count = dao.findCountByName(name);
		int startRowNum = pagesize*(pageno-1)+1;
		int endRowNum = pagesize*pageno;
		if(count<=endRowNum)
			count=endRowNum;
		List<Product> products = dao.findAllByName(name, startRowNum, endRowNum);
		return new Page().builder().pageno(pageno).pagesize(pagesize).totalcount(count).products(products).build();
	}
	
	
	
	
	
	
	
	
	
}