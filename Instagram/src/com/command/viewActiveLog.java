package com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import com.activelog.*;

import net.sf.json.JSONArray;
public class viewActiveLog implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("id");
		
		activelogDAO dao = activelogDAO.getinstance();
		List<activelogDTO> list = dao.getActiveLog(user_id);
		
		for(activelogDTO dto :list) {
			if(dto.getLike_id() == null)
				System.out.println("comment_id "+ dto.getComment_id());
			else
				System.out.println("like_id "+ dto.getLike_id());
		}
		PrintWriter pw;
		
		try {
			pw = response.getWriter();
			pw.print(JSONArray.fromObject(list).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
