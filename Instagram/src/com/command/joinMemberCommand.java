package com.command;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import com.member.*;
import com.myfeed.*;

public class joinMemberCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String email="";
		String username ="";
		String user_id = "";
		String password = "";
		String profilePath = "";
		String contents = "";
		
		memberDTO member_dto = new memberDTO();
		memberDAO member_dao = memberDAO.getInstance();
		
		myfeedDTO myfeed_dto = new myfeedDTO();
		myfeedDAO myfeed_dao = myfeedDAO.getinstance();
		
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
	        contents = multi.getParameter("contents");
	        
	        // name="subject" �� �༮ value�� ������
	        	         
	        // ������ ��ü �����̸����� ������
	        Enumeration files = multi.getFileNames();
	         
	        if(files != null)
	        	while(files.hasMoreElements())
	        	{
	        	// form �±׿��� <input type="file" name="���⿡ ������ �̸�" />�� �����´�.
	        		String file1 = (String)files.nextElement(); // ���� input�� ������ �̸��� ������
	            
	        		profilePath= multi.getFilesystemName(file1);
	        		if(profilePath == null)
	        		{
	        			profilePath = "null.jpg";
	        			break;
	        		}
	        		//image_path = image_path + "\\" + multi.getFilesystemName(file1);
	        		originalName1 = multi.getOriginalFileName(file1);
	        		System.out.println(profilePath);
	           
	        		//File file = multi.getFile(file1);
	           	} 
	        
	        
	        member_dto.setEmail(email);
	        member_dto.setUsername(username);
	        member_dto.setUser_id(user_id);
	        member_dto.setPassword(password);
	       // dto.setProfileImg_path(profilePath);
	        
	        myfeed_dto.setUser_id(user_id);
	        myfeed_dto.setContents(contents);
	        myfeed_dto.setProfile_img(profilePath);
	        
	        check = member_dao.insertMember(member_dto);
	        if(check > 0) {
	        	check = myfeed_dao.insertMyfeed(myfeed_dto);
	        	check = myfeed_dao.insertFollow(user_id, user_id);
	        }
	        
	        request.setAttribute("state", check);
	        
	    }catch(Exception e){
	        e.printStackTrace();
	    }
			
	}	
}
