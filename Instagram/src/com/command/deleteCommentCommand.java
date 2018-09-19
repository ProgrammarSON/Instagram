package com.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.comment.*;
import java.util.*;

import net.sf.json.JSONArray;

public class deleteCommentCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String feed_id = request.getParameter("feed_id");
		String comment_id = request.getParameter("comment_id");
		int check = 0;
		commentDAO dao = commentDAO.getinstance();
		
		check = dao.deleteComment(comment_id, feed_id);
	}
}
