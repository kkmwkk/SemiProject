package com.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.db.AdminDAO;

public class GMemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();
		AdminDAO memberdao = new AdminDAO();
		
		boolean result = false;
		boolean result2 = false;
		String deleteEmail = request.getParameter("email");
		result = memberdao.deleteGMember(deleteEmail);
		result2 = memberdao.deleteGMemberBoard(deleteEmail);
		
		
		if(result == false) {
			System.out.println("회원삭제 실패");
			return null;
		}
		if(result2 == false) {
			System.out.println("게시물이 없습니다.");

		}
		forward.setRedirect(true);
		forward.setPath("./GMemberListAction.ad");
		return forward;
	}
}
