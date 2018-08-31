package com.command;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myfeed.*;
import com.newsfeed.*;

public class viewMyFeedCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		String user_id = request.getParameter("user_id");
		String session_id = (String)session.getAttribute("id");
		int check = 0;
		myfeedDAO dao = myfeedDAO.getinstance();
		feedDAO feeddao = feedDAO.getinstance();
		
		
		if(user_id.equals(session_id)) check = 0;
		else {
			check = dao.checkFollow(session_id, user_id);
		}
		myfeedDTO dto = dao.getMyFeed(user_id);
		LinkedHashMap<String,feedDTO> map = feeddao.getMyFeed(user_id);
		
		request.setAttribute("dto", dto);
		request.setAttribute("check", check);
		request.setAttribute("map", map);
	}

}
