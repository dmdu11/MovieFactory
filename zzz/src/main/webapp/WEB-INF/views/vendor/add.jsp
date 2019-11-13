<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	.dropdown:hover .dropdown-menu {
		display:block;
		margin-top:0;
	}
</style>
<script>/* 화면에 로딩이 끝나면 송신해라 */
	$(function() {
		$.ajax({
			url:"http://192.168.0.96:8081/testShop/api/categories",
			method:"get",
			success:function(result) {
				/*
				// $.each(result, function(idx, large){  }) - 범용
				// result.each(large, function() {  }) = jQuery 객체 전용
				var $li = $(".nav li");
				$.each($li, function(idx, li) {
					//console.log(li);	    // html로 표시됨
					//console.log($(li));   // jQuery로 표시됨 
				}); 
				*/
				
				var $ul = $("#fucku");
				$.each(result, function(idx, large) {
					var $li = $("<li>").attr("class","dropdown").appendTo($ul);
					$("<a>").attr("class","dropdown-toggle").attr("data-toggle","dropdown").attr("href","#").text(large.name).appendTo($li);
					var $ul_sub = $("<ul>").attr("class","dropdown-menu").appendTo($li);
					$.each(large.smallCategories, function(idx, small) {
						var $li_sub = $("<li>").appendTo($ul_sub);
						$("<a>").attr("href","#").text(small.name).appendTo($li_sub);
					});
				});
			}
		})
	});
</script>
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<ul class="nav navbar-nav" id="fucku">
			</ul>
		</div>
	</nav>
	
	<!--
	 <nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<ul class="nav navbar-nav">
				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1</a>
					<ul class="dropdown-menu">
						<li><a href="#">Page 1-1</a></li>
						<li><a href="#">Page 1-2</a></li>
						<li><a href="#">Page 1-3</a></li>
					</ul></li>
				<li><a href="#">Page 2</a></li>
				<li><a href="#">Page 3</a></li>
			</ul>
		</div>
	</nav>
	 -->
</body>
</html>