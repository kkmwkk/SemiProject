package com.admin.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.db.AdminDAO;
import com.admin.action.GMemberBoardListAction;

public class AdminFrontController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.print("member");
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null; // Action이 모든 작업을 끝내고 view 해줄 경로를 지정
		Action action = null;
		AdminDAO memberDao = new AdminDAO();

		// 어드민 컨트롤러
		if (command.equals("/AdminMain.ad")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./admin/AdminMain.jsp");

		// 어드민 일반회원 관리창 컨트롤러
		} else if (command.equals("/MemberListAction.ad")) {
			action = new MemberListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// 어드민 고수회원 관리창 컨트롤러
		} else if (command.equals("/GMemberListAction.ad")) {
			action = new GMemberListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 어드민 일반회원 삭제
		} else if (command.equals("/MemberDeleteAction.ad")) {
			action = new MemberDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		// 어드민 고수회원 삭제
		} else if (command.equals("/GMemberDeleteAction.ad")) {
			action = new GMemberDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		// 어드민 고수회원 게시물"만" 삭제
		} else if (command.equals("/GMemberDeleteBoardAction.ad")) {
			action = new GMemberDeleteBoardAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		//고수회원 게시물 확인
		} else if (command.equals("/GMemberBoardListAction.ad")) {
		action = new GMemberBoardListAction();
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());

			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("get-!");
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("post-!");
		doGet(request, response);
	}

}