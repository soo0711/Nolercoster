<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="signUp d-flex justify-content-center" >
	<div class="signUpForm">
		<div class=" d-flex justify-content-center my-3">
			<h1>로그인</h1>
		</div>
		<form>
 		<div class="my-2 d-flex">
         	<div class="mb-2 mr-3 signUpMenu col-3 pl-0">아이디</div>
            <input class="form-control mb-2 col-7 " id="loginId" name="loginId">
         </div>
         
         <div class="my-2 d-flex">
         	<div class="mb-2 mr-3 signUpMenu col-3 pl-0">비밀번호</div>
            <input class="form-control mb-2 col-7 " type="password" id="password" name="password">
         </div>

 		<div class="d-flex justify-content-center">
        	<input type="button" id="signInBtn" class="btn bg-green mt-4 signUpForm col-9" value="로그인">
        </div>
        
        <!-- 카카오 로그인 넣기 -->
   		<div class="d-flex justify-content-center mb-3">
        	<input type="button" id="kakao" class="btn bg-warning mt-2 signUpForm col-9" value="카카오 로그인">
        </div>
        
        </form>
	</div>
</div>

<script>
	$(document).ready(function(){
		$("#signInBtn").on("click", function() {
			let loginId = $("#loginId").val();
			let password = $("#password").val();
			
			// vaildation
			if (!loginId){
				alert("이이디를 입력해주세요.");
				return false;
			}
			
			if (!password){
				alert("비밀번호를 입력해주세요.");
				return false;
			}
			
			$.ajax({
				type: "POST"
				, url: "/user/sign-in"
				, data: {"loginId" : loginId, "password" : password}
			
				, success: function(data){
					if(data.code == 200){
						location.href="/nolercoster/home-view";
					} else{
						alert(data.error_message);
						location.reload();
					}
				}
				, error: function(request, status, error){
					alert("로그인에 실패했습니다. 관리자에게 문의주세요.");
				}
			}); // - ajax
			
		}); // - signInBtn
	}); // - doc

</script>