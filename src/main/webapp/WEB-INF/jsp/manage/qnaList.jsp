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
					<th>작성자 - loginId</th>
					<th>작성 날짜</th>
					<th>답변 작성 여부</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${qnaCardList }" var="qnaCard" varStatus="status">
				<tr>
					<td>${status.count }</td>
					<td><a href="/manage/qna-reply-view?qnaId=${status.count}">${qnaCard.qna.subject}</a></td>
					<td>${qnaCard.user.nickName }</td>
					<td>${qnaCard.qna.createdAt }</td>
					
						<c:if test="${empty qnaCard.qna.reply}">
							<td class="text-danger">미작성</td>
						</c:if>
						<c:if test="${not empty qnaCard.qna.reply}">
							<td class="text-primary">작성완료</td>
						</c:if>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>