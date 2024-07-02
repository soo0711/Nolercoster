<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="text-center">
     <%-- <a href="https://kauth.kakao.com/oauth/authorize"
       th:href="@{https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${kakaoApiKey}&redirect_uri=${redirectUri}}">
		<img src="/static/image/kakao_login_medium_narrow.png">
    </a> --%>
<%--     <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${kakaoApiKey}&redirect_uri=${redirectUri}">
    	<img src="/static/img/kakao_login_medium_narrow.png">
    </a> --%>
    <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${kakaoApiKey}&redirect_uri=${redirectUri}">
    	<img src="/static/img/kakao_login_medium_narrow.png">
    </a>
	</div>
</body>
</html>