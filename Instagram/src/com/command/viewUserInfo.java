package com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.member.*;

public class viewUserInfo implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("id");
		memberDAO dao = memberDAO.getInstance();
		
		memberDTO dto = dao.getMemberinfo(user_id);
		
		
		request.setAttribute("dto",dto);
	}
}
