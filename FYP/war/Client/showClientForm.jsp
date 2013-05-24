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
<!-- 	<script type="text/javascript" src="js/libs/jquery-1.8.2/jquery.js"></script> -->
	<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="js/libs/jqueryui-1.9.2/jquery-ui.min.js"></script>
	<script type="text/javascript" src="js/libs/jquery.validate.js"></script>
	<script type="text/javascript" src="/js/jquery.blockUI.js"></script>
	<link rel="stylesheet" type="text/css" href="css/showForm.css">
	<link rel="stylesheet" type="text/css" href="css/jqueryui/jquery-ui-1.9.2.custom.min.css">
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<title>Form</title>
	<style type="text/css">
		.error{	
			color:red;
			position:absolute;
		}
	</style>
	<script type="text/javascript">
		var version = "<%= form.getVersion() %>";
		var formId = "<%= form.getFormID() %>";
		var formKey = "<%= KeyFactory.keyToString(form.getKey())%>";
		var uploadedFileName = "null";
		var uploadingFileName = "";
		var userSurName = "${sessionScope.EMP.engSurname}";
		var userNickName = "${sessionScope.EMP.nickName}";
		
		 $(function() {
			$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
			$(".hasDatepicker").removeClass("hasDatepicker");
			$(".date-picker").datepicker();
			$(".auto-name").val(userNickName+" "+userSurName);
			/*	For file validation only.
			$('.form-item .upload :file').change(function() {
				var file = this.files[0];
				name = file.name;
				size = file.size;
				type = file.type;
			});
			*/
			$('div[id^=UPLOAD] button').click(function() {
					
					var item = $(this).parents(".form-item");
					var formData = new FormData();
					formData.append("file",item.find("input[type=file]")[0].files[0]);
					
					var fullPath = item.find("input[type=file]").val();
						if (fullPath) {
							var startIndex = (fullPath.indexOf('\\') >= 0 ? fullPath.lastIndexOf('\\') : fullPath.lastIndexOf('/'));
							var filename = fullPath.substring(startIndex);
							if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0) {
								filename = filename.substring(1);
							}
							uploadingFileName = filename;
						}
					
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
						statusCode: {
							404: function() {
								alert("404: upload path fail!")
							},
							500: function() {
								alert("500: server internal error!");
							}
						},
						success : 	function(data) {
										var progress = item.find('progress');
										progress.attr("value",progress.attr("max"));
										
										uploadedFileName = uploadingFileName;
										item.find("input.uploaded-file").val(data);
										alert("File upload :"+uploadedFileName+" Succss.");
									},
						error: 		function(){
										alert("File upload :"+uploadingFileName+" Fail.");
										uploadedFileName = "null";
									},
						data : formData,
						cache : false,
						contentType : false,
						processData : false
					});
					return false;
				});
				$("#filling-form").validate();
				
				$("#filling-form")
					.unbind('submit')
					.bind('submit',function(){
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
				$("label.error").each(function(){
					var stage = $(".form_container");
					var parent = $(this).parents(".form-item");

					$(this).prepend("<-----")

					var left = stage.position().left + stage.width() - $(this).width()-parent.position().left;
					$(this).css("left",left+"px");
				});
				
				
				if($("#filling-form").valid()){
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
									temp.Value = "";
										if(uploadedFileName==uploadedFileName){
											var progress = $(this).find('progress');
											if(progress.attr("value")!=0){
												if(progress.attr("value")==progress.attr("max")){
													temp.Value = $(this).find("input.uploaded-file").val();
													temp.FileName = uploadedFileName;
												}
											}
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
						window.close();
						
					});
					
				}
				<%
			}
			%>
		}
	</script>
</head>
<body>
	<form id="filling-form">
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
	</form>
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
