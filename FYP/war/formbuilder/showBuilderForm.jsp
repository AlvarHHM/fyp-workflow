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
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/showForm.css">
    <link rel="stylesheet" type="text/css" href="css/jqueryui/jquery-ui-1.9.2.custom.min.css">
<link href="path/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<script type="text/javascript" src="js/libs/jquery-1.8.2/jquery.js"></script>
	<script type="text/javascript" src="js/libs/jqueryui-1.9.2/jquery-ui.min.js"></script>
	<title>Form</title>
	<script type="text/javascript">
		var version = "<%= form.getVersion() %>";
		var formId = "<%= form.getFormID() %>";
		var data = new Array();
		 $(function() {
				$(".date-picker").datepicker();
				$("button").click(function(){return false;})
        });
	</script>
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
		<%= form.getFormHtml().getValue() %>
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
			<!--
			<tr>
				<td><button onclick="SubmitApplication()">Submit</button></td>
			<td></td>
			</tr>
			-->
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