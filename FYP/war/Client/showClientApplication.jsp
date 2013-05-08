<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="edu.fyp.bean.Form"%>
    <%@page import="edu.fyp.bean.Application"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
Form form = (Form)request.getSession().getAttribute("form");
Application app = (Application)request.getSession().getAttribute("app");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%= app.getApplyDate() %>
AAA-- -- as- -a-
<%= form.getFormID() %>
</body>
</html>