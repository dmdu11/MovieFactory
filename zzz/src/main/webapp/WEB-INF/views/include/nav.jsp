<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>          
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	$(function() {
		// 로그아웃 처리 - 주소는 스프링 시큐리티로 설정. post로 요청해야함
		$("#logout").on("click", function(e) {
			e.preventDefault();
			var $form = $("<form></form>").attr("action","/aboard2s/users/logout").attr("method","post");
			$("<input>").attr("type","hidden").attr("name", "${_csrf.parameterName}").val("${_csrf.token}").appendTo($form);
			$form.appendTo("body");
			$.ajax({
				url:"/aboard2s/users/logout",
				method:"post",
				data: $form.serialize(),
				success:function() {
					location.reload();
				}
			})
		});
		
		// 회원 탈퇴 - 사용자 의사를 다시 확인하고 비밀번호 입력 페이지로 이동
		$("#menu_parent").on("click", "#resign", function() {
			var choice = confirm('회원을 탈퇴하시겠습니까? 같은 아이디로 재가입할 수 없으며 모든 글은 삭제됩니다');
			if(choice==false)
				return;
			location.href="/aboard2s/users/resign"
		});
	});
</script>
</head>
<body>
<div id="nav" class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/aboard2s">ICIA</a>
		</div>
		<ul class="nav navbar-nav" id="menu_parent">
	       	<!-- 로그인하지 않았을 때 보여줄 메뉴 -->
          	<sec:authorize access="isAnonymous()">
				<li><a href="/aboard2s/users/find?job=findId">아이디 찾기</a></li>
				<li><a href="/aboard2s/users/find?job=findPwd">비번 찾기</a></li>
				<li><a href="/aboard2s/users/join">회원가입</a></li>
			</sec:authorize>
			
			<!-- ROLE_USER 권한으로 로그인했을 때 보여줄 메뉴 -->
			<sec:authorize access="hasRole('ROLE_USER')">
	        	<li><a href='/aboard2s/users/read'>내 정보</a></li>
				<li><a href='/aboard2s/memos/receive'>받은 메모</a></li>
				<li><a href='/aboard2s/memos/send'>보낸 메모</a></li>
          		<li><a id='resign' href='#'>탈퇴</a></li>
          	</sec:authorize>
          	
			<!-- ROLE_ADMIN 권한으로 로그인했을 때 보여줄 메뉴 -->
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href="/aboard2s/system/stat">통계</a></li>
	        	<li><a href="/aboard2s/system/users">회원 관리</a></li>
				<li><a href="/aboard2s/system/boards">게시물 관리</a></li>
          	</sec:authorize>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="/aboard2s/boards/list">게시판</a></li>
			<sec:authorize access="isAnonymous()">
				<li><a href="/aboard2s/users/login">로그인</a></li>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<li><a href="#" id="logout">로그아웃</a></li>
			</sec:authorize>
    	</ul>
	</div>
</div>
</body>
</html>