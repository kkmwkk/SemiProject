package com.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gmember.db.GMemberBean;
import com.gmember.db.GMemberDAO;
import com.member.db.MemberBean;
import com.member.db.MemberDAO;

public class MypageModifyAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      request.setCharacterEncoding("UTF-8");
      ActionForward forward = new ActionForward();
      boolean result = false;
      
      String email = request.getParameter("MEMBER_EMAIL");
      String name = request.getParameter("MEMBER_NAME");
      String password = request.getParameter("MEMBER_PASSWORD");
      String gemail = request.getParameter("GMEMBER_EMAIL");
      String gname = request.getParameter("GMEMBER_NAME");
      String gpassword = request.getParameter("GMEMBER_PASSWORD");
      String major = request.getParameter("GMEMBER_MAJOR");

		/*
		 * System.out.println("email"); System.out.println("name");
		 * System.out.println("password"); System.out.println("gemail");
		 * System.out.println("gname"); System.out.println("gpassword");
		 * System.out.println("gmajor");
		 */

      MemberDAO memberdao = new MemberDAO();
      MemberBean member = new MemberBean();
      GMemberDAO gmemberdao = new GMemberDAO();
      GMemberBean gmember = new GMemberBean();
      
      if(gemail != null) { //고수회원일때 (변경가능정보 : 이름, 비번, 포트폴리오)
    	  try { 
    		  gmember.setEmail(gemail);
    		  gmember.setName(gname);
    		  gmember.setPassword(gpassword);
    		  
    		  result = gmemberdao.gmemberModify(gmember);
    		  if (result == false) {
    			  System.out.println("고수회원 회원정보 수정실패");
    			  response.setContentType("text/html;charset=UTF-8");
    			  PrintWriter out = response.getWriter();
    			  out.println("<script>");
    			  out.println("alert('비밀번호를 입력하세요!')");
    			  out.println("location.href = 'mypagemodify.jsp';");
    			  out.println("</script>");
    			  out.close();
    			  return null;
    		  }
    		  System.out.println("고수회원 회원정보 수정성공");
    		  HttpSession session = request.getSession();
    		  session.invalidate();
    		  forward.setRedirect(true);
    		  forward.setPath("index.jsp"); 
    		  // return forward;
    	  } catch (Exception ex) {
    		  ex.printStackTrace();
    	  }
         
      } else { //일반회원일때 (변경가능정보 : 이름, 비번)
    	  try { 
              member.setEmail(email);
              member.setName(name);
              member.setPassword(password);
     
              result = memberdao.memberModify(member);
              if (result == false) {
                 System.out.println("일반 회원정보 수정실패");
                 response.setContentType("text/html;charset=UTF-8");
                 PrintWriter out = response.getWriter();
                 out.println("<script>");
                 out.println("alert('비밀번호를 입력하세요!')");
                 out.println("location.href = 'mypagemodify.jsp';");
                 out.println("</script>");
                 out.close();
                 return null;
              }
              System.out.println("회원정보 수정성공");
              HttpSession session = request.getSession();
              session.invalidate();
              forward.setRedirect(false);
              forward.setPath("index.jsp"); 
              // return forward;
           } catch (Exception ex) {
              ex.printStackTrace();
           }
      }
      return forward;
   }
}