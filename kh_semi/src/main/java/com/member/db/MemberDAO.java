package com.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MemberDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	
	public MemberDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception ex) {
			System.out.println("DB 연결 실패 : "  + ex);
			return;
		}
	}
	
	// 회원가입
	public boolean joinMember(MemberBean member) {
		String sql = "INSERT INTO Member VALUES (SEQ_Board_no.NEXTVAL,?,?,?,?,SYSDATE)";
		
		int result = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, member.getName());
			System.out.println(member.getName());
			
			pstmt.setString(2, member.getEmail());
			System.out.println(member.getEmail());
			
			pstmt.setString(3, member.getPassword());
			System.out.println(member.getPassword());
			
			pstmt.setString(4, member.getGender());
			System.out.println(member.getGender());
			
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
	
	// 회원검사
	public MemberBean isMember(MemberBean member) {
		String sql = "SELECT name, email, gender, regdate FROM Member WHERE Email = ? AND password = ?";
		
		try {
			con= ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getPassword());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				MemberBean memberbean = MemberBean.builder()
						.name(rs.getString("name"))
						.email(rs.getString("email"))
						.password(member.getPassword())
						.gender(rs.getString("gender"))
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
	
	// 회원 중복
	public int findByUsername(String email) {
		System.out.println(email);
		String sql = "SELECT * FROM Member WHERE Email = ?";
		try {
			con= ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			if(rs != null) try {rs.close();}catch(SQLException ex) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException ex) {}
			if(con != null) try {con.close();}catch(SQLException ex) {}
		}
		return -1; // 없어
	}
	
	 public int joinIdCheck(String userid) {
	      String sql = "SELECT * FROM FRESH_USER WHERE USER_ID = ?";
	         
	      try {
	            con = ds.getConnection();
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1,  userid);
	           
	            if(rs.next()) {
	               return 1;
	            }
	         } catch (Exception ex) {
	            ex.printStackTrace();
	         } finally {
	            if(rs != null) try {rs.close();}catch(SQLException ex) {}
	            if(pstmt != null) try {pstmt.close();}catch(SQLException ex) {}
	            if(con != null) try {con.close();}catch(SQLException ex) {}
	         }
	         return -1;
	 }
	 
	 	//일반회원 정보 수정 메소드 (이름, 비밀번호만 변경가능)
	   public boolean memberModify(MemberBean member) { 
	      
	      String sql = "UPDATE member SET Name = ?, Password = ? where Email = ?";
	      
	      System.out.println(member.getName());
	      System.out.println(member.getPassword());
	      System.out.println(member.getEmail());
	      
	      try {
	         con = ds.getConnection();
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, member.getName());
	         pstmt.setString(2, member.getPassword());
	         pstmt.setString(3, member.getEmail());
	         pstmt.executeUpdate();
	         return true; //정보수정 성공시 true 리턴
	      }catch(Exception ex) {
	         System.out.println("memberModify 회원정보 변경실패 : " + ex);
	      }finally {
	         if(rs != null) try {rs.close();} catch(SQLException ex) {}
	         if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
	         if(con != null) try {con.close();} catch(SQLException ex) {}
	      }
	      return false;
	   }
}