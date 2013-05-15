<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.fyp.bean.Form"%>
<%@page import="com.google.appengine.api.datastore.KeyFactory" %>
<%
	//Get form list
	ArrayList<Form> formList = (ArrayList) request.getSession()
			.getAttribute("formList");
%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/formbuilder/css/common.css">
	<link rel="stylesheet" type="text/css"
	href="/formbuilder/css/showBuilderFormList.css">
	<script type="text/javascript" src="/formbuilder/js/libs/jquery-1.8.2/jquery.js"></script>
	<script>
	$(function(){
		$.each($('select[name="status"]'),function(){
			var currentStatus= $(this).parent().find("input[name='currentStatus']").val();
			$(this).val(currentStatus);
			});
	});
	</script>
</head>
<body>
	<div id="bodyContainer">
		<div id="topContainer">
			<%@ include file="head.jsp"%>
		</div>
		<div id="mainContainer">
			<div id="menu">
				<%@ include file="menu.jsp"%>
			</div>
			<div id="mainBody">
				<fieldset>
					<legend align='center'>You can Maintain these Form</legend>
					<div class="searchField">
						<form method="get" action="/formbuilder/showBuilderFormListServlet">
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
								<th>Form ID</th>
								<th>Version</th>
								<th>Title</th>
								<th>Description</th>
								<th>Status</th>
								<th>Action</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="10"></td>
							</tr>
						</tfoot>
						<tbody>
							<%
								if (formList.size() == 0) {
							%>
							<tr>
								<td colspan="10">No form found.</td>
							</tr>
							<%
								} else {
									for (int i = 0; i < formList.size(); i++) {
										Form tempForm = formList.get(i);
							%>
							<tr>
								<td><%=tempForm.getFormID()%></td>
								<td><%=tempForm.getVersion()%></td>
								<td><%=tempForm.getTitle()%></td>
								<td><%=tempForm.getDescription()%></td>
								<td>
								<form method="get" action="/formbuilder/updateFromStatus">
								<select name="status">
									<option value="Editing">Editing</option>
									<option value="Release">Release</option>
									<option value="Disable">Disable</option>
								</select>
								<input type="hidden" name="currentStatus" value="<%= tempForm.getStatus() %>"/>
								<input type="hidden" name="formID" value="<%=tempForm.getFormID()%>"/>
								<input type="hidden" name="version" value="<%=tempForm.getVersion()%>"/>
								<input type="submit"/>
								</form>
								</td>
								<td><a
									href="showBuilderFormServlet?formID=<%=tempForm.getFormID()%>&version=<%=tempForm.getVersion()%>"
									target="_blank"> <img src="/formbuilder/img/dc.png" width="30px"
										height="30px" />
								</a> <a target="_blank" onclick='window.open("/path?formKey=<%=KeyFactory.keyToString(tempForm.getKey())%>")'
									style="cursor: pointer">Path</a>
								</td>
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