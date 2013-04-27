<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="edu.fyp.bean.Form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
//Get form
Form form = (Form)request.getSession().getAttribute("form");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" type="text/css" href="css/showForm.css">
<title>Form</title>
</head>
<body>
<div class="form_container">
<%
if(form==null){
	%>
	Error. Form not found.
	<%
}else{
	%>
	<%= form.getFormHtml() %>
	<%
}
%>
</div>
<%
if(form!=null){
	%>
	<div class="control_panel">
	<div class="zoom"></div>
	<div class="form_detail">
		<table class="form_detail_table">
		<tbody>
			<tr>
				<td class="form_detail_left">Form ID:</td>
				<td><%= form.getFormID() %>
			</tr>
			<tr>
				<td class="form_detail_left">Version:</td>
				<td><%= form.getVersion() %>
			</tr>
			<tr>
				<td class="form_detail_left">Title:</td>
				<td><%= form.getTitle() %>
			</tr>
			<tr>
				<td class="form_detail_left">Description:</td>
				<td><%= form.getDescription() %>
			</tr>
			<tr>
						<td><button>Submit</button></td>
			<td></td>
			</tr>
		</tbody>
		</table>
	</div>
	</div>
	<%
}
%>
</body>
</html>
<% 
request.getSession().removeAttribute("form");
%>