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

<title>고수 회원관리 시스템 관리자모드(회원목록보기)</title>


<!-- css -->
<link href="${path}/css/admin.css" rel="stylesheet">
</head>
<body>
<table border="1">
    <thead>
    	<tr align="center">
			<th colspan="8">고수 회원 목록</td>
		</tr>
    <tr>
        <th>번호</th>
        <th>이름</th>        
        <th>이메일</th>
        <th>성별</th>
        <th>가입 날짜</th>
        <th>포트폴리오</th>
        <th>활동 분야</th>
        <th>계정 삭제</th>
    </tr>
    <%for (int i = 0; i < memberlist.size(); i++) {
        AdminBean member = (AdminBean) memberlist.get(i);%>
    </thead>
    <tbody>
        <tr align="center">
			<td><%=member.getNo()%></td>
			<td><%=member.getName()%></td>
			<td>
				<a href="GMemberBoardListAction.ad?email=<%=member.getEmail()%>">
					<%=member.getEmail() %>
				</a>
			</td>
			<td><%=member.getGender()%></td>
			<td><%=member.getRegdate()%></td>
			<td class="text-center" colspan="1">
            	<a class="portfolio-link" data-bs-toggle="modal"href="#portfolioModal1">
               		<button class="btn btn-secondary">포트폴리오 보기</button></a>
            </td>
           	<td><%=member.getMajor()%></td>
			<td><a href="GMemberDeleteAction.ad?email=<%=member.getEmail()%>">
					삭 제 </a></td>
		</tr>
        <%}%>
        <tr align="center">
			<td colspan="8"><a href="./AdminMain.ad">뒤로</a></td>
		</tr>
    </tbody>
</table>

</body>
</html>