<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.newsfeed.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	//feedDAO dao = new feedDAO();
	//LinkedHashMap<String,feedDTO> map = dao.getNewsFeed("sjw");
	LinkedHashMap<String,feedDTO> map =(LinkedHashMap<String,feedDTO>) request.getAttribute("map"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form>
		<table border=1>
		<%for(String key : map.keySet()){%>
			<tr>
				<td><%=map.get(key).getUser_id()%></td>
				<td><%=map.get(key).getContents() %></td>
			</tr>
			<tr><td>댓글 </td><td>내용</td></tr>
		<%  
			for(reply r : map.get(key).getReplys())
			{%>
				<tr>
					<td><%=r.getId() %></td><td><%=r.getComment() %></td>
				</tr>	
		<%  }%>
			<tr><td>-- </td><td>-- </td></tr>
			<tr><td>-- </td><td>-- </td></tr>
		<%}%>
		
		</table>
	</form>
</body>
</html>