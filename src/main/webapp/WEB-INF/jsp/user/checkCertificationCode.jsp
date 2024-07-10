<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
   <div class="d-flex justify-content-center">
      <h1 class="display-4 font-weight-bold">인증번호 확인</h1>
   </div>
   <hr>
   <form action="/user/check-certificationCode" method="post">
   	  
      <div class="d-flex justify-content-center mt-3">
      	<input id="code" name="code" placeholder="인증번호 입력" class="form-control col-2 mr-1">
      </div>

      <div class="d-flex justify-content-center mt-3">
         <input type="submit" value="인증번호 확인" class="bg-green col-2 border-0" id="checkCodeBtn">
      </div>
   
   </form>
</div>

<script>
   $(document).ready(function(){
      
      $("#checkCodeBtn").on('click', function(e){
         e.preventDefault();
         //alert("클릭");
         let code = $("#code").val().trim();
         
         if(!code){
            alert("인증번호를 입력하세요");
            return false;
         }
    
         
         $.ajax({
            type:"POST"
            ,url:"/user/check-certificationCode"
            ,data:{"code":code}
            ,success:function(data){
               if(data.code == 200){
            	  alert("인증코드가 확인 되었습니다");
                  location.href="/user/reset-password-view";
               }else{
            	   alert(data.error_message);
               }
            }
            ,error:function(request, status, error){
            	alert("인증번호 인증 실패");
            }
         });//ajax
         
      });//findIdBtn
   });//document
</script>