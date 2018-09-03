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
			int maxSize = 1024 * 1024 * 10; // �ѹ��� �ø� �� �ִ� ���� �뷮 : 10M�� ����
		     
		    int check = 0;
		    
		    HttpSession session = request.getSession();
		    String user_id = (String)session.getAttribute("id");
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
		        
		        
		        String array[] = contents.split(" ");
		        
		        for(int i=0;i<array.length;i++)
		        {
		        	int idx = array[i].indexOf("#");
		        	if(idx > -1)
		        	{
		        		System.out.println(array[i].substring(idx+1));
		        		//DB�� ��� ȿ�������� ������?
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
