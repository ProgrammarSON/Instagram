package com.command;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.member.*;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class joinMemberCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String email="";
		String username ="";
		String user_id = "";
		String password = "";
		String profilePath = "";
		
		memberDTO dto = new memberDTO();
		memberDAO dao = memberDAO.getInstance();
		
		String uploadPath = request.getRealPath("/profile_image");
			 	
		System.out.println(uploadPath);
		int maxSize = 1024 * 1024 * 10; // �ѹ��� �ø� �� �ִ� ���� �뷮 : 10M�� ����
	    int check = 0;
	    	    
	    String fileName1 = ""; // �ߺ�ó���� �̸�
	    String originalName1 = ""; // �ߺ� ó���� ���� ���� �̸�
	    long fileSize = 0; // ���� ������
	    String fileType = ""; // ���� Ÿ��
	     
	    
	    MultipartRequest multi = null;
	     
	    try{
	        // request,����������,�뷮,���ڵ�Ÿ��,�ߺ����ϸ� ���� �⺻ ��å
	        multi = new MultipartRequest(request,uploadPath,maxSize,"utf-8",new DefaultFileRenamePolicy());
	         
	        // form���� input name="name" �� �༮ value�� ������
	        email = multi.getParameter("email");
	        username = multi.getParameter("username");
	        user_id = multi.getParameter("user_id");
	        password = multi.getParameter("password");
	        // name="subject" �� �༮ value�� ������
	        	         
	        // ������ ��ü �����̸����� ������
	        Enumeration files = multi.getFileNames();
	         
	        while(files.hasMoreElements())
	        {
	            // form �±׿��� <input type="file" name="���⿡ ������ �̸�" />�� �����´�.
	            String file1 = (String)files.nextElement(); // ���� input�� ������ �̸��� ������
	            
	            profilePath= multi.getFilesystemName(file1);
	            //image_path = image_path + "\\" + multi.getFilesystemName(file1);
	            originalName1 = multi.getOriginalFileName(file1);
	            System.out.println(profilePath);
	           
	            File file = multi.getFile(file1);
	            
	            fileSize = file.length();
	        }
	        
	        dto.setEmail(email);
	        dto.setUsername(username);
	        dto.setUser_id(user_id);
	        dto.setPassword(password);
	        dto.setProfileImg_path(profilePath);
	        
	        check = dao.insertMember(dto);
	        
	        request.setAttribute("state", check);
	        
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	}	
}
