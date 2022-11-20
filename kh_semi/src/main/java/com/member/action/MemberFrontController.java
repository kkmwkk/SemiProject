package com.member.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.db.MemberDAO;


public class MemberFrontController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	private static final long serialVersionUID = 1L;
	


	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("member");
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null; // Action이 모든 작업을 끝내고 view 해줄 경로를 지정
		Action action = null;
		MemberDAO memberDao = new MemberDAO();
		System.out.println(command);
		
		// 로그인 컨트롤러
		if (command.equals("/MemberLogin.me")) {
			System.out.println("MemberLogin.me 컨트롤러 실행");
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/loginForm.jsp");
		
		// 일반멤버 회원가입 컨트롤러
		} else if (command.equals("/MemberJoin.me")) {
			System.out.println("MemberJoin.me 컨트롤러 실행");
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/joinForm.jsp");
			
		// 고수멤버 회원가입 컨트롤러
		}else if (command.equals("/GosuJoin.me")) {
			System.out.println("GosuJoin.me 컨트롤러 실행");
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/gosuJoinForm.jsp");
		
		}else if (command.equals("/MemberJoinAction.me")) {
			action = new MemberJoinAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}else if (command.equals("/MemberLoginAction.me")) {
			action = new MemberLoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}else if (command.equals("/MemberLogout.me")) {
			System.out.println("MemberLogout.me 컨트롤러 실행");
			HttpSession session = request.getSession();
			session.invalidate();
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("index.jsp");
		
		}else if(command.equals("/UserNameCheck.me")) {
			System.out.println("/UserNameCheck.me 컨트롤러 실행중!");
			BufferedReader br = request.getReader();
			String username = br.readLine();
			System.out.println(username);	
			int result = memberDao.findByUsername(username);
			PrintWriter out = response.getWriter();
			System.out.println(result);
			if(result == 1) {
				out.print("ok");
			}else {
				out.print("fail");
			}
			out.flush();
		}else if (command.equals("/MypageModifyAction.me")) {
	         action = new MypageModifyAction();
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