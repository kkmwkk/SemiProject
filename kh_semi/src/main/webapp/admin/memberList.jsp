<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<c:set var = "path" value = "${pageContext.request.contextPath}" />
<%@ page import="java.util.*"%>
<%@ page import="com.admin.db.*"%>
<%
List memberlist = (List) request.getAttribute("memberlist");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리 시스템 관리자모드(회원목록보기)</title>
<!-- css -->
<link href="${path}/css/admin.css" rel="stylesheet">
</head>
<body>

	<table border="1">
		<thead>
		<tr align="center">
			<th colspan="6">일반 회원 목록</td>
		</tr>
		<tr align="center">
			<th>번호</th>
        	<th>이름</th>  
        	<th>이메일</th>  
			<th>성별</th>
			<th>가입 날짜</th>
			<th>계정 삭제</th>
		</tr>
		<%for (int i = 0; i < memberlist.size(); i++) {
			AdminBean member = (AdminBean) memberlist.get(i);%>
		</thead>
		<tr align="center">
			<td><%=member.getNo()%></td>
			<td><%=member.getName()%></td>
			<td><%=member.getEmail()%></td>
			<td><%=member.getGender()%></td>
			<td><%=member.getRegdate()%></td>
			<td><a href="MemberDeleteAction.ad?email=<%=member.getEmail()%>">
					삭 제 </a></td>
		</tr>
		<%}%>
		<tr align="center">
			<td colspan="6"><a href="./AdminMain.ad">뒤로</a></td>
		</tr>
	</table>

</body>
</html>