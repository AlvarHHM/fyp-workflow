$(document).ready(function() {

	$("#approval-type").change(function() {
		switch ($("#approval-type").val()) {
		case "spec":
			$("#approval-userId-warpper").show();
			$("#approval-deptId-wrapper").hide();
			$("#approval-superLv-wrapper").hide();
			break;
		case "super":
			$("#approval-userId-warpper").hide();
			$("#approval-deptId-wrapper").hide();
			$("#approval-superLv-wrapper").hide();
			break;
		case "lud":
			$("#approval-userId-warpper").hide();
			$("#approval-deptId-wrapper").hide();
			$("#approval-superLv-wrapper").show();
			break
		case "ld":
			$("#approval-userId-warpper").hide();
			$("#approval-deptId-wrapper").show();
			$("#approval-superLv-wrapper").show();
			break;
		default:
			$("#approval-userId-warpper").hide();
			$("#approval-deptId-wrapper").hide();
			$("#approval-superLv-wrapper").hide();
			break;

		}
	});

	$("#approval-property .property-submit-btn").click(function() {
		var props = $("#property-panel").data("props");

		switch ($("#approval-type").val()) {
		case "spec":
			props.user = $("#approval-userId").val();
			props.dept = "";
			props.level = "";
			break;
		case "super":
			props.user = "";
			props.dept = "user";
			props.level = "super";
			break;
		case "lud":
			props.user = "";
			props.dept = "user"
			prop.level = $("#approval-superLv").val();
			break;
		case "ld":
			props.user = "";
			prop.dept = $("#approval-deptId").val();
			prop.level = $("#approval-superLv").val();
			break;

		}
	});

	$("#notice-type").change(function() {
		switch ($("#notice-type").val()) {
		case "email":
			$("#notice-userId-warpper").hide();
			$("#notice-email-warpper").show();
			break;
		case "system":
			$("#notice-userId-warpper").show();
			$("#notice-email-warpper").hide();
			break;
		default:
			$("#notice-userId-warpper").hide();
			$("#notice-email-warpper").hide();
			break;
		}
	});
	
	$("#notice-property .property-submit-btn").click(function() {
		var props = $("#property-panel").data("props");
		switch ($("#notice-type").val()) {
		case "email":
			props.email = $("#notice-type").val();
			break;
		case "system":
			props.user = $("#notice-type").val();
			break;

		}
	});
	
	

});