<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="edu.fyp.bean.Application"%>
<%
//Get form list
ArrayList<Application> appList = (ArrayList)request.getSession().getAttribute("appList");
SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");

%>
<html>
<head>
<%@ include file="header.jsp"%>
</head>
<body>
	<div id="bodyContainer">
		<%@ include file="head.jsp"%>
		<div id="mainContainer">
			<%@ include file="menu.jsp"%>
			<div id="mainBody">
				<fieldset>
					<legend align='center'>Application</legend>
					<div class="searchField">
						<form method="get" action="showFormListServlet">
							<select name="search">
								<option value="formID">Form ID</option>
								<option value="title">Title</option>
							</select> <input type="text" name="keyword" id="Search"
								placeholder="input keyword here!" class="hint" /> <input
								type="submit" class="buttom" value="Search" />
						</form>
					</div>
					<table class="viewFormTable">
						<thead>
							<tr>
								<th>Form Name</th>
								<th>Process flow</th>
								<th>Apply Date</th>
								<th>Status</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="10"></td>
							</tr>
						</tfoot>
						<tbody>
							<% 
			if(appList.size()==0){
%>
							<tr>
								<td colspan="10">No Application.</td>
							</tr>
							<%
			} else{
				for(int i=0;i<appList.size();i++){
					Application app = appList.get(i);
					%>
							<tr>
								<td><%= app.getFormID() %></td>
								<td><%= "Path" %></td>
								<td><%= dateformat.format(app.getApplyDate()) %></td>
								<td><%= app.getStatus() %></td>
							</tr>
							<%
				}
			}
			%>
						</tbody>
					</table>
				</fieldset>

			</div>
		</div>
	</div>
</body>
</html>
<% 
request.getSession().removeAttribute("formList");
%>