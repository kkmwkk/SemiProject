package com.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gmember.db.GMemberBean;
import com.gmember.db.GMemberDAO;
import com.member.db.MemberBean;
import com.member.db.MemberDAO;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();

		//HttpSession session = request.getSession();
		MemberDAO memberdao = new MemberDAO();
		MemberBean member = new MemberBean();
		GMemberDAO gmemberdao = new GMemberDAO();
		GMemberBean gmember = new GMemberBean();
		
		member.setEmail(request.getParameter("email"));
		member.setPassword(request.getParameter("password"));

		gmember.setEmail(request.getParameter("email"));
		gmember.setPassword(request.getParameter("password"));
		
		
		MemberBean memberEntity = memberdao.isMember(member);
		GMemberBean gmemberEntity = gmemberdao.isMember(gmember);
		
		// 로그인 성공
		if(memberEntity != null) {
			HttpSession session = request.getSession();
			session.setAttribute("principal", memberEntity);
			System.out.println(memberEntity);
			forward.setRedirect(true);
			System.out.println("로그인 성공");
			forward.setPath("./index.jsp");
			
		}else if(gmemberEntity != null) {
			HttpSession session = request.getSession();
			session.setAttribute("principal", gmemberEntity);
			forward.setRedirect(true);
			forward.setPath("./index.jsp");	
			
		}else if(memberEntity == null || gmemberEntity == null) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('존재하지 않는 회원정보 입니다.')");
			out.println("location.href = './MemberLogin.me'; ");
			out.println("</script>");	
		}
		return forward;		
	}
}

