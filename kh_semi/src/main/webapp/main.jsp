<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ page import="com.member.db.*, com.gmember.db.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제3자</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
<style type="text/css">
div {
	margin: 40px auto;
	text-align: center;
	height: 400px;
}

.jumbotron:hover {
	cursor: pointer
}
</style>

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-6 col-sm-6">
				<div class="jumbotron" onclick="location.href='boardlist.bo';">
					재능삼</div>
			</div>
			<c:choose>
				<c:when test="${principal.major == null}">
					<div class="col-lg-6 col-sm-6">
						<div class="jumbotron"
							onclick="alert('고수 회원만 이용가능한 서비스입니다.');">재능팜</div>
					</div>
				</c:when>

				<c:otherwise>
					<div class="col-lg-6 col-sm-6">
						<div class="jumbotron" onclick="location.href='WriteForm.bo';">
							재능팜</div>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>


