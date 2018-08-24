package com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.comment.*;

public class viewCommentCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String feed_id = request.getParameter("feed_id");
		List<commentDTO> list = null;
		commentDAO dao = commentDAO.getinstance();
		list = dao.getComments(feed_id);
		
		request.setAttribute("feed_id", feed_id);
		request.setAttribute("list", list);
	}
}
