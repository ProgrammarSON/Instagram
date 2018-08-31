package com.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comment.*;

import net.sf.json.JSONArray;

public class writeReplyCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	
//		response.setContentType("text/html;charset=UTF-8");
//		response.setCharacterEncoding("UTF-8");
		
		String user_id = request.getParameter("user_id");
		String comment_id = request.getParameter("comment_id");
		String contents = request.getParameter("contents");
		
		commentDAO dao = commentDAO.getinstance();
		replyDTO dto = new replyDTO();
		dto.setUser_id(user_id);
		dto.setComment_id(comment_id);
		dto.setContents(contents);
		
		int check = dao.insertReply(dto);
		
		if(check > 0) { 
			System.out.println("success");
			List<replyDTO> list = dao.getReply(comment_id);
			PrintWriter out;
			try {
//				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=utf-8");
				response.setCharacterEncoding("utf-8");
				
				out = response.getWriter();
				out.print(JSONArray.fromObject(list).toString());		
			} catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else System.out.println("failed");
				
	}
}
