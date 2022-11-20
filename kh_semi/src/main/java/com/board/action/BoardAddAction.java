package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.db.BoardBean;
import com.board.db.BoardDAO;

public class BoardAddAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
      BoardDAO   boarddao = new BoardDAO();
      BoardBean   boarddata = new BoardBean();
      ActionForward   forward = new ActionForward();
      
      
      boolean   result = false;
      
      try {
         request.setCharacterEncoding("utf-8");
         boarddata.setWriter(request.getParameter("writer"));
         boarddata.setTitle(request.getParameter("title"));
         boarddata.setCategory(request.getParameter("category"));
         boarddata.setContent(request.getParameter("content"));
         boarddata.setCnt(1);
         
       
         result = boarddao.boardInsert(boarddata);
         
         if (result == false) {
            System.out.println("게시판 등록 실패");
            return null;
         }
         System.out.println("게시판 등록 완료");
         
         forward.setRedirect(true);
         forward.setPath("./AfterAddBoard.bo");
         return forward;
         
      } catch (Exception e) {
         e.printStackTrace();
      }
      return null;
   }

}