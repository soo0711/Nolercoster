<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>놀러코스터 관리자</title>
         <!--jquery-->
         <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
        
         <!--bootstrap-->
         <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
         <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
         <!--내가 만든 스타일시트-->
         <link rel="stylesheet" type="text/css" href="/static/css/style.css">
    </head>

    <body>
        <header>
            <div class="d-flex align-items-end logo">
				<h1 class="font-weight-bold" id="logo">관리자 페이지</h1>
			</div>
			<hr>
			<!-- 상단 bar-->
			<div class="m-3 d-flex justify-content-end bg-info">
				<ul class="nav nav-fill w-100">
					<li class="nav-item"><a href="/manage/qna-list-view" class="nav-link text-white" id="qna">문의</a></li>
					<li class="nav-item"><a href="#" class="nav-link text-white" id="save">코스 저장</a></li>
					<li class="nav-item"><a href="/manage/user-list-view" class="nav-link text-white" id="user">회원관리</a></li>
				</ul>
			</div>
			
			<hr>
        </header>

        <div>
			<jsp:include page="../${viewName}.jsp" />
        </div>

        
    </body>
</html>