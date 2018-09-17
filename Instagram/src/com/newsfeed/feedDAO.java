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
		sql.append("SELECT n.contents, n.user_id, n.NEWSFEED_ID, to_char(n.feed_date,'yyyy mm dd HH24 MI SS') AS feed_date, n.image_path, m.PROFILE_IMG, NVL2(l.newsfeed_id,'like','unlike') like_state, ");
		sql.append("n.comment_count, n.like_count, n.address, n.latitude, n.longitude ");
		sql.append("FROM NEWSFEED n JOIN (SELECT following FROM follow ");
		sql.append("WHERE user_id = ?) p ");
		sql.append("ON n.user_id = p.following ");
		sql.append("JOIN myfeed m ");
		sql.append("ON m.user_id = n.user_id LEFT OUTER JOIN likes l ");
		sql.append("ON l.newsfeed_id = n.newsfeed_id AND l.USER_ID = ? ");
		sql.append("ORDER BY n.feed_date DESC");
		
/*		sql.append("SELECT n.contents, n.user_id, n.NEWSFEED_ID, n.FEED_DATE, n.image_path, m.PROFILE_IMG ");
		sql.append("FROM NEWSFEED n JOIN (SELECT following FROM follow ");
		sql.append("WHERE user_id = ?) p ");
		sql.append("ON n.user_id = p.following ");
		sql.append("JOIN myfeed m ");
		sql.append("ON m.user_id = n.user_id ");
		sql.append("ORDER BY n.feed_date DESC");*/
		
		System.out.println(sql.toString());
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			map = new LinkedHashMap<>();
			
			while(rs.next())
			{
				feedDTO dto = new feedDTO();
				dto.setContents(rs.getString("contents"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setDate(CalDate(rs.getString("feed_date")));
				dto.setImage_path(rs.getString("image_path"));
				dto.setProfile_img(rs.getString("profile_img"));
				dto.setLike_state(rs.getString("like_state"));
				dto.setLike_count(rs.getString("like_count"));
				dto.setComment_count(rs.getString("comment_count"));
				dto.setAddress(rs.getString("address"));
				dto.setLatitude(rs.getString("latitude"));
				dto.setLongitude(rs.getString("longitude"));
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
		sql.append("SELECT contents, user_id, NEWSFEED_ID, FEED_DATE, image_path, like_count, comment_count ");
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
				dto.setComment_count(rs.getString("comment_count"));
				dto.setLike_count(rs.getString("like_count"));
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
			cstmt = conn.prepareCall("{call newsfeed_proc(?,?,?,?,?,?,?)}");
			cstmt.setString(1, dto.getUser_id());
			cstmt.setString(2, dto.getContents());
			cstmt.setString(3, dto.getImage_path());
			cstmt.setString(4, dto.getAddress());
			cstmt.setString(5, dto.getLatitude());
			cstmt.setString(6, dto.getLongitude());
			cstmt.registerOutParameter(7, java.sql.Types.INTEGER);
			
			cstmt.executeUpdate();			
			newsfeed_id = cstmt.getInt(7);
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
		sql.append("SELECT n.image_path, n.contents, n.comment_count, m.user_id, m.profile_img ");
		sql.append("FROM newsfeed n JOIN myfeed m ");
		sql.append("ON n.user_id = m.user_id ");
		sql.append("WHERE newsfeed_id = ?");
		System.out.println(sql.toString());
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, feed_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setImage_path(rs.getString("image_path"));
				dto.setContents(rs.getString("contents"));				
				dto.setComment_count(rs.getString("comment_count"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setProfile_img(rs.getString("profile_img"));
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
	
	public LinkedHashMap<String,feedDTO> getHashTagFeed(String tag){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LinkedHashMap<String,feedDTO> map = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT contents, user_id, n.NEWSFEED_ID, to_char(feed_date,'yyyy mm dd HH24 MI SS') AS feed_date, image_path, comment_count, like_count ");
		sql.append("FROM newsfeed n JOIN hashtag h ");
		sql.append("ON n.newsfeed_id = h.NEWSFEED_ID ");
		sql.append("WHERE hashtag_contents = ? ");
		sql.append("ORDER BY feed_date DESC");
		
		System.out.println(sql.toString());
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, tag);
			rs = pstmt.executeQuery();
			map = new LinkedHashMap<>();
			
			while(rs.next())
			{
				feedDTO dto = new feedDTO();
				dto.setContents(rs.getString("contents"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setDate(CalDate(rs.getString("feed_date")));
				dto.setImage_path(rs.getString("image_path"));
				dto.setComment_count(rs.getString("comment_count"));
				dto.setLike_count(rs.getString("like_count"));
				map.put(rs.getString("newsfeed_id"), dto);
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
	
	private String CalDate(String d) {
		String dateArray[] = d.split(" ");
		
		int dbyear = Integer.parseInt(dateArray[0]);
		int dbmon = Integer.parseInt(dateArray[1]);
		int dbday = Integer.parseInt(dateArray[2]);
		
		int dbhour = Integer.parseInt(dateArray[3]);
		int dbmin = Integer.parseInt(dateArray[4]);
		int dbsec = Integer.parseInt(dateArray[5]);
			 
		Calendar calendar = new GregorianCalendar(Locale.KOREA);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		
		 String result="";
		 if(year-dbyear > 0) result = dateArray[0]+"-"+dateArray[1]+"-"+dateArray[2]+"-";
		 else if(month - dbmon > 0) result = dateArray[1]+"-" + dateArray[2]+"-";
		 else if(day - dbday  > 0) result = Integer.toString(day-dbday) +"days ago";
		 else if(hour - dbhour > 0) result = Integer.toString(hour-dbhour)+"hours ago";
		 else if(minute - dbmin > 0) result = Integer.toString(minute - dbmin) +"min ago";
		 else result = Integer.toString(second-dbsec)+"sec ago";
		
		 return result;
	}
	
	public int setLikeinfo(String feed_id, String user_id,String state) {
		Connection conn = null;
		CallableStatement cstmt = null;
		int check = 0;
		StringBuffer sql = new StringBuffer();
		if(state.equals("like"))
			sql.append("{call insertlike_proc(?,?,?)}");
		else
			sql.append("{call deletelike_proc(?,?,?)}");
		
		try {
			conn = getConnection();
			cstmt = conn.prepareCall(sql.toString());
			cstmt.setString(1, feed_id);
			cstmt.setString(2, user_id);
			cstmt.registerOutParameter(3, java.sql.Types.INTEGER);
			cstmt.executeUpdate();
			
			check = cstmt.getInt(3);
						
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
	
	
}
