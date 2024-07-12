<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="col-7">
		<div class="d-flex justify-content-center">
			<h1 class="display-4 font-weight-bold">문의페이지 - 수정</h1>
		</div>
		<div class="mt-2">
			<div class="d-flex justify-content-between p-2 border border-dark">
				<input type="text" id="subject" class="ml-4 col-9 border-0" placeholder="제목" value="${qnaCard.qna.subject }">
				<div class="mr-5 text-nowrap">
					<span class="mr-4">${qnaCard.user.nickName }</span>
					<span>${qnaCard.qna.updatedAt }</span>
				</div>
			</div>
		</div>
		<div class="mt-3 min-400">
			<textarea rows="20" placeholder="내용" class="w-100 p-2 border-dark border" id="context" name="context">${qnaCard.qna.context }</textarea>	
		</div>
		<div class="d-flex justify-content-end my-3">
			<button type="button" id="deleteBtn" class="btn bg-secondary border border-dark text-white col-2 mr-3">내용 모두 삭제</button>
			<button type="button" id="updateBtn" class="btn bg-light border border-dark col-2" data-qna-id="${qnaCard.qna.id }">수정하기</button>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		
		// 내용 모두 삭제
		$("#deleteBtn").on("click", function(e) {
			// alert("delete");
			e.preventDefault();
			$("#context").val("");
		});
		
		// 수정하기
		$("#updateBtn").on("click", function(e){
			// alert("modify");
			e.preventDefault();
			let context = $("#context").val();
			let subject = $("#subject").val();
			let qnaId = $(this).data("qna-id");
			
			if (!subject){
				alert("제목을 입력해주세요.");
				return false;
			}
			
			if (!context){
				alert("내용을 입력해주세요.");
				return false;
			}
			
			$.ajax({
				type: "POST"
				, url: "/qna/qna-update"
				, data: {"qnaId" : qnaId, "subject" : subject, "context" : context}
			
				, success: function(data){
					if (data.code == 200){
						alert("수정 성공");
						location.href="/qna/qna-detail-view?qnaId=" + data.qnaId;
					} else {
						alert(data.error_message);
					}
				} 
				, error: function(response, status, error){
					alert("수정 실패. 관리자에게 문의주세요.");
				}
			});
		});
		
		
	}); // - doc

</script>