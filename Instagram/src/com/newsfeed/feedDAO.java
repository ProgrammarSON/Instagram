package com.newsfeed;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class feedDAO {
	
	private static feedDAO instance = new feedDAO();
	
	public static feedDAO getinstance() {
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

	public LinkedHashMap<String,feedDTO> getNewsFeed(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LinkedHashMap<String,feedDTO> map = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT n.contents, n.user_id, n.NEWSFEED_ID, n.FEED_DATE, n.image_path, m.PROFILE_IMG ");
		sql.append("FROM NEWSFEED n JOIN (SELECT following FROM follow ");
		sql.append("WHERE user_id = ?) p ");
		sql.append("ON n.user_id = p.following ");
		sql.append("JOIN myfeed m ");
		sql.append("ON m.user_id = n.user_id ");
		sql.append("ORDER BY n.feed_date DESC");
		
		System.out.println(sql.toString());
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			map = new LinkedHashMap<>();
			
			while(rs.next())
			{
				feedDTO dto = new feedDTO();
				dto.setContents(rs.getString("contents"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setDate(rs.getString("feed_date"));
				dto.setImage_path(rs.getString("image_path"));
				dto.setProfile_img(rs.getString("profile_img"));
				map.put(rs.getString("newsfeed_id"), dto);
				//System.out.println(rs.getString("contents"));
				//System.out.println(rs.getString("image_path"));
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
		return map;
	}
	
	public LinkedHashMap<String,feedDTO> getMyFeed(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LinkedHashMap<String,feedDTO> map = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT contents, user_id, NEWSFEED_ID, FEED_DATE, image_path ");
		sql.append("FROM NEWSFEED ");
		sql.append("WHERE user_id = ? ");
		sql.append("ORDER BY feed_date DESC");
		
		System.out.println(sql.toString());
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			map = new LinkedHashMap<>();
			
			while(rs.next())
			{
				feedDTO dto = new feedDTO();
				dto.setContents(rs.getString("contents"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setDate(rs.getString("feed_date"));
				dto.setImage_path(rs.getString("image_path"));
				map.put(rs.getString("newsfeed_id"), dto);
				//System.out.println(rs.getString("contents"));
				//System.out.println(rs.getString("image_path"));
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
		return map;
	}
	
	public int insertNewsFeed(feedDTO dto) {
		Connection conn = null;
		CallableStatement cstmt = null;
		
		int check = 0;
		int newsfeed_id = 0;
		try {
			conn = getConnection();
			cstmt = conn.prepareCall("{call newsfeed_proc(?,?,?,?)");
			cstmt.setString(1, dto.getUser_id());
			cstmt.setString(2, dto.getContents());
			cstmt.setString(3,dto.getImage_path());
			cstmt.registerOutParameter(4, java.sql.Types.INTEGER);
			
			cstmt.executeUpdate();			
			newsfeed_id = cstmt.getInt(4);
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
		return newsfeed_id;
	}
	
	public int updateMyFeedNum(String user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		int check = 0;
		sql.append("UPDATE myfeed ");
		sql.append("SET feed_num = feed_num + 1 ");
		sql.append("WHERE user_id = ?");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, user_id);
			check = pstmt.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return check;
	}
	
	public feedDTO getOneFeed(String feed_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		feedDTO dto = new feedDTO();
		sql.append("SELECT image_path, contents ");
		sql.append("FROM newsfeed ");
		sql.append("WHERE newsfeed_id = ?");
		
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, feed_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setImage_path(rs.getString("image_path"));
				dto.setContents(rs.getString("contents"));				
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
		return dto;
	}
	
	public void insertHashTag(Set<String> set, int feed_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO hashtag VALUES(?,?)");
			
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			Iterator it = set.iterator();
			
			while(it.hasNext())
			{
				pstmt.setString(1, (String)it.next());
				pstmt.setInt(2, feed_id);
				pstmt.addBatch();
				pstmt.clearParameters();
			}
			pstmt.executeBatch();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
