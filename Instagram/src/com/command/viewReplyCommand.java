package com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.comment.*;

import net.sf.json.JSONArray;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class viewReplyCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String comment_id = request.getParameter("comment_id");
				
		commentDAO dao = commentDAO.getinstance();
		List<replyDTO> list = dao.getReply(comment_id);
	
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(JSONArray.fromObject(list).toString());		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//request.setAttribute("reply_list",list);
	}

}
