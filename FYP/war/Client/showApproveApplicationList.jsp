<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="edu.fyp.bean.Application"%>
<%@page import="edu.fyp.bean.Form"%>
<%@page import="edu.fyp.bean.Employee"%>
<%@page import="com.google.appengine.api.datastore.KeyFactory"%>

<%
//Get form list
ArrayList<Form> formList = (ArrayList<Form>)request.getSession().getAttribute("formList");
ArrayList<Application> appList = (ArrayList<Application>)request.getSession().getAttribute("appList");
ArrayList<Employee> empList = (ArrayList<Employee>)request.getSession().getAttribute("empList");
SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

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
								<th>Form ID</th>
								<th>Form Version</th>
								<th>Applier</th>
								<th>Process flow</th>
								<th>Apply Date</th>
																<th>Status</th>
								<th>action</th>
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
					String appKeyStr = KeyFactory.keyToString(app.getKey());
					Form tempForm = null;
					Employee tempEmp = null;
					for(int j = 0 ; j < formList.size() ; j++){
						String formID = formList.get(j).getFormID();
						String version = formList.get(j).getVersion();
						String appFormID = app.getFormID();
						String appFormVersion = app.getVersion();
						if(formID.equalsIgnoreCase(appFormID) && version.equalsIgnoreCase(appFormVersion)){
							tempForm = formList.get(j);
						}
					}
					for( int j = 0 ; j < empList.size() ; j++){
						if(empList.get(j).getEmpId().equalsIgnoreCase(app.getEmpID())){
							tempEmp = empList.get(j);
						}
					}
					String name = tempEmp.getEngOtherName() + " " + tempEmp.getEngSurname();
					%>
							<tr>
								<td><%= tempForm.getTitle() %></td>
								<td><%= app.getFormID() %></td>
								<td><%= app.getVersion() %></td>
								<td><%= name %></td>
								<td><a href="/pathReadOnly?appKey=<%=KeyFactory.keyToString(app.getKey())%>">Path</a></td>
								<td><%= dateformat.format(app.getApplyDate()) %></td>
								<td><%= app.getStatus() %></td>
													<td><a href="/Client/showApproveApplicationServlet?appKey=<%= KeyFactory.keyToString(app.getKey()) %>" target="_blank">
					<img src="img/dc.png" width="30px" height="30px"/></a>
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
request.getSession().removeAttribute("appList");
%>