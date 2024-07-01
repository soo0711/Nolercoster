<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="content">
	<div class="d-flex justify-content-center">
		<h1>관리자 페이지</h1>
	</div>
	<jsp:include page="../include/navQna.jsp"></jsp:include>
	<div class="d-flex justify-content-center">
		<table class="table text-center">
			<thead>
				<tr>
					<th>No.</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성 날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${qnaList }" var="qna" varStatus="status">
				<tr>
					<td>${status.count }</td>
					<td>${qna.subject }</td>
					<td>${qna.userId }</td>
					<td>${qna.createdAt }</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>