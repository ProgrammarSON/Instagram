<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- <jsp:include page="header.jsp"></jsp:include> --%>
    
<%
	session.invalidate();
	response.sendRedirect("../login.html");
%>
<!doctype html>
<html>
<head></head>
<body></body>
</html>   
<%-- 
<jsp:include page="footer.jsp"></jsp:include> --%>