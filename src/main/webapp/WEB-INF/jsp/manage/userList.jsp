<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="content">
	<div class="d-flex justify-content-center">
		<h1>관리자 페이지</h1>
	</div>
	<jsp:include page="../include/navQna.jsp"></jsp:include>
	<div class="d-flex justify-content-end mr-3">
		회원 이름 검색  <input type="text" class="ml-2 mr-2" name="name" id="name">
		<button class="btn bg-info p-2 ml-2" id="nameSearchBtn">검색</button>
	</div>
	<div class="d-flex justify-content-center mt-2">
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

<script>
	$(document).ready(function(){
		$("#nameSearchBtn").on('click', function(){
			//alert("클릭");
			let name = $("#name").val().trim();
			
			$.ajax({
				type:"POST"
				,url:"/manage/find-user-by-name"
				,data:{"name":name}
				,success:function(data) {
					if(data.code == 200) {
						
					} 
				}
				,error:function(request, status, error) {
					alert("이름 검색에 실패했습니다.");
				}
			});
			
			
		});//nameSearchBtn
	});
</script>