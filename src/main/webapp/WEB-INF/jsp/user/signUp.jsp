<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="signUp d-flex justify-content-center" >
	<div class="signUpForm">
		<div class=" d-flex justify-content-center mb-3">
			<h1>회원가입</h1>
		</div>
		<form id="signUpForm" action="/user/sign-up" method="post">
			<!-- 아이디 -->
			<div class="my-2">
				<div class="d-flex">
					<div class="mb-2 mr-3 signUpMenu">아이디</div>
                	<input class="form-control mb-2 mr-3  col-7" type="text" id="loginId" name="loginId" placeholder="아이디를 입력하세요">
                </div>
                 <button class="duplicate-btn btn btn-secondary mb-2">중복확인</button>
			</div>

	
		<!--아이디 체크 결과-->
        <div class="my-1">
        	<div id="idCheckLength" class="text-danger d-none ">ID를 4자 이상 입력해주세요</div>
            <div id="idCheckDupㅉlicated" class="text-danger d-none">중복된 아이디 입니다</div>
            <div id="idCheckOk" class="text-success d-none">사용가능한 아이디 입니다</div>
        </div>		
        
         <!--PW-->
         <div class="my-2 d-flex">
         	<div class="mb-2 mr-3 signUpMenu">비밀번호</div>
            <input class="form-control mb-2 col-7 " type="password" id="password" name="password" placeholder="(8자이상)">
         </div>

         <div class="my-2 d-flex">
            <div class="mb-2 mr-3 signUpMenu">비밀번호 확인</div>
            <input class="form-control mb-2  col-7" type="password" id="confirmPassword" name="confirmPassword">
         </div>
         
         
         <div class="my-2 d-flex">
            <div class="mb-2 mr-3 signUpMenu">이름</div>
            <input class="form-control mb-2  col-7" type="text" id="name" name="name">
         </div>
         
         <div class="my-2 d-flex">
            <div class="mb-2 mr-3 signUpMenu">생년월일</div>
            <input class="form-control mb-2  col-7" type="text" id="birth" name="birth">
         </div>
 		<div class="d-flex justify-content-center mb-3">
        	<input type="submit" id="signUpBtn" class="btn bg-green mt-4 signUpForm col-9" value="가입하기">
        </div>
                    

         </form>
	</div>
</div>