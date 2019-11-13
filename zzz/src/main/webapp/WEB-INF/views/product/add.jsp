<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/testShop/ckeditor/ckeditor.js"></script>
<script>
	function getLargeCategory() {
		$.ajax({
			url: '/testShop/api/categories/large',				
			method: "get",
			success: function(result) {
				$("#large").empty();
				$('<option selected="selected" disabled="disabled">### 선택하세요 ###</option>').appendTo("#large");
				$.each(result, function(idx, large) {
					$("<option>").appendTo($("#large")).attr("value", large.LNO).text(large.NAME);
				});
			}
		});
	}
	
	function getSmallCategory(lno) {
		$.ajax({
			url: '/testShop/api/categories/small?lno=' + lno,				
			method: "get",
			success: function(result) {
				$("#small").empty();
				$.each(result, function(idx, small) {
					$("<option>").appendTo($("#small")).attr("value", small.SNO).text(small.NAME);
				});
				getVendor($("#small").val());
			}
		});
	}
	
	function getVendor(sno) {
		$.ajax({
			url: '/testShop/api/vendors?sno=' + sno,				
			method: "get",
			success: function(result) {
				console.log(result);
				$("#vendor").empty();
				$.each(result, function(idx, vendor) {
					$("<option>").appendTo($("#vendor")).attr("value", vendor.vendorNo).text(vendor.name);
				});
			}
		});
	}
	
	$(function() {
		getLargeCategory();
		
		$("#large").on("click change", function() {
			var lno = $(this).val();
			getSmallCategory(lno);
		});
		
		$("#small").on("click change", function() {
			var sno = $(this).val();
			getVendor(sno);
		});
		
		var ck = CKEDITOR.replace("info", {
			filebrowserUploadUrl: '<c:url value="/api/ckupload" />'
		});
		
		$("#write").on("click", function() {
			var formData = new FormData();
			formData.append("lno", $("#large").val());
			formData.append("sno", $("#small").val());
			formData.append("vendorNo", $("#vendor").val());
			formData.append("name", $("#name").val());
			formData.append("price", $("#price").val());
			formData.append("stock", $("#stock").val());
			formData.append("info", CKEDITOR.instances['info'].getData());
			formData.append("sajin", document.getElementById('sajin').files[0]);
			for (var pair of formData.entries()) {
			    console.log(pair[0]+ ', ' + pair[1]); 
			}
			$.ajax({
				url:'/testShop/api/products',
				data: formData,
				method:"post",
				processData: false,
		        contentType: false,
				success:function(result, textStatus, request) {
					location.href = request.getResponseHeader('location');
				}, error:function(xhr) {
					console.log(xhr.responseText);
				}
			});
		});
	});
</script>
</head>
<body>
<form id="writeForm">
	<div class="form-group">
  		<label for="large">대분류:</label>
  		<select class="form-control" id="large">
		</select>
	</div>
	<div class="form-group" id="small_div">
  		<label for="small">소분류:</label>
  		<select class="form-control" id="small">
  			<option selected="selected" disabled="disabled">### 대분류를 선택하세요 ###</option>
		</select>
	</div>
	<div class="form-group" id="vendor_div">
  		<label for="vendor">제조사:</label>
  		<select class="form-control" id="vendor">
  			<option selected="selected" disabled="disabled">### 소분류를 선택하세요 ###</option>
		</select>
	</div>
	<div class="form-group">
		<label for="title">가격:</label>
		<input type="text" class="form-control" id="price" placeholder="가격" name="price">
    </div>
    <div class="form-group">
		<label for="title">수량:</label>
		<input type="text" class="form-control" id="stock" placeholder="수량" name="stock">
    </div>
	<div class="form-group">
		<label for="title">이름:</label>
		<input type="text" class="form-control" id="name" placeholder="제품명" name="name">
    </div>
    <div class="form-group">
		<textarea class="form-control" rows="5" id="info" name="info"></textarea>
	</div>
	<div class="form-group">
		<label for="image">대표사진</label>
    	<input type="file" class="form-control-file" id="sajin">
	</div>
	<button type="button" class="btn btn-success" id="write">작성</button>
</form>
</body>
</html>