<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="col-7">
		<div class="d-flex justify-content-center">
			<h1 class="display-4 font-weight-bold">문의페이지 - 상세</h1>
		</div>
		<div class="mt-2">
			<div class="d-flex justify-content-between p-2 border border-dark">
				<span class="ml-2",>${qna.subject}</span>
				<div class="mr-5">
					<span>${qna.createdAt}</span>
				</div>
			</div>
		</div>
		<div class="mt-3 border border-dark min-200">
			<div class="m-2">
			 	${qna.context}
			</div>
		</div>
		<div class="d-flex justify-content-center pt-3">
			<textarea rows="7" cols="115" placeholder="답변" class=" border  border-dark" data-qna-id="${qna.id}" id="reply" name="reply">${qna.reply}</textarea>	
		</div>
		<div class="d-flex justify-content-end ">
			<button class="btn btn-secondary m-2 col-2" id="qnaDeleteBtn">내용 모두 지우기</button>
			<button class="bg-green btn m-2 col-2" id="qnaReplyBtn">답변 등록</button>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$("#qnaReplyBtn").on('click', function(e){
			e.preventDefault();
			//alert("클릭");
			let reply = $("#reply").val();
			let qnaId = $("#reply").data("qna-id");
			$.ajax({
				type:"POST"
				,url: "/qna/qna-reply"
				,data: {"reply":reply, "qnaId": qnaId}
				,success: function(data){
					if(data.code == 200) {
						alert("답변이 등록되었습니다.");
						location.href="/manage/qna-list-view"
					} 
				}
				,error: function(request, status, error){
					alert("답글 작성에 실패했습니다");
				}
			});
		});
		
		// 답변 내용 지우기 
		$("#qnaDeleteBtn").on("click", function(e){
			e.preventDefault();
			// alert("삭제");
			$("#reply").val("");
		});
		
	});
</script>