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
<script type="text/javascript">
   function mypagemodify(){  //일반회원 회원정보 변경(이름, 비번)
      mypagemodify.submit();
   }
</script>
<style>
table {
   height: 200px;
   /* margin:auto; */
   margin-left: auto;
   margin-right: auto;
   text-align: center;
}

#div1 {
   margin: 40px auto;
   /*    display: flex; */
   /* justify-content: center; */
}

div {
   margin: 5px auto;
   /*    display: flex; */
   /* justify-content: center; */
}
</style>
</head>
<body>

   <form action="MypageModifyAction.me" method="post" name="mypagemodify">
      <div class="col-lg-8" id="div1">
         <div class="jumbotron" style="padding-top: 5px; padding-bottom: 5px;">
            <div class="container">
               <div class="row">
                  <div class="col-sm-12">
                     <div class="col-lg-8">
                        <h2 class="text-center">회원정보</h2>
                        &nbsp;
                        <c:choose>
                           <c:when test="${principal.major == null}">
                              <table class="table table-borderless">
                                 <!-- 일반회원 테이블 -->
                                 <colgroup>
                                    <col width="30%">
                                    <col width="30%">
                                    <col width="60%">
                                 </colgroup>
                                 <tr>
                                    <td rowspan="5" class="align-middle">
                                       <!-- 일반회원 이미지 --> <img src="./images/normal.jpg" alt="일반회원"
                                       style="border-radius: 85px;" width="170px" height="170px">
                                    </td>
                                    <td>이메일</td>
                                    <td><input type="text" class="form-control"
                                       value="${principal.email}" name="MEMBER_EMAIL"
                                       id="MEMBER_EMAIL" maxlength="20" readonly="readonly"></td>
                                 </tr>

                                 <tr>
                                    <td>이름</td>
                                    <td><input type="text" class="form-control"
                                       value="${principal.name}" name="MEMBER_NAME"
                                       id="MEMBER_NAME" maxlength="20"></td>
                                 </tr>

                                 <tr>
                                    <td>비밀번호</td>
                                    <td><input type="text" class="form-control"
                                       value="${principal.password}" name="MEMBER_PASSWORD"
                                       id="MEMBER_PASSWORD" maxlength="20"></td>
                                 </tr>

                                 <tr>
                                    <td>성별</td>
                                    <td><input type="text" class="form-control"
                                       value="${principal.gender}" name="MEMBER_GENDER"
                                       maxlength="20" readonly="readonly"></td>
                                 </tr>

                                 <tr>
                                    <td>가입일자</td>
                                    <td><input type="text" class="form-control"
                                       value="${principal.regdate}" name="MEMBER_REGDATE"
                                       maxlength="20" readonly="readonly"></td>
                                 </tr>

                                 <tr>
                                    <td class="text-center" colspan="3">
                                       <button onclick="javascript:mypagemodify()"
                                          class="btn btn-secondary">저장</button>
                                    </td>
                                 </tr>
                              </table>
                           </c:when>

                           <c:otherwise>
                              <!-- 고수회원 테이블 -->
                              <table class="table table-borderless">
                                 <colgroup>
                                    <col width="30%">
                                    <col width="30%">
                                    <col width="60%">
                                 </colgroup>
                                 <tr>
                                    <td rowspan="5" class="align-middle">
                                       <!-- 고수회원 이미지 --> <img src="./images/gosu.jpg" alt="고수회원"
                                       style="border-radius: 85px;" width="170px" height="170px">
                                    </td>
                                    <td>이메일</td>
                                    <td><input type="text" class="form-control"
                                       value="${principal.email}" name="GMEMBER_EMAIL"
                                       id="GMEMBER_EMAIL" maxlength="20" readonly="readonly"></td>
                                 </tr>

                                 <tr>
                                    <td>이름</td>
                                    <td><input type="text" class="form-control"
                                       value="${principal.name}" name="GMEMBER_NAME"
                                       id="GMEMBER_NAME" maxlength="20"></td>
                                 </tr>

                                 <tr>
                                    <td>비밀번호</td>
                                    <td><input type="text" class="form-control"
                                       value="${principal.password}" name="GMEMBER_PASSWORD"
                                       id="GMEMBER_PASSWORD" maxlength="20"></td>
                                 </tr>

                                 <tr>
                                    <td>성별</td>
                                    <td><input type="text" class="form-control"
                                       value="${principal.gender}" name="GMEMBER_GENDER"
                                       maxlength="20" readonly="readonly"></td>
                                 </tr>

                                 <tr>
                                    <td>가입일자</td>
                                    <td><input type="text" class="form-control"
                                       value="${principal.regdate}" name="GMEMBER_REGDATE"
                                       maxlength="20" readonly="readonly"></td>
                                 </tr>

                                 <tr>
                                    <td class="text-center" colspan="3">
                                       <a class="portfolio-link" data-bs-toggle="modal"
                                       href="#portfolioModal1"><button class="btn btn-secondary">포트폴리오 보기</button></a>
                                       <button onclick="javascript:mypagemodify()" class="btn btn-secondary">저장</button>
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
      </div>
   </form>

   <!-- Portfolio Modal popup-->
   <div class="portfolio-modal modal fade" id="portfolioModal1"
      tabindex="-1" role="dialog" aria-hidden="true">
      <div class="modal-dialog">
         <div class="modal-content">
            <div class="close-modal" data-bs-dismiss="modal">
               <img src="assets/img/close-icon.svg" alt="Close modal" />
            </div>
            <div class="container">
               <div class="row justify-content-center">
                  <div class="col-lg-8">
                     <div class="modal-body">
                        <!-- Project details-->
                        <h2 class="text-uppercase">포트폴리오</h2>
                        
                        <img class="img-fluid d-block mx-auto" src="upload/${principal.filename}" alt="..." /> 
                        
                        <ul class="list-inline">
                           <li><strong>분야 :</strong>major</li>
                        </ul>
                        <button class="btn btn-secondary btn-xl text-uppercase" data-bs-dismiss="modal" type="button">
                           <i class="fas fa-xmark me-1"></i>close
                        </button>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
</body>
</html>