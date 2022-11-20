<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<c:set var = "path" value = "${pageContext.request.contextPath}" />
<%@ page import = "java.util.*" %>
<%@ page import = "java.text.SimpleDateFormat" %>    
<%@ page import = "com.admin.db.*" %>    
<%@ page import = "com.admin.action.*" %>    
<%@ page import = "com.board.db.*" %>    

<%
	String Email = (String) request.getAttribute("Email");
	List boardList = (List)request.getAttribute("boardlist");
%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고수작성게시물</title>
<!-- css -->
<link href="${path}/css/admin.css" rel="stylesheet">
</head>
<body>
	<table border="1">
	<thead>
		<tr align="center">
			<th colspan="8"><%=Email%> 작성 목록</td>
		</tr>
		<tr align="center">
			<th>번호</th>
			<th>제목</th>
			<th>카테고리</th>
			<th>작성자</th>
			<th>내용</th>
			<th>조회수</th>
			<th>작성일</th>
			<th>게시물 삭제</th>
		</tr>
		<%
			for(int i=0; i<boardList.size();i++){
				BoardBean bl = (BoardBean)boardList.get(i);
		%>	
		</thead>
		<tr>
			<td><%=bl.getNo()%></td>
			<td><%=bl.getTitle()%></td>
			<td><%=bl.getCategory()%></td>
			<td><%=bl.getWriter()%></td>
			<td><%=bl.getContent()%></td>
			<td><%=bl.getCnt()%></td>
			<td><%=bl.getRegdate()%></td>
			<td><a href="GMemberDeleteBoardAction.ad?no=<%=bl.getNo()%>">
					삭 제 </a></td>

		</tr>
		<% } %>
		<tr align="center">
			<td colspan="8"><a href="./GMemberListAction.ad">뒤로</a></td>
		</tr>		
	</table>
</body>
</html>