<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="signUp d-flex justify-content-center" >
	<div class="signUpForm">
		<div class=" d-flex justify-content-center mb-3">
			<h1>회원가입</h1>
		</div>
		<form>
			<!-- 아이디 -->
			<div class="my-2">
				<div class="d-flex">
					<div class="mb-2 mr-3 signUpMenu col-4 pl-0">아이디</div>
                	<input class="form-control mb-2 mr-3 col-7" type="text" id="loginId" name="loginId" placeholder="아이디를 입력하세요">
	                <button class="duplicate-btn btn btn-secondary mb-2 col-3">중복확인</button>
                </div>
			</div>

	
		<!--아이디 체크 결과-->
        <div class="my-1">
        	<div id="idCheckLength" class="text-danger d-none ">ID를 8자 이상 입력해주세요</div>
            <div id="idCheckDupㅉlicated" class="text-danger d-none">중복된 아이디 입니다</div>
            <div id="idCheckOk" class="text-success d-none">사용가능한 아이디 입니다</div>
        </div>		
        
         <!--PW-->
         <div class="my-2 d-flex">
         	<div class="mb-2 mr-3 signUpMenu col-4 pl-0">비밀번호</div>
            <input class="form-control mb-2 col-7 " type="password" id="password" name="password" placeholder="(8자이상)">
         </div>

         <div class="my-2 d-flex">
            <div class="mb-2 mr-3 signUpMenu col-4 pl-0">비밀번호 확인</div>
            <input class="form-control mb-2  col-7" type="password" id="confirmPassword" name="confirmPassword">
         </div>
         
         
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
		// 중복확인
		$(".duplicate-btn").on('click', function(e) {
			e.preventDefault();
			//alert("중복확인");
			$("#idCheckLength").addClass("d-none");
			$("#idCheckDupㅉlicated").addClass("d-none");
			$("#idCheckOk").addClass("d-none");
			
			
			let loginId = $("#loginId").val().trim();
			if(loginId.length < 8) {
				$("#idCheckLength").removeClass("d-none");
				return false;
			}
			
			$.ajax({
				type:"POST"
				,url:"/user/is-duplicated-id"
				,data:{"loginId":loginId}
				,success:function(data) {
					if(data.code == 200) {
						if(data.is_duplicated_id == true) {// 중복 아이디 일 때
							$("#idCheckDupㅉlicated").removeClass("d-none");
						} else {
							$("#idCheckDuplicated").addClass("d-none");
							$("#idCheckOk").removeClass("d-none");
						}
					} 
				}
				,error:function(request, status, error) {
					alert("아이디 중복확인에 실패했습니다. 관리자에게 문의주세요.");
				}
			});//ajax
			
		});
		
		// 회원가입
		$("#signUpBtn").on("click",function(e) {
		e.preventDefault();
		let loginId = $("#loginId").val().trim();
		let password = $("#password").val().trim();
		let confirmPassword = $("#confirmPassword").val().trim();
		let name = $("#name").val().trim();
		let email = $("#email").val().trim();
		let phoneStart = $("#phoneStart").val().trim();
		let phoneMiddle = $("#phoneMiddle").val().trim();
		let phoneEnd = $("#phoneEnd").val().trim();
		let phoneNumber = phoneStart + phoneMiddle + phoneEnd;
		
		// validation
		if (!loginId){
			alert("아이디를 입력하세요.");
			return false;
		}
		
		if (password.length < 8){
			alert("비밀번호 8자 이상 입력해주세요.")
			return false;
		}
		
		if (!password || !confirmPassword){
			alert("비밀번호를 입력하세요.");
			return false;
		}
		
		
		if (password != confirmPassword){
			alert("비밀번호가 일치하지 않습니다.");
			return false;
		}
		
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
			, url: "/user/sign-up"
			, data: {"loginId": loginId, "password": password, "name": name, "phoneNumber": phoneNumber, "email": email}
		
			, success: function(data){
				if (data.code == 200){
					alert("회원가입을 축하합니다!(❁´◡`❁)💖");
					location.href="/nolercoster/home-view";
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