package com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.myfeed.*;

import net.sf.json.JSONArray;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class searchUserIDCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		myfeedDAO dao = myfeedDAO.getinstance();
		List<myfeedDTO> list = dao.getSearch("abc");
		
		PrintWriter out;
		
		try {
			out = response.getWriter();
			out.print(JSONArray.fromObject(list).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	

}
