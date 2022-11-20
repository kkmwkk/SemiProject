<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.principal == null}">
			<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
				<!-- 브랜드 / 로고 -->
				<a class="navbar-brand"
					href="<%=request.getContextPath()%>/index.jsp">제3자</a>
				<!-- 링크 -->
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="MemberLogin.me">로그인</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="MemberJoin.me">회원가입</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="GosuJoin.me">고수회원가입</a></li>
				</ul>
			</nav>
		</c:when>
		<c:otherwise>
			<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
				<!-- 브랜드 / 로고 -->
				<a class="navbar-brand"
					href="<%=request.getContextPath()%>/index.jsp">제삼자</a>
				<!-- 링크 -->
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="#">${principal.name}님 반갑습니다.</a></li>
					<li class="nav-item"><a class="nav-link" href="mypage.jsp">마이페이지</a></li>
					<li class="nav-item"><a class="nav-link" href="MemberLogout.me">로그아웃</a></li>
					<c:choose>
						<c:when test="${sessionScope.principal.email == 'qwe@qwe'}">
							<li class="nav-item"><a class="nav-link" href="./AdminMain.ad">회원관리</a></li>
						</c:when>
					</c:choose>
				</ul>
			</nav>
		</c:otherwise>
	</c:choose>
</body>
</html>