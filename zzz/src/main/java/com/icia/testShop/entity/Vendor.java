package com.icia.testShop.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vendor {
	private long vendorNo;
	private String name;
	private String tel;
	private long sno;
	private long totalSalesCnt;
	private long totalSalesAmount;
}
