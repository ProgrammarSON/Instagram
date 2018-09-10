package com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import com.myfeed.*;

import net.sf.json.JSONArray;

public class viewModalFollowCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String user_id = request.getParameter("user_id");
		String state = request.getParameter("state");
		String my_id = (String)session.getAttribute("id");
		//System.out.println(user_id);
		myfeedDAO dao = myfeedDAO.getinstance();
		List<myfeedDTO> list = dao.getModalFollow(user_id, state);
		
		for(int i=0; i<list.size(); i++)
		{
			int check = dao.checkFollow(my_id,list.get(i).getUser_id());
			System.out.println(check);
			if(check > 0) list.get(i).setFollow_check("following");
			else list.get(i).setFollow_check("unfollow");
		}
		
		PrintWriter out;
		
		try {
			out = response.getWriter();
			out.print(JSONArray.fromObject(list).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
