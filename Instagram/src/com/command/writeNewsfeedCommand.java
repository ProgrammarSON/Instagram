package com.command;

import java.io.File;
import java.util.*;

import com.newsfeed.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;



public class writeNewsfeedCommand implements Command{

	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
			String uploadPath = request.getRealPath("/feed_image");
			int maxSize = 1024 * 1024 * 10; 
		    int newsfeed_id = 0;
		    int ma = 0;
		    
		    
		    HttpSession session = request.getSession();
		    String user_id = (String)session.getAttribute("id");
		    String contents = "";
		    String image_path="";
		    String address="";
		    String latitude="";
		    String longitude="";
		    
		    feedDTO dto = new feedDTO();
		    feedDAO dao =feedDAO.getinstance();
		    Set<String> set = new HashSet<>();
		    //String address = request.getParameter("add1");
		    
		 		         
		     
		    
		    MultipartRequest multi = null;
		     
		    try{
		        
		        multi = new MultipartRequest(request,uploadPath,maxSize,"utf-8",new DefaultFileRenamePolicy());
		       		    
		        contents = multi.getParameter("contents");
		        address = multi.getParameter("address");
		        latitude = multi.getParameter("latitude");
		        longitude = multi.getParameter("longitude");
		        
		        Enumeration files = multi.getFileNames();
		         
		        while(files.hasMoreElements())
		        {
		           String file1 = (String)files.nextElement();
		            
		            image_path= multi.getFilesystemName(file1);
		            
		            
		            //image_path = image_path + "\\" + multi.getFilesystemName(file1);
		            if(image_path == null)
	        		{
	        			image_path = "null.jpg";
	        			break;
	        		}
		        }
		        
		        
		        String array[] = contents.split(" ");
		        
		        for(int i=0;i<array.length;i++)
		        {
		        	int idx = array[i].indexOf("#");
		        	if(idx > -1)
		        	{
		        		set.add(array[i].substring(idx+1));
		        	}
		        }
		        	        
		       dto.setUser_id(user_id);
		       dto.setContents(contents);
		       dto.setImage_path(image_path);
		       dto.setAddress(address);
		       dto.setLatitude(latitude);
		       dto.setLongitude(longitude);
		       
		       newsfeed_id = dao.insertNewsFeed(dto);
		       	       
		        
		        if(newsfeed_id > 0) 
		        {
		        	if(set.size() > 0)
		        		dao.insertHashTag(set, newsfeed_id);
		        	int check = dao.updateMyFeedNum(user_id);
		         }
		        
		        request.setAttribute("user_id",user_id);
		    }catch(Exception e){
		        e.printStackTrace();
		    }
	}

}
