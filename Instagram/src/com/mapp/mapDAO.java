package com.mapp;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.comment.commentDAO;

public class mapDAO {

		private static mapDAO instance = new mapDAO();
		
		public static mapDAO getinstance() {
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
		int ma = 0;	
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO Mapinfo VALUES (map_seq.NEXTVAL,?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getAddress());
			ma = pstmt.executeUpdate();
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
		return ma;
	}
}

