package com.member;

import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class memberDAO {

	
	public static final int MEMBER_NONEXISTENT  = 0;
	public static final int MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;
	
	private static memberDAO instance = new memberDAO();
	private memberDAO() {
		
	}	
	public static memberDAO getInstance() {
		return instance;
	}
	
	private Connection getConnection() {
		
		Context context = null;
		DataSource dataSource = null;
		Connection connection = null;
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
			connection = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public int insertMember(memberDTO dto) {
		int ri = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO member VALUES (?,?,?,?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getUsername());
			pstmt.setString(3, dto.getUser_id());
			pstmt.setString(4, dto.getPassword());
			ri = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	
	
/*	public int confirmId(String id) {
		int ri = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select id from member where id = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				ri = memberDAO.MEMBER_EXISTENT;
			} else {
				ri = memberDAO.MEMBER_NONEXISTENT;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null)conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return ri;
	}*/
	
	public int checkMember(String email, String pw) {
		int ri = 0;
		String dbpw;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT password FROM member WHERE email = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dbpw = rs.getString("password");
				if(dbpw.equals(pw)) {
					ri = memberDAO.MEMBER_EXISTENT;
				} else {
					ri = memberDAO.MEMBER_NONEXISTENT;
				}
			} else {
				ri = memberDAO.MEMBER_LOGIN_IS_NOT;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	/*public int updateMember(String id, String pw) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		int state=0;
		
		String sql = "UPDATE member SET pw=? WHERE id=?";
				
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,pw);
			pstmt.setString(2, id);
			state = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}	
		return state;
	}*/
	
	public String getUserid(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String user_id="";
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT user_id FROM member ");
		sql.append("WHERE email = ?");
		
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user_id = rs.getString("user_id");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return user_id;
	}
	
	public memberDTO getMemberinfo(String user_id) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		memberDTO dto = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT email, username, profile_img, contents ");
		sql.append("FROM member m JOIN myfeed f ");
		sql.append("ON m.user_id = f.USER_ID ");
		sql.append("WHERE m.user_id = ? ");
				
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new memberDTO();
				dto.setEmail(rs.getString("email"));
				dto.setUsername(rs.getString("username"));
				dto.setProfile_img(rs.getString("profile_img"));
				dto.setContents(rs.getString("contents"));
				dto.setUser_id(user_id);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}

	public int updateUserInfo(memberDTO dto,String o_user_id) {
		Connection conn = null;
		CallableStatement cstmt = null;
		
		int check = 0;
		
		try {
			conn = getConnection();
			cstmt = conn.prepareCall("{call modify_userinfo_proc(?,?,?,?,?,?,?)}");
			cstmt.setString(1, o_user_id);
			cstmt.setString(2, dto.getUser_id());
			cstmt.setString(3, dto.getUsername());
			cstmt.setString(4, dto.getPassword());
			cstmt.setString(5, dto.getContents());
			cstmt.setString(6, dto.getProfile_img());
			cstmt.registerOutParameter(7, java.sql.Types.INTEGER);
			
			cstmt.executeUpdate();			
			check = cstmt.getInt(7);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(cstmt != null) cstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return check;
	}
/*	public int updateMember(memberDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBER SET Email = ?, Username = ?, User_id = ? ,Password = ?"
					+"WHERE Email=? Password=?";
		int ri = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getUsername());
			pstmt.setString(3, dto.getUser_id());
			pstmt.setString(4, dto.getPassword());
			ri = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	return ri;*/
}
