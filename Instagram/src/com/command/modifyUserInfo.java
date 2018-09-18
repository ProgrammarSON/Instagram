package com.command;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.*;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class modifyUserInfo implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String uploadPath = request.getRealPath("/feed_image");
		int maxSize = 1024 * 1024 * 10; 
	   	    
	    HttpSession session = request.getSession();
	    String o_user_id = (String)session.getAttribute("id");
	    String username = "";
	    String user_id = "";
	    String password = "";
	    String contents = "";
	    String profile_img = "";
	    int check = 0;
	    
	    memberDAO dao = memberDAO.getInstance();
	    memberDTO dto = new memberDTO();
	    //String address = request.getParameter("add1");
	        
	    MultipartRequest multi = null;
	     
	    try{
	        
	        multi = new MultipartRequest(request,uploadPath,maxSize,"utf-8",new DefaultFileRenamePolicy());
	       	
	        username = multi.getParameter("username");
	        user_id = multi.getParameter("user_id");
	        password = multi.getParameter("password");
	        contents = multi.getParameter("contents");
	        
	        if(password.equals("")) password = "no";
	        
	        Enumeration files = multi.getFileNames();
	         
	        while(files.hasMoreElements())
	        {
	           String file1 = (String)files.nextElement();
	            
	            profile_img = multi.getFilesystemName(file1);
	            	            
	            //image_path = image_path + "\\" + multi.getFilesystemName(file1);
	            if(profile_img == null)
        		{
	            	profile_img = "null.jpg";
        			break;
        		}
	        }
	        
	        System.out.println("password->"+password);
	        dto.setUsername(username);
	        dto.setUser_id(user_id);
	        dto.setPassword(password);
	        dto.setContents(contents);
	        dto.setProfile_img(profile_img);
	        
	        check = dao.updateUserInfo(dto, o_user_id);
        
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	    request.setAttribute("check", check);  
	}
}
