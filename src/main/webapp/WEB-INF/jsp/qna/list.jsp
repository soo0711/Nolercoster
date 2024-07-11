<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<div class="d-flex justify-content-center" >
      <h1 class="display-4 font-weight-bold">문의 페이지</h1>
   </div>
   <hr>
   
	<div class="d-flex justify-content-center  min-400">
		<table class="table text-center">
			<thead>
				<tr>
					<th>No.</th>
					<th>제목</th>
					<th>닉네임</th>
					<th>작성 날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${qnaCardList }" var="qnaCard" varStatus="status">
				
					<tr>
						<td>${status.count}</td>
						<td><a href="/qna/qna-detail-view?qnaId=${status.count}">${qnaCard.qna.subject}</a></td>
						<td>${qnaCard.user.nickName }</td>
						<td>${qnaCard.qna.createdAt }</td>
					</tr>
				
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="d-flex justify-content-end pr-4">
		<a href="/qna/qna-create-view" class="btn bg-green">문의 글 쓰기</a>
	</div>
</div>