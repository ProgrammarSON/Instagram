package com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.comment.*;

public class writeCommentCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("id");
		String feed_id = request.getParameter("comment_feed_id");
		String content = request.getParameter("comment_content");
		
		
		int check = 0;
		commentDTO dto = new commentDTO();
		commentDAO dao = commentDAO.getinstance();
		
		dto.setFeed_id(feed_id);
		dto.setUser_id(user_id);
		dto.setContent(content);
		check = dao.insertComment(dto);
		request.setAttribute("feed_id", feed_id);				
	}

}
