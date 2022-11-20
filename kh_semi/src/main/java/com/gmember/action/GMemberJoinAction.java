package com.gmember.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmember.db.GMemberBean;
import com.gmember.db.GMemberDAO;
import com.member.action.Action;
import com.member.action.ActionForward;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class GMemberJoinAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
			
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html; charset=UTF-8");
				
				ActionForward forward = new ActionForward();
				GMemberDAO gmemberdao = new GMemberDAO();
				GMemberBean gmember = new GMemberBean(); //DTO
				
				String uploadDir =this.getClass().getResource("").getPath();
				uploadDir = "/Users/jeonmin-u/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/kh_semi/upload";
				
				int maxSize = 1024 * 1024 * 100;
				String encoding = "UTF-8";
				
				MultipartRequest multipartRequest = new MultipartRequest(request, uploadDir, maxSize, encoding, new DefaultFileRenamePolicy());
				
				
				String fileName = multipartRequest.getOriginalFileName("file");
				String fileRealName = multipartRequest.getFilesystemName("file");
				
				boolean result = false;
				
				
				gmember.setName(multipartRequest.getParameter("MEMBER_NAME"));
				gmember.setEmail(multipartRequest.getParameter("MEMBER_EMAIL"));
				gmember.setPassword(multipartRequest.getParameter("MEMBER_PW"));
				gmember.setGender(multipartRequest.getParameter("MEMBER_GENDER"));
				gmember.setMajor(multipartRequest.getParameter("MEMBER_MAJOR"));
				gmember.setFilename(fileName);
				gmember.setFilerealname(fileRealName);

				
				

		
				result = gmemberdao.joinMember(gmember);
				
				if(result == false) {
					System.out.println("회원가입 실패");
					return null;
				}
				
				forward.setRedirect(false);
				forward.setPath("/MemberLogin.me"); //회원가입 됐을때의 페이지(로그인화면)
				return forward;
		}
	}