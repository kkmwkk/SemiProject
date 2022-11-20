package com.gmember.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class GMemberDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	
	public GMemberDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception ex) {
			System.out.println("DB 연결 실패 : "  + ex);
			return;
		}
	}
	
	public boolean joinMember(GMemberBean gmember) {
		String sql = "INSERT INTO GMember VALUES (SEQ_GMember_no.NEXTVAL,?,?,?,?,?,?,?,sysdate)";
		int result = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			
			pstmt.setString(1, gmember.getName());
			System.out.println(gmember.getName());
			
			pstmt.setString(2, gmember.getEmail());
			System.out.println(gmember.getEmail());
			
			pstmt.setString(3, gmember.getPassword());
			System.out.println(gmember.getPassword());
			
			pstmt.setString(4, gmember.getGender());
			System.out.println(gmember.getGender());
			
			pstmt.setString(5, gmember.getFilename());
			
			pstmt.setString(6, gmember.getFilerealname());
			
			pstmt.setString(7, gmember.getMajor());
			
			
			
			result = pstmt.executeUpdate(); //리턴타입 Int
		
			if(result != 0) { //0이 아니라면 = 정상적으로 insert가 되었다면 true를 리턴
				return true;
			}
		}catch(Exception ex) {
			System.out.println("joinMember 에러 : 44" + ex);
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException ex) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException ex) {}
			if(con != null) try {con.close();}catch(SQLException ex) {}
		}
		return false;
	}
	
	public GMemberBean isMember(GMemberBean gmember) {
		String sql = "SELECT name, email, password, gender, major, filename, filerealname, regdate FROM GMember WHERE Email = ? AND password = ?";
		
		try {
			con= ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gmember.getEmail());
			pstmt.setString(2, gmember.getPassword());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				GMemberBean memberbean = GMemberBean.builder()
						.name(rs.getString("name"))
						.email(rs.getString("email"))
						.password(rs.getString("password"))
						.gender(rs.getString("gender"))
						.major(rs.getString("major"))
						.filename(rs.getString("filename"))
						.filerealname(rs.getString("filerealname"))
						.regdate(rs.getDate("regdate"))
						.build();
				return memberbean;	
			}
		}catch(Exception ex) {
			System.out.println("isMember 에러 : " + ex);
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException ex) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException ex) {}
			if(con != null) try {con.close();}catch(SQLException ex) {}
		}
		return null;
	}
	
	   //고수회원 정보 수정 메소드 (이름, 비밀번호, 포트폴리오 변경가능)
	   public boolean gmemberModify(GMemberBean gmember) { 
	      
	      String sql = "UPDATE gmember SET Name = ?, Password = ? where Email = ?";
	      
	      System.out.println(gmember.getName());
	      System.out.println(gmember.getPassword());
	      System.out.println(gmember.getEmail());
	      
	      try {
	         con = ds.getConnection();
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, gmember.getName());
	         pstmt.setString(2, gmember.getPassword());
	         pstmt.setString(3, gmember.getEmail());
	         pstmt.executeUpdate();
	         return true; //정보수정 성공시 true 리턴
	      }catch(Exception ex) {
	         System.out.println("gmemberModify 회원정보 변경실패 : " + ex);
	      }finally {
	         if(rs != null) try {rs.close();} catch(SQLException ex) {}
	         if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
	         if(con != null) try {con.close();} catch(SQLException ex) {}
	      }
	      return false;
	   }
	   
	   public int upload(String fileName, String fileRealName) {

			String sql = "INSERT INTO gmember(fileName,fileRealName) VALUES (?, ?)";

			try {

				con = ds.getConnection();
		        
				pstmt = con.prepareStatement(sql);

				pstmt.setString(1,  fileName);

				pstmt.setString(2,  fileRealName);

				return pstmt.executeUpdate();

			} catch(Exception e) {

				e.printStackTrace();

			}

			return -1;

		}
}