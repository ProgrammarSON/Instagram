package com.command;

import java.io.File;
import java.util.Enumeration;
import com.newsfeed.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;



public class writeNewsfeedCommand implements Command{

	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		   	//String uploadPath = "C:\\JSP\\Instagram\\Instagram\\WebContent\\feed_image";
		 	//String image_path = request.getRealPath("/feed_image");
		 	String uploadPath = "C:\\JSP\\Instagram\\Instagram\\WebContent\\feed_image";
		    int maxSize = 1024 * 1024 * 10; // 한번에 올릴 수 있는 파일 용량 : 10M로 제한
		     
		    int check = 0;
		    
		    String user_id = "";
		    String contents = "";
		    String image_path="";
		    feedDTO dto = new feedDTO();
		    feedDAO dao =feedDAO.getinstance();
		    
		    String fileName1 = ""; // 중복처리된 이름
		    String originalName1 = ""; // 중복 처리전 실제 원본 이름
		    long fileSize = 0; // 파일 사이즈
		    String fileType = ""; // 파일 타입
		     
		    
		    MultipartRequest multi = null;
		     
		    try{
		        // request,파일저장경로,용량,인코딩타입,중복파일명에 대한 기본 정책
		        multi = new MultipartRequest(request,uploadPath,maxSize,"utf-8",new DefaultFileRenamePolicy());
		         
		        // form내의 input name="name" 인 녀석 value를 가져옴
		        user_id = multi.getParameter("user_id");
		        // name="subject" 인 녀석 value를 가져옴
		        contents = multi.getParameter("contents");
		         
		        // 전송한 전체 파일이름들을 가져옴
		        Enumeration files = multi.getFileNames();
		         
		        while(files.hasMoreElements())
		        {
		            // form 태그에서 <input type="file" name="여기에 지정한 이름" />을 가져온다.
		            String file1 = (String)files.nextElement(); // 파일 input에 지정한 이름을 가져옴
		            
		            image_path= multi.getFilesystemName(file1);
		            //image_path = image_path + "\\" + multi.getFilesystemName(file1);
		            originalName1 = multi.getOriginalFileName(file1);
		            System.out.println(image_path);
		           
		            File file = multi.getFile(file1);
		            
		            fileSize = file.length();
		        }
		        
		        dto.setUser_id(user_id);
		        dto.setContents(contents);
		        dto.setImage_path(image_path);
		        dao.insertNewsFeed(dto);
		        
		        request.setAttribute("user_id",user_id);
		    }catch(Exception e){
		        e.printStackTrace();
		    }
	}

}
