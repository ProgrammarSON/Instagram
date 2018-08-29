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
		 	//String uploadPath = "D:\\java\\git\\Instagram\\Instagram\\WebContent\\feed_image";
			String uploadPath = request.getRealPath("/feed_image");
			System.out.println(uploadPath);
			int maxSize = 1024 * 1024 * 10; // �ѹ��� �ø� �� �ִ� ���� �뷮 : 10M�� ����
		     
		    int check = 0;
		    
		    String user_id = "";
		    String contents = "";
		    String image_path="";
		    feedDTO dto = new feedDTO();
		    feedDAO dao =feedDAO.getinstance();
		    
		    String fileName1 = ""; // �ߺ�ó���� �̸�
		    String originalName1 = ""; // �ߺ� ó���� ���� ���� �̸�
		    long fileSize = 0; // ���� ������
		    String fileType = ""; // ���� Ÿ��
		     
		    
		    MultipartRequest multi = null;
		     
		    try{
		        // request,����������,�뷮,���ڵ�Ÿ��,�ߺ����ϸ� ���� �⺻ ��å
		        multi = new MultipartRequest(request,uploadPath,maxSize,"utf-8",new DefaultFileRenamePolicy());
		         
		        // form���� input name="name" �� �༮ value�� ������
		        user_id = multi.getParameter("user_id");
		        // name="subject" �� �༮ value�� ������
		        contents = multi.getParameter("contents");
		         
		        // ������ ��ü �����̸����� ������
		        Enumeration files = multi.getFileNames();
		         
		        while(files.hasMoreElements())
		        {
		            // form �±׿��� <input type="file" name="���⿡ ������ �̸�" />�� �����´�.
		            String file1 = (String)files.nextElement(); // ���� input�� ������ �̸��� ������
		            
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
