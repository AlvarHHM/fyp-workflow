//after select database, insert table into combobox

$(function(){
	hideDBValidation(0);
	
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
						json.databases,function(index, values){
							outStr += "<option class='option-removeable' value='"+table[i]+"'"
											+">"+table[i]+"</option>";
					});
					$("#db-table").html(outStr);
				}
			);
			for(var i=0;i<table.length;i++){
				$("#db-table").append("<option class='option-removeable' value='"+table[i]+"'"
					+">"+table[i]+"</option>");
			}
	
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
		var dbID=$("#db-db").val();
		var field=new Array();
		field[0]="date";//for demo
		field[1]="type";
		field[2]="remark1";
		field[3]="remark2";
		//get db table list
		//table getDBTableList(dbID);
   
		//insert table list into db-table
		for(var i=0;i<field.length;i++){
			$("#db-field").append("<option value='"+field[i]+"'"
				+">"+field[i]+"</option>");
		}
	
		//show field combobox
		$("#li-field").css("display","");
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
		case 0: $("#li-table").css("display","none");
		case 1:$("#li-operator").css("display","none");
		case 2:$("#li-field").css("display","none");
		case 3:$("#li-value").css("display","none");
	}
}