package com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comment.*;

public class writeReplyCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String user_id = request.getParameter("user_id");
		String comment_id = request.getParameter("comment_id");
		String contents = request.getParameter("contents");
		
		commentDAO dao = commentDAO.getinstance();
		replyDTO dto = new replyDTO();
		
		int check = dao.insertReply(dto);
			
	}
}
