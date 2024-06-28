<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    pageEncoding="UTF-8"%>
<div>
	<div class="d-flex justify-content-center">
		<h1>관리자 페이지</h1>
	</div>
	<div class="m-3 d-flex justify-content-end bg-info">
		<ul class="nav nav-fill w-100">
			<li class="nav-item"><a href="/manage/qna-list-view" class="nav-link text-white">문의</a></li>
			<li class="nav-item"><a href="#" class="nav-link text-white">코스 저장</a></li>
			<li class="nav-item"><a href="/manage/user-list-view" class="nav-link text-white">회원관리</a></li>
		</ul>
	</div>
	<div class="d-flex justify-content-center">
		<table class="table text-center">
			<thead>
				<tr>
					<th>No.</th>
					<th>유저 이름</th>
					<th>가입 날짜</th>
				</tr>
			</thead>
			<tbody>
				<!-- 반복 구간 -->
				<tr>
					<td>1</td>
					<td>사용자1</td>
					<td>2024-06-29</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>