package com.comment;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class commentDAO {

	private static commentDAO instance = new commentDAO();
	
	public static commentDAO getinstance() {
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
	
	public int insertComment(commentDTO dto) {
		Connection conn = null;
		CallableStatement cstmt = null;
		StringBuffer sql = new StringBuffer();
		sql.append("{call insertcomment_proc(?,?,?,?)}");
		int check = 0;
		
		try {
			conn = getConnection();
			cstmt = conn.prepareCall(sql.toString());
			cstmt.setString(1, dto.getUser_id());
			cstmt.setString(2, dto.getFeed_id());
			cstmt.setString(3, dto.getContent());
			cstmt.registerOutParameter(4, java.sql.Types.INTEGER);
			cstmt.executeUpdate();
			
			check = cstmt.getInt(4);
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

	public List<commentDTO> getComments(String feedid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<commentDTO> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT comment_id, c.user_id, to_char(comment_date,'yyyy mm dd HH24 MI SS') AS comment_date, c.contents, profile_img ");
		sql.append("FROM comments c JOIN myfeed m ");
		sql.append("ON c.user_id = m.user_id ");
		sql.append("WHERE c.NEWSFEED_ID = ? ");
		sql.append("ORDER BY comment_date");
		System.out.println(sql.toString());
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, feedid);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				commentDTO dto = new commentDTO();
				dto.setComment_id(rs.getString("comment_id"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setComment_date(CalDate(rs.getString("comment_date")));
				dto.setContent(rs.getString("contents"));
				dto.setImg_path(rs.getString("profile_img"));
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
	
	public int insertReply(replyDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		int check=0;
		sql.append("INSERT INTO reply ");
		sql.append("VALUES(reply_seq.nextval, ?, ?, ?, sysdate)");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getComment_id());
			pstmt.setString(2, dto.getUser_id());
			pstmt.setString(3, dto.getContents());
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
	
	public List<replyDTO> getReply(String comment_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<replyDTO> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT r.user_id, r.contents, profile_img, to_char(reply_date,'yyyy mm dd HH24 MI SS') AS reply_date ");
		sql.append("FROM reply r JOIN myfeed m ");
		sql.append("ON r.user_id = m.user_id ");
		sql.append("WHERE r.comment_id = ?");
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, comment_id);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				replyDTO dto = new replyDTO();
				dto.setUser_id(rs.getString("user_id"));
				dto.setContents(rs.getString("contents"));
				dto.setImg_path(rs.getString("profile_img"));
				dto.setReply_date(CalDate(rs.getString("reply_date")));
				list.add(dto);
				//System.out.println(id+" ggg "+contents);
				//map.get(feedid).getReplys().add(new reply(id,contents));
			
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
	
	public int deleteComment(String comment_id,String feed_id) {
		Connection conn = null;
		CallableStatement cstmt = null;
		StringBuffer sql = new StringBuffer();
		sql.append("{call deleteComment_proc(?,?,?)}");
		int check = 0;
		
		try {
			conn = getConnection();
			cstmt = conn.prepareCall(sql.toString());
			cstmt.setString(1, comment_id);
			cstmt.setString(2, feed_id);
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
	
	private String CalDate(String d) {
		String dateArray[] = d.split(" ");
		
		int dbyear = Integer.parseInt(dateArray[0]);
		int dbmon = Integer.parseInt(dateArray[1]);
		int dbday = Integer.parseInt(dateArray[2]);
		
		int dbhour = Integer.parseInt(dateArray[3]);
		int dbmin = Integer.parseInt(dateArray[4]);
		int dbsec = Integer.parseInt(dateArray[5]);
			 
		//Calendar calendar = new GregorianCalendar(Locale.KOREA);
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		
		
		String result="";
		if(year-dbyear > 0) result = dateArray[0]+"-"+dateArray[1]+"-"+dateArray[2];
		else if(month - dbmon > 0) result = dateArray[1]+"월 " + dateArray[2]+"일";
		else if(day - dbday  > 0) result = Integer.toString(day-dbday) +"일 전";
		else if(hour - dbhour > 0) result = Integer.toString(hour-dbhour)+"시간 전";
		else if(minute - dbmin > 0) result = Integer.toString(minute - dbmin) +"분 전";
		else result = Integer.toString(second-dbsec)+"초 전";
		
		return result;
	}
	
}
