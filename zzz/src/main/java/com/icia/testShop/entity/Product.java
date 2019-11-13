package com.icia.testShop.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
	private long productNo;
	private long lno;
	private long sno;
	private long vendorNo;
	private String name;
	private long price;
	private long stock;
	private String info;
	private String image;
	private long ratingCnt;
	private long ratingSum;
	private long totalSalesCnt;
	private long totalSalesAmount;
}
