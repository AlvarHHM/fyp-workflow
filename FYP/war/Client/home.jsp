<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="edu.fyp.bean.Application"%>
<%@page import="edu.fyp.bean.Employee"%>
<%@page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@page import="edu.fyp.bean.Form"%>
<%
	ArrayList<Application> appList = (ArrayList<Application>) request
			.getAttribute("appList");
	ArrayList<Application> approveAppList = (ArrayList<Application>) request
			.getAttribute("approveAppList");
	ArrayList<Form> formList = (ArrayList<Form>) request
			.getAttribute("formList");
	ArrayList<Form> approveFormList = (ArrayList<Form>) request.getSession().getAttribute("approveFormList" );
	ArrayList<Form> appFormList = (ArrayList<Form>) request.getSession().getAttribute("appFormList" );
	ArrayList<Employee> applierList = (ArrayList<Employee>) request
			.getAttribute("applierList");
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
				<fieldset class="approve_app_table">
					<legend align='center'>Latest Approve Applications</legend>
					<table class="viewFormTable">
						<thead>
							<tr>
								<th>Application ID</th>
								<th>Form Title</th>
								<th>Applier</th>
								<th>Apply Date</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</tfoot>
						<tbody>
							<%
							if (approveAppList!=null &&approveAppList.size() > 0) {
							%>
							<%
								for (int i = 0; i < approveAppList.size(); i++) {
										Application app = approveAppList.get(i);
										Employee emp = applierList.get(i);
										Form form = approveFormList.get(i);
							%>
							<tr>
								<td><a
									href="/Client/showApproveApplicationServlet?appKey=<%=KeyFactory.keyToString(app.getKey())%>"
									target="_blank"><%=app.getAppID()%></a></td>
								<td><%=form.getTitle()%></td>
								<td><%=emp.getEngOtherName() + " " + emp.getChiSurname()%></td>
								<td><%=app.getApplyDate()%></td>
							</tr>
							<%
								}
							}else{
								%>
							<tr>
								<td colspan="5">No Application needed to approve.</td>
							</tr>
							<%
							}
							%>
							<tr>
						</tbody>
					</table>
				</fieldset>

				<fieldset class="app_table">
					<legend align='center'>Latest Application</legend>
					<table class="viewFormTable">
						<thead>
							<tr>
								<th>Application ID</th>
								<th>Title</th>
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
							if (appList!=null &&appList.size() > 0) {
							%>
							<%
								for (int i = 0; i < appList.size(); i++) {
										Application app = appList.get(i);
										Form form = appForm.get(i);
							%>
							<tr>
								<td><a
									href="/Client/showClientApplicationServlet?formID=<%=app.getFormID()%>&version=<%=app.getVersion()%>&appKey=<%=KeyFactory.keyToString(app.getKey())%>"
									target="_blank"><%=app.getAppID()%></a></td>
							<td></td>
							<td></td>
							<td></td>
							</tr>
						</tbody>
					</table>
				</fieldset>

				<fieldset class="app_table">
					<legend>Recent Use</legend>
					<table>
						<tr>
							<td><a href="form.html" target="_blank">Day leave</a></td>
						</tr>
						<tr>
							<td><a href="#">Room booking</a></td>
						</tr>
						<tr>
							<td><a href="#">CAPS Application</a></td>
						</tr>
					</table>
				</fieldset>
			</div>
		</div>
	</div>
</body>
</html>
<%
	request.removeAttribute("appList");
	request.removeAttribute("approveAppList");
	request.removeAttribute("formList");
	request.removeAttribute("applierList");
%>