<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="signUp d-flex justify-content-center" >
	<div class="signUpForm">
		<div class=" d-flex justify-content-center mb-3">
			<h1>카카오 가입을 위한 세부 사항 입력</h1>
		</div>
		<form>
         <div class="my-2 d-flex">
            <div class="mb-2 mr-3 signUpMenu col-4 pl-0">이름</div>
            <input class="form-control mb-2  col-7" type="text" id="name" name="name">
         </div>
         
         <div class="my-2 d-flex">
            <div class="mb-2 mr-3 signUpMenu col-4 pl-0">이메일</div>
            <input class="form-control mb-2 col-7" type="text" id="email" name="email">
         </div>
         
                  <div class="my-2 d-flex">
            <div class="mb-2 mr-3 signUpMenu col-4 pl-0">전화번호</div>
            <div class="d-flex align-items-center mb-2 col-10 pl-0">
					<select class="form-control" id="phoneStart">
					  <option selected>010</option>
					  <option>016</option>
					  <option>017</option>
					  <option>018</option>
					</select>
					<span class="mx-2"> - </span>
					<input type="text" class="form-control my-2" id="phoneMiddle">
					<span class="mx-2"> - </span>
					<input type="text" class="form-control my-2" id="phoneEnd">
				</div>
         </div>
         
 		<div class="d-flex justify-content-center mb-3">
        	<input type="submit" id="signUpBtn" class="btn bg-green mt-4 signUpForm col-9" value="가입하기">
        </div>
        </form>
	</div>
</div>

<script>
	$(document).ready(function() {
		
		// 회원가입
		$("#signUpBtn").on("click",function(e) {
		e.preventDefault();
		let name = $("#name").val().trim();
		let email = $("#email").val().trim();
		let phoneStart = $("#phoneStart").val().trim();
		let phoneMiddle = $("#phoneMiddle").val().trim();
		let phoneEnd = $("#phoneEnd").val().trim();
		let phoneNumber = phoneStart + phoneMiddle + phoneEnd;
		
		
		if (!name){
			alert("이름을 입력하세요.");
			return false;
		}
		
		if (!email){
			alert("이메일을 입력하세요.");
			return false;
		}
		
		if(!email.includes("@")){
			alert("이메일 양식이 틀렸습니다.")
			return false;
		}
		
		if (!phoneStart || !phoneMiddle || !phoneEnd){
			alert("전화번호를 입력하세요.");
			return false;
		}
		
		$.ajax({
			type: "POST"
			, url: "/user/sign-up-kakao"
			, data: {"name": name, "phoneNumber": phoneNumber, "email": email}
		
			, success: function(data){
				if (data.code == 200){
					alert("회원가입을 축하합니다!(❁´◡`❁)💖");
					location.href="/user/sign-in-view";
				} else {
					alert(data.error_message);
				}
			}
			
			, error: function(request, status, error){
				alert("회원가입에 실패했습니다. 관리자에게 문의주세요.");
			}
		
		})
		
		}); // - 회원가입 click
		
	});
</script>