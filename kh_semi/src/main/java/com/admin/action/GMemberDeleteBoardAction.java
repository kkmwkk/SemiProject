package com.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.db.AdminDAO;

public class GMemberDeleteBoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();
		AdminDAO memberdao = new AdminDAO();
		
		boolean result = false;
		String deleteno = request.getParameter("no");
		result = memberdao.deleteBoard(deleteno);
		
		
		if(result == false) {
			System.out.println("회원삭제 실패");
			return null;
		}
		forward.setRedirect(true);
		forward.setPath("./GMemberListAction.ad");
		return forward;
	}
}
