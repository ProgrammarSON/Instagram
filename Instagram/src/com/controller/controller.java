package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.command.*;


/**
 * Servlet implementation class controller
 */
@WebServlet("*.do")
public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
				
		String viewPage = null;
		Command command = null;
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		if(com.equals("/writenewsfeed.do")) {
			command = new writeNewsfeedCommand();
			command.execute(request, response);
			viewPage = "viewnewsfeed.do";
			
		}else if(com.equals("/viewnewsfeed.do")) {
			command = new viewnewsfeedCommand();
			command.execute(request, response);
			viewPage = "viewnewsfeed.jsp";
			
		}else if(com.equals("/writecomment.do")) {
			command = new writeCommentCommand();
			command.execute(request, response);
			viewPage = "check_comment.jsp";
			
		}else if(com.equals("/viewcomment.do")) {
			command = new viewCommentCommand();
			command.execute(request, response);
			viewPage = "viewcomment.jsp";
			
		}else if(com.equals("/viewreply.do")) {
			command = new viewReplyCommand();
			command.execute(request, response);
			return;
			
			//viewPage = "viewcomment.jsp";
		}else if(com.equals("/writereply.do")) {
			command = new writeReplyCommand();
			command.execute(request, response);
			return;
			
		}else if(com.equals("/writenewsfeed.do")) {
			command = new writeNewsfeedCommand();
			command.execute(request, response);
			viewPage  = "check_newsfeed.jsp";
			
		}else if(com.equals("/login.do")) {
			command = new loginMemberCommand();
			command.execute(request, response);
			viewPage = "/member/check_login.jsp";
			
		}else if(com.equals("/join.do")) {
			command = new joinMemberCommand();
			command.execute(request, response);
			viewPage = "/member/check_join.jsp";
			
		}else if(com.equals("/viewmyfeed.do")) {
			command = new viewMyFeedCommand();
			command.execute(request, response);
			viewPage = "viewmyfeed.jsp";
			
		}else if(com.equals("/following.do")) {
			command = new followingCommand();
			command.execute(request, response);
			return;
			
		}else if(com.equals("/searchid.do")) {
			command = new searchUserIDCommand();
			command.execute(request, response);
			return;
		}else if(com.equals("/viewhashtag.do")) {
			command = new viewHashTagFeedCommand();
			command.execute(request, response);
			viewPage = "viewhashtagfeed.jsp";
		}else if(com.equals("/like.do")) {
			command = new likeCommand();
			command.execute(request, response);
			return;
		}else if(com.equals("/viewmodalfollow.do")) {
			command = new viewModalFollowCommand();
			command.execute(request, response);
			return;
		}
		
		RequestDispatcher dispatcher =request.getRequestDispatcher(viewPage);
		dispatcher.forward(request,response);
	}
}
