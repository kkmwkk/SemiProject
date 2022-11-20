package com.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.db.MemberBean;
import com.member.db.MemberDAO;

public class MemberJoinAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		
			request.setCharacterEncoding("utf-8");
			
			ActionForward forward = new ActionForward();
			MemberDAO memberdao = new MemberDAO();
			MemberBean member = new MemberBean(); //DTO
			
			boolean result = false;
			
			member.setName(request.getParameter("MEMBER_NAME"));
			member.setEmail(request.getParameter("MEMBER_EMAIL"));
			member.setPassword(request.getParameter("MEMBER_PW"));
			member.setGender(request.getParameter("MEMBER_GENDER"));

	
			result = memberdao.joinMember(member);
			
			if(result == false) {
				System.out.println("회원가입 실패");
				return null;
			}
			
			forward.setRedirect(true);
			forward.setPath("./MemberLogin.me"); //회원가입 됐을때의 페이지(로그인화면)
			return forward;
	}
}
