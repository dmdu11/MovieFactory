<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	$(function() {
		var productNo = location.search.substr(11);
		$.ajax({
			url: "/testShop/api/products/" + productNo,
			method:"get",
			success:function(result) {
				$("#info").html(result.info);
			}
		})
	})
</script>
</head>
<body>
	<pre id="info"></pre>
</body>
</html>