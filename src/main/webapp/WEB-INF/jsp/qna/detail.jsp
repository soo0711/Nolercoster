<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex justify-content-center">
	<div class="col-7">
		<div class="d-flex justify-content-center">
			<h1 class="display-4 font-weight-bold">문의페이지 - 상세</h1>
		</div>
		<div class="mt-2">
			<div class="d-flex justify-content-between p-2 border border-dark">
				<span class="ml-5">${qnaCard.qna.subject }</span>
				<div class="mr-5 text-nowrap">
					<span class="mr-4">${qnaCard.user.nickName }</span>
					<span>${qnaCard.qna.updatedAt }</span>
				</div>
			</div>
		</div>
		<div class="mt-3 border border-dark min-400">
			<div class="m-3">
			 	${qnaCard.qna.context }
			</div>
		</div>
		<c:if test="${qnaCard.qna.reply ne NULL}">
			<div class="d-flex">
				<div class="mt-3 border border-dark w-100">
					<div class="m-3 min-10">
						<span>${qnaCard.qna.reply }</span>
					</div>
				</div>
			</div>
		</c:if>
		<form action="/qna/qna-update-view?qnaId=${qnaCard.qna.id }" method="POST">
			<div class="d-flex justify-content-end">
				<button type="submit" id="btnUpdate" class="btn bg-light border border-dark ml-3 my-3 col-2">수정하기</button>
			</div>
		</form>
	</div>
</div>
