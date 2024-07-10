<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<div class="d-flex justify-content-center">
      <h1 class="display-4 font-weight-bold">아이디 찾기</h1>
   </div>
   <hr>
   	  
      <div class="d-flex justify-content-center mt-3">
      	<input id="name" name="name" placeholder="이름" class="form-control col-2 mr-1">
      </div>

      <div class="d-flex justify-content-center mt-3">
         <input id="email" name="email" placeholder="이메일" class="form-control col-2">
      </div>
      <div class="d-flex justify-content-center mt-3">
         <input type="submit" value="아이디 찾기" class="btn bg-green col-2 border-0" id="findIdBtn">
      </div>
</div>

<script>
	$(document).ready(function() {
		$("#findIdBtn").on("click", function(){
			// alert("클릭");
			let name = $("#name").val().trim();
			let email = $("#email").val().trim();
			
			if (!name){
				alert("이름을 입력해주세요.");
				return false;
			}
			if (!email){
				alert("이메일을 입력해주세요.");
				return false;
			}
			
			$.ajax({
				type: "POST"
				, url: "/user/find-id"
				, data: {"name" : name , "email" : email}
			
				, success: function(data){
					if (data.code == 200){
						alert("아이디는 " + data.loginId + " 입니다.")
						location.href="/user/sign-in-view";
					} else {
						alert(data.error_message);
					}
				}
				, error: function(response, status, error){
					alert("아이디 찾기에 실패했습니다. 관리자에게 문의주세요.");
				}
			}) // - id ajax
		}); // - find Id
	}); // - doc

</script>