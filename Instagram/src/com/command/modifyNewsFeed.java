package com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.newsfeed.*;
public class modifyNewsFeed implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int check = 0;
		String contents = request.getParameter("contents");
		String address = request.getParameter("address");
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		String feed_id = request.getParameter("feed_id");
		
		feedDAO dao = feedDAO.getinstance();
		feedDTO dto = new feedDTO();
		
		dto.setContents(contents);
		dto.setAddress(address);
		dto.setLatitude(latitude);
		dto.setLongitude(longitude);
		
		check = dao.updateNewsFeed(dto, feed_id);
		
		request.setAttribute("check", check);
		request.setAttribute("feed_id",feed_id);
	}

}
