package com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import com.newsfeed.*;
public class viewnewsfeedCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("id");
		feedDAO dao = new feedDAO();
		LinkedHashMap<String,feedDTO> map = dao.getNewsFeed(user_id);
		//for(String key : map.keySet())
		//	dao.getReply(map, key);
				
		request.setAttribute("map",map);
	}

}
