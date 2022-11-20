package com.admin.db;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.board.db.BoardBean;

public class AdminDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;

	public AdminDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}

	// 일반회원목록(admin 계정 로그인시 '회원관리')
	public List getMemberList() {
		String sql = "SELECT * FROM MEMBER";
		// String sql = "SELECT distinct member_id, member_pw, member_name, member_age,
		// member_gender, member_email FROM MEMBER"; //pk = fk
		// 회원가입 후 게시글을 쓴 회원을 추출

		List memberlist = new ArrayList();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				AdminBean memberbean = AdminBean.builder().no(rs.getInt("no")).name(rs.getString("name"))
						.email(rs.getString("email")).password(rs.getString("password")).gender(rs.getString("gender"))
						.regdate(rs.getDate("regdate")).build();

				memberlist.add(memberbean);
			}

			return memberlist;
		} catch (Exception ex) {
			System.out.println("getDetailMember 에러 : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		return null;
	}
	// 고수회원목록(admin 계정 로그인시 '회원관리')
	public List getGMemberList() {
		String sql = "SELECT * FROM GMEMBER";
		// String sql = "SELECT distinct member_id, member_pw, member_name, member_age,
		// member_gender, member_email FROM MEMBER"; //pk = fk
		// 회원가입 후 게시글을 쓴 회원을 추출
		
		List memberlist = new ArrayList();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				AdminBean memberbean = AdminBean.builder()
						.no(rs.getInt("no"))
						.name(rs.getString("name"))
						.email(rs.getString("email"))
						.password(rs.getString("password"))
						.filename(rs.getString("filename"))
						.filerealname(rs.getString("filerealname"))
						.major(rs.getString("major"))
						.gender(rs.getString("gender"))
						.regdate(rs.getDate("regdate")).build();
				
				memberlist.add(memberbean);
			}
			
			return memberlist;
		} catch (Exception ex) {
			System.out.println("getDetailMember 에러 : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		return null;
	}

	// 일반 회원 삭제
	public boolean deleteMember(String email) {
		String sql = "DELETE FROM MEMBER WHERE email = ?";
		int result = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);

			result = pstmt.executeUpdate();
			if (result != 0) {
				return true;
			}
		} catch (Exception ex) {
			System.out.println("deleteMember에러 : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}

		return false;
	}
	// 고수 회원 삭제
	public boolean deleteGMember(String email) {
		String sql = "DELETE FROM GMEMBER WHERE email = ?";
		int result = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			
			result = pstmt.executeUpdate();
			if (result != 0) {
				return true;
			}
		} catch (Exception ex) {
			System.out.println("deleteGMember에러 : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		
		return false;
	}
	// 고수 회원 삭제로인한 게시물 같이 삭제
	public boolean deleteGMemberBoard(String writer) {
		String sql = "DELETE FROM board WHERE WRITER = ?";
		int result = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, writer);
			
			result = pstmt.executeUpdate();
			if (result != 0) {
				return true;
			}
		} catch (Exception ex) {
			System.out.println("deleteGMemberBoard에러 : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		
		return false;
	}
	// 고수 회원 게시물"만" 삭제
	public boolean deleteBoard(String no) {
		String sql = "DELETE FROM board WHERE no = ?";
		int result = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, no);
			
			result = pstmt.executeUpdate();
			if (result != 0) {
				return true;
			}
		} catch (Exception ex) {
			System.out.println("deleteGMemberBoard에러 : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		
		return false;
	}
	
	//게시글 수
	public int getListCount(String email) {
		String sql = "select count(*) from GMEMBER where email = ?";
		int x = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		}catch(Exception ex) {
			System.out.println("getListCount 실패 : " + ex);
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) {}
			if(con!=null) try {con.close();}catch(SQLException ex) {}
		}
		return x;
	}
	
	//게시판 레코드를 읽어 옴
	public List getBoardList(int page, int limit, String writer) {
		String sql = "select * from board WHERE WRITER = ?";
		List boardlist = new ArrayList();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, writer);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardBean boardbean = BoardBean.builder()
						.no(rs.getInt("no"))
						.title(rs.getString("title"))
						.category(rs.getString("category"))
						.writer(rs.getString("writer"))
						.content(rs.getString("content"))
						.cnt(rs.getInt("cnt"))
						.regdate(rs.getDate("regdate")).build();
				boardlist.add(boardbean);
			}
			return boardlist;
		}catch(Exception ex) {
			System.out.println("getBoardList 읽어오기 실패 : " + ex);
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) {}
			if(con!=null) try {con.close();}catch(SQLException ex) {}
		}
		return null;
	}
}