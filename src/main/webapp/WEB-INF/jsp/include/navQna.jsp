<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="m-3 d-flex justify-content-end bg-info">
	<ul class="nav nav-fill w-100">
		<li class="nav-item"><a href="#" class="nav-link text-white" id="qna">문의</a></li>
		<li class="nav-item"><a href="#" class="nav-link text-white" id="save">코스 저장</a></li>
		<li class="nav-item"><a href="#" class="nav-link text-white" id="user">회원관리</a></li>
	</ul>
</div>

<script>
	$(document).ready(function() {
		$(document).on("click", "#qna", function(e){
			e.preventDefault();
			// alert("test");
			$.ajax({
				url: "/manage/list-view"
				, data: {"menu" : 1}
			
				, success: function(data){
					$(".content").html(data);
				}
			}); // - qna ajax
		}); // - qna
		
		
		// 코스 저장 자리
		
		
		$(document).on("click", "#user", function(e){
			e.preventDefault();
			// alert("test");
			$.ajax({
				url: "/manage/list-view"
				, data: {"menu" : 3}
			
				, success: function(data){
					$(".content").html(data);
				}
			}); // - user ajax
		}); // - user
	}); // - doc
</script>