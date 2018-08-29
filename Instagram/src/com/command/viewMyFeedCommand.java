package com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.myfeed.*;

public class viewMyFeedCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String user_id = request.getParameter("user_id");
		
		myfeedDAO dao = myfeedDAO.getinstance();
		myfeedDTO dto = dao.getMyFeed(user_id);
		
		request.setAttribute("dto", dto);
	}

}
