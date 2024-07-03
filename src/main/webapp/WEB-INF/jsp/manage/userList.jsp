<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="content">
	<div class="d-flex justify-content-center">
		<h1>관리자 페이지</h1>
	</div>
	<jsp:include page="../include/navQna.jsp"></jsp:include>
	<div class="d-flex justify-content-center">
		<table class="table text-center">
			<thead>
				<tr>
					<th>No.</th>
					<th>로그인 아이디</th>
					<th>이름</th>
					<th>이메일</th>
					<th>토큰</th>
					<th>닉네임</th>
					<th>루트</th>
					<th>코인</th>
					<th>가입 날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userList }" var="user" varStatus="status">
				<tr>
					<td>${status.count }</td>
					<td>${user.loginId }</td>
					<td>${user.name }</td>
					<td>${user.email }</td>
					<td>${user.userToken }</td>
					<td>${user.nickName }</td>
					<td>${user.route }</td>
					<td>${user.coin }</td>
					<td>${user.createdAt }</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>