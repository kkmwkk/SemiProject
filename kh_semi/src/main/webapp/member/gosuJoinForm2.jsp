<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<%
	String major = request.getParameter("major");
	System.out.println(major);
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>회원가입</title>
  <meta charset="utf-8">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>

<body>

<div class="container mt-3">
  <h1>제삼자 <small>에 오신것을 환영합니다.(고수)</small></h1>
</div>
<br>


<div class="container">
	<form name = "joinForm" action="./GosuMemberJoinAction.gme" method="post" enctype = "multipart/form-data">
	 <!-- onsubmit="return valid()" -->
		<div class="form-group">
			<label for="username"><b>이름</b></label> 
			<input type="text" class="form-control" placeholder="Enter Username" name="MEMBER_NAME" id="MEMBER_NAME">
		</div>


		<div class="form-group">
			<label for="email"><b>이메일</b></label> 
			<input type="email" class="form-control" placeholder="Enter email" name="MEMBER_EMAIL" id="MEMBER_EMAIL">
		</div>
		
		<!-- <div class="d-flex justify-content-end">
			<button type="button" class="btn btn-info" onClick="javascript:usernameCheck()">중복체크</button>
		</div> -->
		
		<div class="form-group">
			<label for="password"><b>패스워드</b></label> 
			<input type="password" class="form-control" placeholder="Enter password" name="MEMBER_PW" id="MEMBER_PW">
		</div>
		
		<div class="form-group">
			<label for="password"><b>재능</b></label> 
			<input type="text" class="form-control" value = "<%=major%>"  readonly = "readonly"  name="MEMBER_MAJOR" id="MEMBER_MAJOR">
		</div>
		
		파일 : <input type="file" name="file"><br>

		<input type="submit" value="업로드"><br>
		
		<label for="gender"><b>성별</b></label>
		<br>
		<div class="form-check-inline">
			<label class="form-check-label"> 
			<input type="radio" class="form-check-input" name="MEMBER_GENDER" id="MEMBER_GENDER" value="남">남자		
			</label>
		</div>
		
		
		
		<div class="form-check-inline">
			<label class="form-check-label"> 
			<input type="radio" class="form-check-input" name="MEMBER_GENDER" id="MEMBER_GENDER" value="여">여자
			</label>
		<br>
		<br>
		</div>
		<div>
			<button id="btn-save" class="btn btn-primary" onclick="javascript:joinform.submit()">확인</button>
		</div>
	</form>
</div>

<script>
	var isChecking = false;
	
	function valid(){
		if(isChecking == false){
			alert("아이디 중복체크를 해주세요");
		}
		return isChecking;
	}
	
	function usernameCheck(){
		// DB에서 확인해서 정상이면 isChecking = true
		var email = $("#MEMBER_EMAIL").val();
		console.log(email);
		
		$.ajax({
			type: "POST",
			url: "UserNameCheck.me",
			data: email,
			contentType: "text/plain; charset=utf-8",
			dataType: "Text"  // 응답 받을 데이터의 타입을 적으면 자바스크립트 오브젝트로 파싱해줌.
		}).done(function(data){
			if(data === 'ok'){ // 유저네임 있다는 것
				isChecking = false;
				alert('유저네임이 중복되었습니다.')
			}else{
				isChecking = true;
				$("#email").attr("readonly", "readonly");
				alert("해당 유저네임을 사용할 수 있습니다.")
			}
		});
	}
</script>
</body>
</html>