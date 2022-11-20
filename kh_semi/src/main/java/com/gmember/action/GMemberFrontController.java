package com.gmember.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmember.db.GMemberDAO;
import com.member.action.Action;
import com.member.action.ActionForward;

public class GMemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("gmember 22");
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null; // Action이 모든 작업을 끝내고 view 해줄 경로를 지정
		Action action = null;
		GMemberDAO gmemberDao = new GMemberDAO();
		System.out.println(command);
	
		
		if (command.equals("/LessionJoin.gme")) {
		System.out.println("LessionJoin.me 컨트롤러 실행");
		forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./member/gosuJoinForm2.jsp");
		}
		
		else if (command.equals("/GMemberJoin.gme")) {
			System.out.println("GMemberJoin.gme 컨트롤러 실행");
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/lessonJoinForm.jsp");
		}
		
		else if (command.equals("/GMemberJoin2.gme")) {
			System.out.println("GMemberJoin2.gme 컨트롤러 실행");
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/gosuJoinForm2.jsp");
		}
		
		
		
		else if (command.equals("/lessonJoinForm.gme")) {
		System.out.println("222");
		forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./member/lessonJoinForm.jsp");
		}
		
		else if (command.equals("/GosuMemberJoinAction.gme")) {
		System.out.println("GosuMemberJoinAction 컨트롤러 실행중");
		action = new GMemberJoinAction();
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
       



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get-!");
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post-!");
		doGet(request, response);
	}

}
