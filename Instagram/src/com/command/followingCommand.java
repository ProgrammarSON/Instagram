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
		String state = request.getParameter("check");
				
		myfeedDAO dao = myfeedDAO.getinstance();
		
		if(state.equals("follow")) 
		{
			check = dao.insertFollow(user_id, follow_id);
					
			if(check >0) {
				check = dao.updateFollowNum(user_id, follow_id);
			}
		}else {
			
			check = dao.deleteFollow(user_id, follow_id);
			
			if(check >0) {
				check = dao.updateunFollowNum(user_id, follow_id);
			}
		}
		
		PrintWriter out;
		
		try {
			out = response.getWriter();
			out.print("{\"data\":" + check+"}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
}
