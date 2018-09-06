package com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import com.newsfeed.*;

public class viewHashTagFeedCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String hashtag = request.getParameter("hashtag");
		String imagePath="";
		feedDAO dao = feedDAO.getinstance();
		LinkedHashMap<String, feedDTO> map = null;
		
		map = dao.getHashTagFeed(hashtag);
		for(String key : map.keySet())
		{
			imagePath = map.get(key).getImage_path();
			break;
		}
		request.setAttribute("imagePath", imagePath);
		request.setAttribute("map", map);
		request.setAttribute("hashtag", hashtag);
	}	
}
