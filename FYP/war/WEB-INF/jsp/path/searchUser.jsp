<html>
<head>
<link rel="stylesheet"
	href="/css/jquery.mobile-1.3.1.min.css" />
<script src="/js/jquery-1.9.1.min.js"></script>
<script
	src="/js/jquery.mobile-1.3.1.min.js"></script>
<script>
	$(document).ready(function() {
		$("#search-input").keyup(function() {
			$.ajax({
				type : "GET",
				url : "/searchUser.do",
				data : {
					queryString : $("#search-input").val()
				},
				success : function(msg) {
					refreshResult(msg);
				}

			});
		});
	});
	
	function refreshResult(object){
		$("#user-list").empty();
		console.log(object);
		var result = JSON.parse(object);
		console.log(result);
		$("#user-list").append($("<li data-role=\"list-divider\" role=\"heading\">Employee</li>"));
		$.each(result,function(i,e){
			console.log(e);
			var item = $("<li></li>");
			item.attr("data-theme","f");
			var itemLink = $("<a></a>");
			itemLink.attr("href","#");
			itemLink.attr("data-transition","slide");
			itemLink.click(function(){
				returnValue(e.empId);
			}); 
			itemLink.html(e.engOtherName+" "+e.engSurname+", "+e.nickName);
			item.append(itemLink);
			console.log(item);
			$("#user-list").append(item);
			
		});
		$("#user-list").listview("refresh");
	}

	function returnValue(value) {
		userId.value = value;
		top.close();
		return false;
	}
</script>
</head>
<body>
	<div data-role="page" id="page1">
		<div data-role="content">
			<div data-role="fieldcontain">
				<input name="" id="search-input" placeholder="" value=""
					type="search">
			</div>
			<ul id="user-list" data-role="listview" data-divider-theme="c"
				data-inset="true">
				<li data-role="list-divider" role="heading">Employee</li>
				<li data-theme="f"><a href="#page1" data-transition="slide"
					onClick=""> Please enter search key </a></li>

			</ul>
		</div>
	</div>

</body>
</html>