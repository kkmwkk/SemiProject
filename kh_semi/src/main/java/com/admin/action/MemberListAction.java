package com.admin.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.db.AdminDAO;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();
		AdminDAO memberdao = new AdminDAO();
		List memberlist = new ArrayList();

		memberlist = memberdao.getMemberList(); //admin 계정이 회원관리 클릭시 db에 연결된 getMemberList메소드 실행
		if(memberlist == null) {
			System.out.println("관리자 목록보기 실패");
			return null;
		}
		
		request.setAttribute("memberlist", memberlist);
		forward.setRedirect(false);
		forward.setPath("./admin/memberList.jsp");
		return forward;
	}
}
