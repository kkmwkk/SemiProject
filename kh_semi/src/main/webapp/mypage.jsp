<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ page import="com.member.db.*, com.gmember.db.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
table {
	height: 280px;
	margin-left: auto;
	margin-right: auto;
	text-align: center;
}

#div1 {
	margin: 40px auto;
}

div {
	margin: 20px auto;
}
</style>
</head>
<body>
	<div class="col-lg-8" id="div1">
		<div class="jumbotron" style="padding-top: 5px; padding-bottom: 5px;">
			<div class="row">
				<div class="col-sm-12">
					<div class="col-lg-8">
						<h2 class="text-center">회원정보</h2>
						&nbsp;
						<c:choose>
							<c:when test="${principal.major == null}">
								<table class="table table-borderless">
									<colgroup>
										<col width="30%">
										<col width="30%">
										<col width="60%">
									</colgroup>
									<tr>
										<td rowspan="4" class="align-middle">
											<!-- 일반회원 이미지 --> <img src="./images/normal.jpg" alt="일반회원"
											style="border-radius: 85px;" width="170px" height="170px">
										</td>
										<td>이름</td>
										<td>${principal.name}</td>
									</tr>

									<tr>
										<td>이메일</td>
										<td>${principal.email}</td>
									</tr>

									<tr>
										<td>성별</td>
										<td>${principal.gender}</td>
									</tr>

									<tr>
										<td>가입일자</td>
										<td>${principal.regdate}</td>
									</tr>
									<tr>
										<td class="text-center" colspan="3">
											<button onclick="location.href='mypagemodify.jsp'"
												class="btn btn-secondary">회원정보 수정</button>
										</td>
									</tr>
								</table>
							</c:when>
						

						<c:otherwise>
							<table class="table table-borderless">
								<colgroup>
									<col width="30%">
									<col width="30%">
									<col width="60%">
								</colgroup>
								<tr>
									<td rowspan="5" class="align-middle"><img
										src="./images/gosu.jpg" alt="고수회원"
										style="border-radius: 85px;" width="170px" height="170px">
									</td>
									<td>이름</td>
									<td>${principal.name}</td>
								</tr>

								<tr>
									<td>이메일</td>
									<td>${principal.email}</td>
								</tr>

								<tr>
									<td>성별</td>
									<td>${principal.gender}</td>
								</tr>

								<tr>
									<td>활동분야</td>
									<td>${principal.major}</td>
								</tr>

								<tr>
									<td>가입일자</td>
									<td>${principal.regdate}</td>
								</tr>
								<tr>
									<td class="text-center" colspan="3">
										<button onclick="location.href='mypagemodify.jsp'"
											class="btn btn-secondary">회원정보 수정</button>
									</td>
								</tr>
							</table>
						</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>