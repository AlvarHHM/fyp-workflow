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
	<title>Application Display - <%= form.getTitle() %></title>
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/showApplication.css">
    <link rel="stylesheet" type="text/css" href="css/jqueryui/jquery-ui-1.9.2.custom.min.css">
	<script type="text/javascript" src="js/JQ/jquery-1.9.0.js"></script>
	<script type="text/javascript" src="js/JQ/jquery-ui-1.10.0.custom.min.js"></script>
	<script type="text/javascript">
		if(form!=null&&app!=null){
			var appData = "<%=app.getFormData()%>";
		}
	</script>
</head>
<body>

	<div class="application_container">
	<%
	if(app==null){
		%>
			<span>Error. Application not found.</span>
		</div>
		<%
	}else if(form!=null){
		%>
		<%= form.getFormHtml().getValue() %>
		</div>
		<div class="control_panel">
			<div class="zoom"></div>
			<div class="form_detail">
				<table class="app_detail_table">
					<tbody>
						<tr>
							<td class="app_detail_left">Title:</td>
							<td><%= form.getTitle() %></td>
						</tr>
						<tr>
							<td class="app_detail_left">Version:</td>
							<td><%= form.getVersion() %></td>
						</tr>
						<tr>
							<td class="app_detail_left">Apply Date:</td>
							<td><%= app.getApplyDate() %></td>
						</tr>
						<tr>
							<td class="app_detail_left">Description:</td>
							<td><%= form.getDescription() %></td>
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
request.getSession().removeAttribute("app");
request.getSession().removeAttribute("form");
%>