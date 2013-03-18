//after select database, insert table into combobox

$(function(){
	$.getJSON(
		"databaseConnection.json", 
		function(json){
			var outStr = "<option></option>";
			$.each(
				json.databases,function(index, values){
					outStr += "<option value='"+values.id+"'>"+values.name+"</option>";
			});
			$("#db-db").html(outStr);
		}
	);
	hideDBValidation(0);
	
	$("#db-db").change(function(){
		//hide validation field
		hideDBValidation(0);
		
		//init table
		var dbID=$(this).val();
		
		//table[0]="vacation";
		//for demo
		
		//get db table list
		//table=getDBTableList(dbID);
		if(dbID){
		//insert table list into db-table
			$.getJSON(
				"databaseTable.json", 
				function(json){
					var outStr = "<option></option>";
					$.each(
						json.tables,function(index, values){
							outStr += "<option class='option-removeable'"
											+" value='"+values.name+"'>"
											+values.name+"</option>";
					});
					$("#db-table").html(outStr);
				}
			);
	
		//show table combobox
			$("#li-table").css("display","");
		}
	});
});
//after select table, insert field into combox
$(function(){
	$("#db-table").change(function(){
		//hide validation field
		hideDBValidation(1);
		//init table
		//var dbID=$("#db-db").val();
		var tableID=$(this).val();
		
		if(tableID){
			//get db table list
			//table getDBTableList(dbID);
			
			$.getJSON(
				"databaseTableCol.json", 
				function(json){
					var outStr = "<option></option>";
					$.each(
						json.columns,function(index, values){
							outStr += "<option class='option-removeable'"
											+" value='"+values.name+"'>"
											+values.name+"</option>";
					});
					$("#db-field").html(outStr);
				}
			);
	
			//show field combobox
			$("#li-field").css("display","");
		}
	});
});
$(function(){
	$("#db-field").change(function(){
		//hide validation field
		hideDBValidation(2);
		
		$("#li-operator").css("display","");
	}) ;
});
$(function(){
	$("#db-operator").change(function(){
		$("#li-value").css("display","");
	}) ;
});
//get table list
function getDBTableList(dbID){
	//unimplement error
}
//get table field
function getTableField(dbID,table){
	//implement error
}
//hide table, operator, value
function hideDBValidation(level){  
	switch(level){
		case 0:$("#li-table").css("display","none");
		case 1:$("#li-operator").css("display","none");
		case 2:$("#li-field").css("display","none");
		case 3:$("#li-value").css("display","none");
	}
}