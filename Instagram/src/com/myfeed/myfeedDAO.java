package com.myfeed;

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
}
