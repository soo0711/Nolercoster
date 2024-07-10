<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
   <div class="d-flex justify-content-center">
      <h1 class="display-4 font-weight-bold">비밀번호 재설정</h1>
   </div>
   <hr>
   <form action="/user/reset-password" method="post">
   	  
      <div class="d-flex justify-content-center mt-3">
      	<input type="password" id="password" name="code" placeholder="새 비밀번호 입력" class="form-control col-2 mr-1">
      </div>
      <div class="d-flex justify-content-center mt-3">
      	<input type="password" id="confirmPassword" name="confirmPassword" placeholder="새 비밀번호 확인" class="form-control col-2 mr-1">
      </div>

      <div class="d-flex justify-content-center mt-3">
         <input type="submit" value="비밀번호 변경" class="bg-green col-2 border-0" id="resetPwBtn">
      </div>
   
   </form>
</div>
    
<script>
	$(document).ready(function() {
		$("#resetPwBtn").on('click', function(e){
			e.preventDefault();
			
			let password = $("#password").val().trim();
			let confirmPassword = $("#confirmPassword").val().trim();
			
			if(password.length < 8) {
				alert("비밀번호를 8자 이상 입력해주세요.");
				return false;
			} 
			if(password != confirmPassword) {
				alert("비밀번호가 일치하지 않습니다 다시 입력해주세요.");
				return false;
			}
			
	
			$.ajax({
	            type:"POST"
	            ,url:"/user/reset-password"
	            ,data:{"password":password}
	            ,success:function(data){
	               if(data.code == 200){
	            	  alert("비밀번호가 변경 되었습니다 로그인을 해주세요");
	                  location.href="/user/sign-in-view";
	               }else{
	            	   alert("비밀번호 재설정 실패");
	               }
	            }
	            ,error:function(request, status, error){
	            	alert("비밀번호 재설정 실패");
	            }
	         });//ajax
		});//resetPwBtn
		
	});//document
</script>