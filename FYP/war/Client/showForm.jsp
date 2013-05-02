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
	<script type="text/javascript" src="js/JQ/jquery-1.9.0.js"></script>
	<title>Form</title>
	<script type="text/javascript">
		var version = "<%= form.getVersion() %>";
		var formId = "<%= form.getFormID() %>";
		var data = new array();
		 $(function() {
				$(".date-picker").datepicker();
        });
		function SubmitApplication(){
			
			<%
			if(form==null){
				%>
					alert("Error. Form not found.");
				<%
			}else{
				%>
					$('.form-item').each(function(index) {
						var id = $(this).attr(id);
						var temp = new Array()
						var itemType = id.split('-')[1];
						
						temp["Label"] = "";
						temp["Value"] = "";
						switch (true) {
							case (/TEXTFIELD/) .test(itemType):
							case (/DATE/) .test(itemType):
								temp["Label"] = $(this).find("label.label").html();
								temp["value"] = $(this).find("input[type=text]").val();
								break;
							case (/RADIOBUTTON/) .test(itemType):
								temp["Label"] = $(this).find("legend.label").html();
								temp["value"] = 
										$(this).find("input[type=radio]:checked")
															.next("label").html();
								break;
							case (/CHECKBOX/) .test(itemType):
								temp["Label"] = $(this).find("legend.label").html();
								$(this).find("input[type=checkbox]:checked").each(function(){
									temp["value"] += $(this).next("label").html();
								});
								break;
							case (/COMBOBOX/) .test(itemType):
								temp["Label"] = $(this).find("label.label").html();
								temp["value"] = 
										$(this).find("option:selected").html();
								break;
							case (/TEXTAREA/) .test(itemType):
								temp["Label"] = $(this).find("label.label").html();
								temp["value"] = $(this).find("textarea").val();
								break;
							default:
								break;
						}
						data[id] = temp;
					});
					$.post("http://localhost:8888/Client/applyApplication"
					,{
						FormID : formId,
						Version : version,
						Data : data
					.always(function(data) {
						alert(data);
					});
				<%
			}
			%>
		}
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
			<tr>
				<td><button onclick="SubmitApplication()">Submit</button></td>
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