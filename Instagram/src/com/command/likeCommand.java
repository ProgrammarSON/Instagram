package com.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newsfeed.*;

public class likeCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String session_id = (String)session.getAttribute("id");
		String feed_id = request.getParameter("feed_id");
		String state = request.getParameter("check");
		
		feedDAO dao = feedDAO.getinstance();
		int check = dao.setLikeinfo(feed_id, session_id, state);
		
		PrintWriter out;
		
		try {
			out = response.getWriter();
			out.print("{\"data\":"+check+"}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
}
