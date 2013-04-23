$(document).ready(function(){
	
	$("#approval-type").change(function(){
		switch($("#approval-type").val()){
		case "spec":
			$("#approval-userId-warpper").show();
			break;
		case "super":
			$("#approval-userId-warpper").hide();
			break;
		case "lu":
			$("#approval-userId-warpper").hide();
			break
		case "ld":
			$("#approval-userId-warpper").hide();
			break;
			
		}
	});
	
	$("#approval-property .property-submit-btn").click(function(){
		var props = $("#property-panel").data("props");
		
		switch($("#approval-type").val()){
		case "spec":
			props.use = 123123;
			break;
		}
	});
	
	



});