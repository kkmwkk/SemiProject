<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
	
<style>
html, body {
    width: 100%;
    height: 100%;
}

article {
    min-height: 100%;
    display: flex;
    flex-direction: column;
    align-items: stretch;
}

main {
    flex-grow: 1;
}

footer{
position:fixed; 
width:100%;
height:50px; 
background:;
color:white; 
text-align:center;
bottom:0;left:0;
}
</style>
<meta charset="UTF-8">
<title>레슨(공예 / 미술)</title>
</head>
<body>
<article>

<div class="container mt-3">
  <h1>어떤 서비스를 제공할 수 있나요?</h1>
</div>
<br>
<form name="lessonForm" action="GMemberJoin2.gme" method="post">
<div class="container mt-3">
  <input class="form-check-input" type="checkbox" value="study" id="major" name="major">학업
</div>
<div class="container mt-3">
  <input class="form-check-input" type="checkbox" value="외국어" id="major" name="major">외국어
</div>

<div class="container mt-3">
  <input class="form-check-input" type="checkbox" value="외국어시험" id="major" name="major">외국어시험
</div>

<div class="container mt-3">
  <input class="form-check-input" type="checkbox" value="공예" id="major" name="major">공예
</div>

<div>
  <footer>
  <button type="button" class="btn" onclick="javascript:history.go(-1)">이전</button>
  <button type="button" class="btn btn-success" onclick="javascript:lessonForm.submit()">다음</button>
  </footer>
</div>
</form>
</article>
</body>
</html>