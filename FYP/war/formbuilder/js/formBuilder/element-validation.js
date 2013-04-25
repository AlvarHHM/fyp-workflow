//after select database, insert table into combobox
var constraint = "";
$(function(){

	hideDBValidation(0);
	
	$.getJSON(
		"databaseConnection.json", 
		function(json){
			var outStr = "<option value=''>Database</option>";
			$.each(
				json.databases,function(index, values){
					outStr += "<option value='"+values.id+"'>"+values.name+"</option>";
			});
			$("#validation-db").html(outStr);
		}
	);
	$("#validation-db").change(function(){
		//hide validation field
		hideDBValidation(0);
		
		//Get table ID
		var dbID=$(this).val();
		
		if(dbID!=null&dbID!=""){
			//get db table list
			getDBTableList(dbID);
			
			//show table combobox
			$("#li-validation-table").css("display","");
		}
	});
	
//after select table, insert field into combox
	$("#validation-table").change(function(){
		//hide validation field
		hideDBValidation(1);
		//init table
		//var dbID=$("#validation-db").val();
		var tableID=$(this).val();
		
		if(tableID!=null&tableID!=""){
			//get db table list
			//table getDBTableList(dbID);
			
			$.getJSON(
				"databaseTableCol.json", 
				function(json){
					var outStr = "<option value=''>Column</option>";
					$.each(
						json.columns,function(index, values){
							outStr += "<option class='option-removeable'"
											+" value='"+values.name+"'>"
											+values.name+"</option>";
					});
					$("#validation-field").html(outStr);
				}
			);
	
			//show field combobox
			$("#li-validation-field").css("display","");
		}
	});
	
	$("#validation-field").change(function(){
		//hide validation field
		hideDBValidation(2);
		
		$("#li-validation-operator").css("display","");
	}) ;
	
	$("#validation-operator").change(function(){
		$("#li-validation-value").css("display","");
	}) ;
	
	
});
//get table list
function getDBTableList(dbID){
	//insert table list into validation-table
	$.getJSON(
		"databaseTable.json", 
		function(json){
			var outStr = "<option value=''>Table</option>";
			$.each(
				json.tables,function(index, values){
					outStr += "<option class='option-removeable'"
									+" value='"+values.name+"'>"
									+values.name+"</option>";
				});
				$("#validation-table").html(outStr);
			}
		);
}
//get table field
function getTableField(dbID,table){
	//implement error
}
//hide table, operator, value
function hideDBValidation(level){  
	switch(level){
		case 0:$("#li-validation-database").css("display","none");
		case 1:$("#li-validation-field").css("display","none");
		case 2:$("#li-validation-operator").css("display","none");
		case 3:$("#li-validation-value").css("display","none");
	}
}