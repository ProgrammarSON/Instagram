package com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.comment.*;
import com.newsfeed.*;

public class viewCommentCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String feed_id = request.getParameter("feed_id");
		List<commentDTO> list = null;
		commentDAO dao = commentDAO.getinstance();
		feedDAO feeddao = feedDAO.getinstance();
		feedDTO feeddto = null;
		
		list = dao.getComments(feed_id);
		feeddto = feeddao.getOneFeed(feed_id);
		System.out.println("feed id -> " +feed_id);
		
		request.setAttribute("dto", feeddto);
		request.setAttribute("feed_id", feed_id);
		request.setAttribute("list", list);
	}
}
