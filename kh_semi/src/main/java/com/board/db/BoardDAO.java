package com.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {

	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	// 1. dbcp 연결
	public BoardDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("DB 연결 실패: " + e);
			return;
		}
	}
	
	// 2. 게시판 글쓰기
	   public boolean boardInsert(BoardBean board) {
	      String sql = "Insert Into Board Values(SEQ_Board_no.NEXTVAL,?,?,?,?,?,SYSDATE)";
	      int result = 0;
	      System.out.println(board.getTitle());
	      System.out.println(board.getCategory());
	      System.out.println(board.getWriter());
	      System.out.println(board.getContent());
	      System.out.println(board.getCnt());
	      try {
	         con = ds.getConnection();
	         pstmt = con.prepareStatement(sql);

	            
	         pstmt.setString(1, board.getTitle());
	         pstmt.setString(2, board.getCategory());
	         pstmt.setString(3, board.getWriter());
	         pstmt.setString(4, board.getContent());
	         pstmt.setInt(5, board.getCnt());
	         
	         result = pstmt.executeUpdate();
	         
	         if(result != 0) {
	            System.out.println("데이터 삽입 성공");
	            return true;
	         }         
	      }catch(Exception ex) {
	         System.out.println("boardInsert 등록 실패 : " + ex);
	      }finally {
	         if(rs!=null) try {rs.close();}catch(SQLException ex) {}
	         if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) {}
	         if(con!=null) try {con.close();}catch(SQLException ex) {}
	      }
	      return false;
	   }
}
