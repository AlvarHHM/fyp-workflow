<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="edu.fyp.bean.Form"%>
<%@page import="com.google.appengine.api.datastore.KeyFactory" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
//Get form
Form form = (Form)request.getSession().getAttribute("form");
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="js/libs/jquery-1.8.2/jquery.js"></script>
	<script type="text/javascript" src="js/libs/jqueryui-1.9.2/jquery-ui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/showForm.css">
	<link rel="stylesheet" type="text/css" href="css/jqueryui/jquery-ui-1.9.2.custom.min.css">
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<title>Form</title>
	<script type="text/javascript">
		var version = "<%= form.getVersion() %>";
		var formId = "<%= form.getFormID() %>";
		var formKey = "<%= KeyFactory.keyToString(form.getKey())%>";
		var selectedUpload = "";
		
		 $(function() {
			$(".hasDatepicker").removeClass("hasDatepicker");
			$(".date-picker").datepicker();
				
			/*	For file validation only.
			$('.form-item .upload :file').change(function() {
				var file = this.files[0];
				name = file.name;
				size = file.size;
				type = file.type;
			});
			*/
			$('.form-item .upload button').click(function() {
					var item = $(this).parents(".form-item");
					var formData = new FormData(item.find('form')[0]);
					
					$.ajax({
						url : '/uploadDoc',
						type : 'POST',
						
						xhr : 	function() {
							var myXhr = $.ajaxSettings.xhr();
							
							if (myXhr.upload) {
								myXhr.upload.addEventListener(
										'progress',
											(function(item){
												return function(e){
													if (e.lengthComputable) {
														item.find('progress').attr({
															value : e.loaded,
															max : e.total
														});
													}
												}
											})(item)
										,false); 
							}
							return myXhr;
						},
						
						success : 	function(e) {
										console.log(e);
									},
						data : formData,
						cache : false,
						contentType : false,
						processData : false
					});
					return false;
				});
        });
		function SubmitApplication(){
			
			<%
			if(form==null){
				%>
					alert("Error. Form not found.");
				<%
			}else{
				%>
					var data = new Array();
					var i = 0;
					$('.form-item').each(function() {
						var itemType = this.id.split('-')[0];
						
						if(!((/HEADING/).test(itemType)|(/LABEL/).test(itemType))){
							var temp = new Object();
							temp.Id = this.id;
							temp.Value = "";
							
							switch (true) {
								case (/TEXTFIELD/) .test(itemType):
								case (/DATE/) .test(itemType):
									temp.Value = $(this).find("input[type=text]").val();
									break;
								case (/RADIOBUTTON/) .test(itemType):
									var choice = $(this).find("input[type=radio]:checked").parents(".choice");
									temp.Value = $(this).find(".choice").index(choice);
								break;
								case (/CHECKBOX/) .test(itemType):
									$(this).find("input[type=checkbox]:checked").each(function(){
										var choice = $(this).parents(".choice");
										temp.Value += $(this).parents(".form-item").find(".choice").index(choice)+",";
									});
									break;
								case (/COMBOBOX/) .test(itemType):
									temp.Value = $(this).find("select")[0].selectedIndex;
									break;
								case (/TEXTAREA/) .test(itemType):
									temp.Value = $(this).find("textarea").val();
									break;
								case (/UPLOAD/) .test(itemType):
									temp.Value = $(this).find("input.uploaded-file").val();
									temp.FileName = "";
									
									var fullPath = $(this).find("input[type=file]").val();
									if (fullPath) {
										var startIndex = (fullPath.indexOf('\\') >= 0 ? fullPath.lastIndexOf('\\') : fullPath.lastIndexOf('/'));
										var filename = fullPath.substring(startIndex);
										if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0) {
											filename = filename.substring(1);
										}
										temp.FileName = filename;
									}
									
									break;
								default:
									break;
							}
							data[i] = temp;
							i++;
						}
					});
					$.post("/Client/applyApplication"
					,{
						FormID : formId,
						FormKey: formKey,
						Version : version,
						Data : JSON.stringify(data)})
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
					<td><%= form.getFormID() %></td>
				</tr>
				<tr>
					<td class="form_detail_left">Version:</td>
					<td><%= form.getVersion() %></td>
				</tr>
				<tr>
					<td class="form_detail_left">Title:</td>
					<td><%= form.getTitle() %></td>
				</tr>
				<tr>
					<td class="form_detail_left">Description:</td>
					<td><%= form.getDescription() %></td>
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