package com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import com.myfeed.*;

import net.sf.json.JSONArray;

public class viewModalFollowCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String user_id = request.getParameter("user_id");
		String state = request.getParameter("state");
		
		System.out.println(user_id);
		myfeedDAO dao = myfeedDAO.getinstance();
		List<myfeedDTO> list = dao.getModalFollow(user_id, state);
		
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
