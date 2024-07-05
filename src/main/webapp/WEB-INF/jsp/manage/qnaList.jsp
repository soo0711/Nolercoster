<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<div class="d-flex justify-content-center">
		<table class="table text-center">
			<thead>
				<tr>
					<th>No.</th>
					<th>제목</th>
					<th>작성자 - 닉네임</th>
					<th>작성자 - loginId</th>
					<th>작성 날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${qnaCardList }" var="qnaCard" varStatus="status">
				<tr>
					<td>${status.count }</td>
					<td>${qnaCard.qna.subject }</td>
					<td>${qnaCard.user.nickName }</td>
					<td>${qnaCard.user.loginId }</td>
					<td>${qnaCard.qna.createdAt }</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>