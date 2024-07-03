<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="content">
	<div class="d-flex justify-content-center">
		<h1>마이 페이지</h1>
	</div>
	<jsp:include page="../include/navUser.jsp"></jsp:include>
	<div class="d-flex justify-content-center">
		<div>
			<h3 class="text-center">내 정보</h3>
			<c:if test="${user.userToken eq NULL}">
			 <div class="my-2 d-flex">
	         	<div class="mb-2 mr-3 signUpMenu col-4 pl-0">비밀번호</div>
	            <input class="form-control mb-2 col-7 " type="password" id="password" name="password" placeholder="(8자이상)">
	         </div>
	
	         <div class="my-2 d-flex">
	            <div class="mb-2 mr-3 signUpMenu col-4 pl-0">비밀번호 확인</div>
	            <input class="form-control mb-2  col-7" type="password" id="confirmPassword" name="confirmPassword">
	         </div>
	         </c:if>
	         <div class="my-2 d-flex">
	            <div class="mb-2 mr-3 signUpMenu col-4 pl-0">닉네임</div>
	            <input class="form-control mb-2  col-7" type="text" id="nickName" name="nickName" value="${user.nickName }">
	         </div>
	         
	         
	                  <div class="my-2 d-flex">
	            <div class="mb-2 mr-3 signUpMenu col-4 pl-0">전화번호</div>
	            <div class="d-flex align-items-center mb-2 col-8 pl-0">
						<input type="text" class="form-control my-2" id="phoneStart" value="${start }">
						<span class="mx-2"> - </span>
						<input type="text" class="form-control my-2" id="phoneMiddle" value="${middle }">
						<span class="mx-2"> - </span>
						<input type="text" class="form-control my-2" id="phoneEnd" value="${end }">
					</div>
	         </div>
	         
	 		<div class="d-flex justify-content-center mb-3">
	        	<input type="submit" id="updateBtn" class="btn bg-info mt-4 signUpForm col-9" value="수정하기">
	        </div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$("#updateBtn").on("click", function(e){
			// alert("test");
			e.preventDefault();
			let password = $("#password").val();
			let confirmPassword = $("#confirmPassword").val();
			let nickName = $("#nickName").val();
			let start = $("#phoneStart").val();
			let middle = $("#phoneMiddle").val();
			let end = $("#phoneEnd").val();
			let phoneNumber = start + middle + end;
			
			if (password != "" && password.length < 8){
				alert("비밀번호를 8자 이상 입력해주세요.");
				return false;
			}
			
			if (password != confirmPassword){
				alert("비밀번호가 서로 일치하지 않습니다.");
				return false;
			}
			
			if (!nickName){
				alert("닉네임을 입력해주세요.");
				return false;
			}
			
			if (!start || !middle || !end){
				alert("전화번호를 입력해주세요.");
				return false;
			}
			
			$.ajax({
				type: "POST"
				, url: "/user/update"
				, data: {"password" : password, "nickName" : nickName, "phoneNumber" : phoneNumber}
			
				, success: function(data){
					if (data.code == 200){
						alert("수정 되었습니다.");
						location.reload(true);
					} else {
						alert(data.error_message);
					}
				}
				, error: function(response, status, error){
					alert("수정에 실패했습니다. 관리자에게 문의주세요.")
				}
			})
			
		}); // - updateBtn
	}); // - doc

</script>