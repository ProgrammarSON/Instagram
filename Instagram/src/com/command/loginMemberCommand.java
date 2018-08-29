package com.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.member.*;

/**
 * Î°úÍ∑∏?ù∏ ?ûë?óÖ?ùÑ Ï≤òÎ¶¨?ïò?äî Action ?Å¥?ûò?ä§
 */
public class loginMemberCommand implements Command
{
 		@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	
        //HttpSession session=request.getSession();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String user_id = "";        
        int check = 0;
        memberDAO dao = memberDAO.getInstance();
       check = dao.checkMember(email, password);
        System.out.println("check = " +check);
        
        
        if(check == memberDAO.MEMBER_NONEXISTENT) request.setAttribute("state", 0);                            
        
        else if(check == memberDAO.MEMBER_LOGIN_IS_NOT) request.setAttribute("state", -1);
        
        else
        {
        	user_id = dao.getUserid(email);        	
        	request.setAttribute("state", 1);
        	request.setAttribute("id",email);
        	request.setAttribute("user_id", user_id);
        }      
        
        System.out.println(check);
 	}	
      
}
 


