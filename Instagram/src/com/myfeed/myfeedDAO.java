package com.myfeed;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class myfeedDAO {

	private static myfeedDAO instance = new myfeedDAO();
	
	public static myfeedDAO getinstance() {
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
	
	public myfeedDTO getMyFeed(String user_id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		myfeedDTO dto = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM myfeed ");
		sql.append("WHERE user_id = ?");
		
		
		try {
			conn =getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				dto = new myfeedDTO();
				dto.setUser_id(rs.getString("user_id"));
				dto.setFeed_num(rs.getString("feed_num"));
				dto.setFollower_num(rs.getString("follower_num"));
				dto.setFollowing_num(rs.getString("following_num"));
				dto.setContents(rs.getString("contents"));
				dto.setProfile_img(rs.getString("profile_img"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	public int insertMyfeed(myfeedDTO dto) {
		int ri = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO myfeed VALUES (?,0,0,0,?,?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUser_id());
			pstmt.setString(2, dto.getContents());
			pstmt.setString(3, dto.getProfile_img());
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
	
	public int insertFollow(String user_id, String follow_id) {
		int ri = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO follow VALUES (?,?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, follow_id);
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
	
	public int deleteFollow(String user_id, String follow_id) {
		int ri = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer(); 
		sql.append("DELETE FROM follow ");
		sql.append("WHERE user_id = ? AND following = ?");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, user_id);
			pstmt.setString(2, follow_id);
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
	
	public int updateFollowNum(String user_id, String follow_id) {
		Connection conn = null;
		CallableStatement cstmt = null;
		int check=0;
		try {
			conn = getConnection();		
			cstmt = conn.prepareCall("{call following_proc(?,?)}");
			cstmt.setString(1, user_id);
			cstmt.setString(2, follow_id);
						
			check = cstmt.executeUpdate();
						
			if(check == 0) System.out.println("No Data");
			else System.out.println("Success");
			
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
	
	public int updateunFollowNum(String user_id, String follow_id) {
		Connection conn = null;
		CallableStatement cstmt = null;
		int check=0;
		try {
			conn = getConnection();		
			cstmt = conn.prepareCall("{call unfollow_proc(?,?)}");
			cstmt.setString(1, user_id);
			cstmt.setString(2, follow_id);
						
			check = cstmt.executeUpdate();
						
			if(check == 0) System.out.println("No Data");
			else System.out.println("Success");
			
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
	
	public int checkFollow(String user_id, String follow_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM follow ");
		sql.append("WHERE user_id = ? AND following = ?");
		int check = 0;
		
		try {
			conn =getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, user_id);
			pstmt.setString(2, follow_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) check = 1;
			else check = -1;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return check;
	}
	
	public List<myfeedDTO> getSearch(String user_id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		myfeedDTO dto = null;
		List<myfeedDTO> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT user_id FROM myfeed ");
		//sql.append("WHERE user_id = ?");
		
		
		try {
			conn =getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			//pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				dto = new myfeedDTO();
				dto.setUser_id(rs.getString("user_id"));
				list.add(dto);
			}		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public String getProfieImg(String user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String img = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT profile_img FROM myfeed ");
		sql.append("WHERE user_id = ?");
		
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				img = rs.getString("profile_img");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return img;
	}
	
	public List<myfeedDTO>getModalFollow(String user_id , String state) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		myfeedDTO dto = null;
		List<myfeedDTO> list = new ArrayList<>();
		if(state.equals("following")) {
			sql.append("SELECT following, profile_img ");
			sql.append("FROM follow f JOIN myfeed m ");
			sql.append("ON f.following = m.USER_ID ");
			sql.append("WHERE f.USER_ID = ? AND NOT f.following = ?");
		}else {
			sql.append("SELECT f.user_id AS follower, profile_img ");
			sql.append("FROM follow f JOIN myfeed m ");
			sql.append("ON f.user_id = m.USER_ID ");
			sql.append("WHERE f.following = ? AND NOT f.user_id = ?");
		}
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_id);
			rs = pstmt.executeQuery();
			
			if(state.equals("following")) 
				while(rs.next()) {
					dto = new myfeedDTO();
					dto.setUser_id(rs.getString("following"));
					dto.setProfile_img(rs.getString("profile_img"));
					list.add(dto);
				}
			else
				while(rs.next()) {
					dto = new myfeedDTO();
					dto.setUser_id(rs.getString("follower"));
					dto.setProfile_img(rs.getString("profile_img"));
					list.add(dto);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
}
