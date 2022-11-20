package com.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardFrontController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;
		System.out.println(command);

		if (command.equals("/BoardAddAction.bo")) {
			System.out.println("BoardAddAction.bo 실행중");
			action = new BoardAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/WriteForm.bo")) {
			forward = new ActionForward();
			System.out.println("WriteForm.bo 컨트롤러 실행중");
			forward.setPath("./board/writeForm.jsp");
			forward.setRedirect(false);
		}

		else if (command.equals("/AfterAddBoard.bo")) {
			forward = new ActionForward();
			System.out.println("AfterAddBoard.bo 컨트롤러 실행중");
			forward.setPath("./main.jsp");
			forward.setRedirect(false);
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
		System.out.println("board doGet()~~~\n");
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("board doPost()%%% \n");
		doGet(request, response);
	}

}