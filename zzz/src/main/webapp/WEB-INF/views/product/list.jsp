<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	// #list에 출력
	function printList(page) {
		var products = page.products;
		var $list = $("#list");
		$.each(products, function(idx, product) {
			// 이미지, 이름, 가격
			var $tr = $("<tr>").appendTo($list);
			var $td = $("<td>").appendTo($tr);
			$("<img>").attr("src", product.image).attr("width","100px").appendTo($td);
			
			$("<td>").text(product.name).appendTo($tr);
			$("<td>").text(product.price).css({
				'color':"red",
				'font-size':"50px"
			}).appendTo($tr);
		});
		printPaging(page);
	}
	
	// ul.pagination에 출력 : page가 13개라고 한다면
	function printPaging(page) {
		// pageno			앞으로		다음으로
		// 1		1~5			X			6
		// 5		1~5			X			6
		// 6		6~10		5			11
		// 10		6~10		5			11
		// 11		11~13		10			X
		
		var pagesPerBlock = 5;
		// 소수점 밑을 떨궈야한다. 소수점 이하 버림
		var blockNo = Math.floor(page.pageno/pagesPerBlock) +1;
		if(page.pageno%pagesPerBlock==0)
			blockNo--;
		
		var startPage = (blockNo-1)*pagesPerBlock +1;
		var endPage = startPage + pagesPerBlock -1;
		
		// 페이지의 개수
		var cntOfPage = Math.floor(page.totalcount/page.pagesize)+1;
		if(page.totalcount%page.pagesize==0)
			cntOfPage--;
		
		// 마지막 페이지는 페이지의 개수보다 클 수 없다.
		if(endPage>cntOfPage)
			endPafe = cntOfPage;
		// 출력
		var $ul = $("#pagination");
		
		var link = "/testShop/product/list?pageno=";
		// 앞으로 버튼 출력
		if(blockNo>1) {
			var $li = $("<li class='previous'>").appendTo($ul);
			$("<a>").attr("href",link+(startPage-1)).text("앞으로").appendTo($li);
		}
		
		for(var i=startPage; i<=endPage; i++) {
			if(page.pageno==i) {
				var $li = $("<li class='active'>").appendTo($ul);
				$("<a>").attr("href",link+i).text(i).appendTo($li);
			}else {
				var $li = $("<li>").appendTo($ul);
				$("<a>").attr("href",link+i).text(i).appendTo($li);
			}
		}
		
		if(endPage<cntOfPage) {
			var $li = $("<li class='next'>").appendTo($ul);
			$("<a>").attr("href",link+(endPage+1)).text("다음으로").appendTo($li);
		}	
	
		
	}

	$(function() {
		// /testShop/product/read?pageno=10 -> location.href=> ?pageno=10 부분.
		var pageno = location.search.substr(8);
		console.log("============================");
		console.log(pageno);
		$.ajax({
			url:"/testShop/api/products/pageno?pageno=" + pageno,
			method:"get",
			success:function(result) {
				printList(result);
			}
		});
	});
</script>
</head>
<body>
	<table id="list">
	</table>
	<div id="paging">
		<ul class="pagination" id="pagination">
		<!-- 
			<li class="previous"><a href="/testShop/product/read?pageno=0">앞으로</a></li>
			<li class="active"><a href="/testShop/product/read?pageno=1">1</a></li>
			<li><a href="/testShop/product/read?pageno=2">2</a></li>
			<li><a href="/testShop/product/read?pageno=3">3</a></li>
			<li><a href="/testShop/product/read?pageno=4">4</a></li>
			<li><a href="/testShop/product/read?pageno=5">5</a></li>
			<li class="next"><a href="/testShop/product/read?pageno=6">다음으로</a></li>
		-->
		</ul>
	</div>
</body>
</html>