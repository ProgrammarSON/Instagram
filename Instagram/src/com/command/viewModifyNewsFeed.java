package com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newsfeed.*;
public class viewModifyNewsFeed implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String feed_id = request.getParameter("feed_id");
		feedDAO dao = feedDAO.getinstance();
		feedDTO dto = dao.getModifyFeed(feed_id);
		
		request.setAttribute("dto", dto);
		request.setAttribute("feed_id", feed_id);
	}	
}
