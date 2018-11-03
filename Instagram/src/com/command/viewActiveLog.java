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
		List<activelogDTO> list = dao.getLikeLog(user_id);
		list.addAll(dao.getCommentLog(user_id));
		
		Collections.sort(list);

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
