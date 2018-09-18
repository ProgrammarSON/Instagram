package com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.newsfeed.*;

public class deleteNewsFeed implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String feed_id = request.getParameter("feed_id");
		
		feedDAO dao = feedDAO.getinstance();
		int check = dao.deleteNewsFeed(feed_id);
		
		request.setAttribute("check", check);
	}
	
}
