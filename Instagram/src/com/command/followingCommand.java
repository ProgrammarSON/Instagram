package com.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.myfeed.*;

public class followingCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int check = 0;
		String user_id = request.getParameter("user_id");
		String follow_id = request.getParameter("follow_id");
		
		myfeedDAO dao = myfeedDAO.getinstance();
				
		check = dao.insertFollowing(user_id, follow_id);
					
		if(check >0) {
			check = dao.updateFollowNum(user_id, follow_id);
		}
		
		PrintWriter out;
		
		try {
			out = response.getWriter();
			out.print("{\"data\":" + 3+"}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
