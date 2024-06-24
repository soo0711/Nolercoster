<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>놀러코스터</title>


<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
	crossorigin="anonymous"></script>


<!--내가 만든 스타일시트-->
<link rel="stylesheet" type="text/css" href="/static/css/style.css">
</head>

<body>
	<div class="d-flex justify-content-center align-items-center logo">
		<a href="/nolercoster/home-view" class="font-weight-bold" id="logo">놀러코스터</a>
	</div>
	<div class="p-2 d-flex justify-content-end menu">
		<!-- 로그인 시 -->
		<c:if test="${!empty userName}">
			<div class="ml-2">${userName}님 환영합니다~</div>
			<a href="/user/sign-out" class="ml-2">로그아웃</a>
			<a href="" class="ml-2">장바구니</a>
			<!-- <a href="/user/user-update-view" class="ml-2">마이페이지</a> -->
			<a href="/user/myPage?userId=${userId}" class="ml-2">마이페이지</a>
		</c:if>
		<!-- 로그인 아닐 경우  -->
		<c:if test="${empty userName}">
			<a href="/user/sign-up-view" class="ml-2">회원가입</a> 
			<a href="/user/sign-in-view" class="ml-2">로그인</a>
		</c:if>
	</div>
	<hr>
	<div class="d-flex justify-content-center banner">
		<img src="/static/img/rollercoster3.png" width="1300px" class="">
	</div>
	
</body>
</html>