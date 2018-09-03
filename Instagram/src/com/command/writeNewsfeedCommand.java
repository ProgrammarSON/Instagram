package com.command;

import java.io.File;
import java.util.Enumeration;
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
			int maxSize = 1024 * 1024 * 10; // ï¿½Ñ¹ï¿½ï¿½ï¿½ ï¿½Ã¸ï¿½ ï¿½ï¿½ ï¿½Ö´ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ë·® : 10Mï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
		     
		    int check = 0;
		    
		    HttpSession session = request.getSession();
		    String user_id = (String)session.getAttribute("id");
		    String contents = "";
		    String image_path="";
		    feedDTO dto = new feedDTO();
		    feedDAO dao =feedDAO.getinstance();
		    
		    String fileName1 = ""; // ï¿½ßºï¿½Ã³ï¿½ï¿½ï¿½ï¿½ ï¿½Ì¸ï¿½
		    String originalName1 = ""; // ï¿½ßºï¿½ Ã³ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½Ì¸ï¿½
		    long fileSize = 0; // ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
		    String fileType = ""; // ï¿½ï¿½ï¿½ï¿½ Å¸ï¿½ï¿½
		     
		    
		    MultipartRequest multi = null;
		     
		    try{
		        // request,ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½,ï¿½ë·®,ï¿½ï¿½ï¿½Úµï¿½Å¸ï¿½ï¿½,ï¿½ßºï¿½ï¿½ï¿½ï¿½Ï¸ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½âº» ï¿½ï¿½Ã¥
		        multi = new MultipartRequest(request,uploadPath,maxSize,"utf-8",new DefaultFileRenamePolicy());
		       		    
		        contents = multi.getParameter("contents");
		         
		        // ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½Ã¼ ï¿½ï¿½ï¿½ï¿½ï¿½Ì¸ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
		        Enumeration files = multi.getFileNames();
		         
		        while(files.hasMoreElements())
		        {
		            // form ï¿½Â±×¿ï¿½ï¿½ï¿½ <input type="file" name="ï¿½ï¿½ï¿½â¿¡ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ì¸ï¿½" />ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½Â´ï¿½.
		            String file1 = (String)files.nextElement(); // ï¿½ï¿½ï¿½ï¿½ inputï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ì¸ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
		            
		            image_path= multi.getFilesystemName(file1);
		            //image_path = image_path + "\\" + multi.getFilesystemName(file1);
		            originalName1 = multi.getOriginalFileName(file1);
		            System.out.println(image_path);
		           
		            File file = multi.getFile(file1);
		            
		            fileSize = file.length();
		        }
		        
		        
		        String array[] = contents.split(" ");
		        
		        for(int i=0;i<array.length;i++)
		        {
		        	int idx = array[i].indexOf("#");
		        	if(idx > -1)
		        	{
		        		System.out.println(array[i].substring(idx+1));
		        		//DB¿¡ ¾î¶»°Ô È¿À²ÀûÀ¸·Î ³ÖÀ»±î?
		        	}
		        }
		        	
		        
		        dto.setUser_id(user_id);
		        dto.setContents(contents);
		        dto.setImage_path(image_path);
		        check = dao.insertNewsFeed(dto);
		        
		        if(check > 0) {
		        	check = dao.updateMyFeedNum(user_id);
		        }
		        
		        request.setAttribute("user_id",user_id);
		    }catch(Exception e){
		        e.printStackTrace();
		    }
	}

}
