package com.icia.testShop.dto;

import java.util.*;

import com.icia.testShop.entity.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Page {
	private int pageno;
	private int pagesize;
	private int totalcount;
	private List<Product> products;
}
