<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<c:set var = "path" value = "${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 게시판</title>

<!-- css -->
<link href="${path}/css/admin.css" rel="stylesheet">
</head>
<body>
	<table border="1">
	    <thead>
		<tr align="center">
			<th colspan="2">관리자 모드</th>
		</tr>
		</thead>
		
		<tr align="center">
			<td><a href="./MemberListAction.ad">회원 정보 확인</a></td>

			<td><a href="./GMemberListAction.ad">고수 회원 정보 확인</a></td>
		</tr>
		<tr align="center">
			<td colspan="2"><a href="./index.jsp">뒤로</a></td>
		</tr>
	</table>


</body>
</html>