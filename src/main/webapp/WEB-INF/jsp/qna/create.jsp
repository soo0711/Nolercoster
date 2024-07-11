<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<div class="d-flex justify-content-center" >
      <h1 class="display-4 font-weight-bold">문의 페이지 - 등록</h1>
   </div>
   <hr>

		<div class="d-flex justify-content-center ">
			<input type="text" class="width-800 border  mb-4"id="subject" name="subject" placeholder="제목">
		</div>
		<div class="d-flex justify-content-center ">
			<textarea rows="20"  placeholder="내용" class="width-800 border" id="context" name="context"></textarea>	
		</div>
		<div class="d-flex justify-content-end col-9">
			<button class="bg-green btn btn-secondary m-2 col-2" id="qnaCreateBtn">등록</button>
		</div>

</div>

<script>
	$(document).ready(function(){
		$("#qnaCreateBtn").on('click', function(e){
			e.preventDefault();
			//alert("클릭");
			let subject = $("#subject").val();
			let context = $("#context").val();
			
			$.ajax({
				type:"POST"
				,url:"/qna/qna-create"
				,data:{"subject": subject, "context": context}
				,success: function(data) {
					if(data.code == 200) {
						alert("문의가 정상적으로 등록되었습니다");
						location.href="/qna/qna-list-view";
					} else {
						alert(data.error_message);
					}
				}
				,error: function(request, status, error) {
					alert("문의글 등록에 실패했습니다");
				}
			});//ajax
			
		});//qnaCreateBtn
	});//document
</script>