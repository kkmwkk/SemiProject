<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gmember.db.*"%>

<html>
<head>
<title>게시판 - 글쓰기</title>

<link
   href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
   rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
   src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link
   href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
   rel="stylesheet">
<script
   src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<link href="board/writeForm.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
   function addboard() {
      boardform.submit();
   }
</script>
<body>
   <!-- 게시판 등록 -->
   <div class="formBox">
      <form action="./BoardAddAction.bo" method="post" name="boardform">
         <input type="hidden" name="writer" value="${principal.email}">
         <table class="bodyTable">
            <tr align="center" valign="middle">
               <td colspan="5"><h2>제 삼자 게시판 write</h2></td>
            </tr>
            <tr>
               <th>
                  <div>글쓴이</div>
               </th>
               <td>${principal.email}</td>
            </tr>

            <tr>
               <th>
                  <div>제 목</div>
               </th>
               <td><input name="title" id="title" type="text" size="50"
                  maxlength="100" value="" /></td>
            </tr>
            <tr>
               <th>
                  <div>분 야</div>
               </th>
               <td>
                  <div class="selectCategory">
                     <label class='radiobtn'><input type='radio' class='category' id='category1' name='category' value='DESIGN'
                        onclick='getCategory(event)' /><span>디자인/개발</span></label>
                     <label class='radiobtn'><input type='radio' class='category' id='category2' name='category' value='HEALTH'
                        onclick='getCategory(event)' /><span>건강/미용</span></label>
                     <label class='radiobtn'><input type='radio' class='category' id='category3' name='category'
                        value='EXAMPLE' onclick='getCategory(event)' /><span>기타</span></label>
                  </div>
               </td>
            </tr>

            <tr>
               <th>
                  <div>내 용</div>
               </th>
               <td>
                  <div>
                     <textarea id="summernote" name="content" rows="5"
                        style="width: 100%; height: 250px;"></textarea>
                  </div> <script
                     src="${pageContext.request.contextPath}/resources/js/summernote-lite.js"></script>
                  <script
                     src="${pageContext.request.contextPath}/resources/js/summernote-ko-KR.js"></script>
                  <script>
                     $('#summernote').summernote({
                        placeholder : '무엇을 기부하실건가요?',
                        height : 300,
                        width : 1000,
                        minHeight : null,
                        maxHeight : null,
                        focus : true,
                        lang : "ko-KR",
                        callbacks : {
                           onImageUpload : function(files) {
                              sendFile(files[0]);
                           }
                        }
                     });

                     function sendFile(file) {
                        var data = new FormData();
                        data.append("file", file);
                        console.log(file);
                        $.ajax({
                           data : data,
                           type : "POST",
                           url : "./summernoteImageFile.jsp",
                           contentType : false,
                           processData : false,
                           success : function(data) {
                              alert("글쓰기 성공");
                              console.log(data);
                              $(writer).summernote("insertImage",
                                    data.url);
                           }
                        });
                     }
                  </script>
               </td>
            </tr>
         </table>
         <table class="buttonTable">
            <tr align="center" valign="middle">
               <td colspan="2"><a class="button2" href="javascript:history.go(-1)">뒤 로</a>
                  <a class="button" href="javascript:addboard()">등 록</a></td>
            </tr>
         </table>
      </form>
   </div>
   <!-- 게시판 등록 끝 -->
</body>
</html>