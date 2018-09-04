package com.mapp;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.comment.commentDAO;

public class mapDAO {

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
		
		public int insertMap(mapDTO dto) {
		int ri = 0;	
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO Mapinfo VALUES (NEXTVAL,?,?,?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getAddress());
			pstmt.setInt(2, dto.getLat());
			pstmt.setInt(3, dto.getLng());
			ri = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return ri;
	}
}

