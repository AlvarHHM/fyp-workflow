$(function(){

});
function showValidation(){
	var item = $(".selected");
    var itemType = item.attr("id").split('-')[0].toUpperCase();
	
	$("#validation>div").hide();
	$("#validation input").unbind("change keyup input paste");
	
    switch (true) {
        case (/CHECKBOX/).test(itemType):
			//Validate required on checkbox is buggy
			break;
			
        case (/TEXTFIELD/).test(itemType):
			validateEmail(item,itemType);
			validateNumber(item,itemType);
			validateMinLength(item,itemType);
			validateMaxLength(item,itemType);
			
			$("#validate-required>input").bind("change",function(){
				if($(this).is(":checked")){
					$("#validate-max-len").show();
					$("#validate-min-len").show();
				}else{
					$("#validate-max-len").hide();
					$("#validate-min-len").hide();
				}
			
			});
			
        case (/UPLOAD/).test(itemType):
        case (/DATE/).test(itemType):
			$("#validate-required").show();
			if(item.find("input").hasClass("required")){
				$("#validate-required>input[type=checkbox]").attr("checked", true);
			}else{
				$("#validate-required>input[type=checkbox]").attr("checked", false);
			}
				
			$("#validate-required>input").bind("change",{i:item},function(e){
				if($(this).is(":checked")){
					e.data.i.find("input").addClass("required");
				}else{
					e.data.i.find("input").removeClass("required");
				}
			
			});
			break;
        case (/TEXTAREA/).test(itemType):
			validateMinLength(item,itemType);
			validateMaxLength(item,itemType);
			
			$("#validate-required").show();
			if(item.find("textarea").hasClass("required")){
				$("#validate-required>input[type=checkbox]").attr("checked", true);
			}else{
				$("#validate-required>input[type=checkbox]").attr("checked", false);
			}
				
			$("#validate-required>input").bind("change",{i:item},function(e){
				if($(this).is(":checked")){
					e.data.i.find("textarea").addClass("required");
				}else{
					e.data.i.find("textarea").removeClass("required");
				}
			
			});
			break;
        case (/RADIOBUTTON/).test(itemType):
			$("#validate-required").show();
			
			if(item.find(".choice:first input[type=radio]").hasClass("required")){
				$("#validate-required>input[type=checkbox]").attr("checked", true);
			}else{
				$("#validate-required>input[type=checkbox]").attr("checked", false);
			}
				
			$("#validate-required>input").bind("change",{i:item},function(e){
				if($(this).is(":checked")){
					e.data.i.find(".choice:first input[type=radio]").addClass("required");
				}else{
					e.data.i.find(".choice:first input[type=radio]").removeClass("required");
				}
			
			});
        case (/COMBOBOX/).test(itemType):
			$("#validate-required").show();
			
			if(item.find("select").hasClass("required")){
				$("#validate-required>input[type=checkbox]").attr("checked", true);
			}else{
				$("#validate-required>input[type=checkbox]").attr("checked", false);
			}
				
			$("#validate-required>input").bind("change",{i:item},function(e){
				if($(this).is(":checked")){
					e.data.i.find("select").addClass("required");
				}else{
					e.data.i.find("select").removeClass("required");
				}
			
			});
			
		case (/LABEL/).test(itemType):
        case (/HEADING/).test(itemType):
        default:
            break;
	}
}
function validateEmail(item,itemType){
	$("#validate-email").show();
	if(item.find("input").hasClass("email")){
		$("#validate-email>input[type=checkbox]").attr("checked", true);
	}else{
		$("#validate-email>input[type=checkbox]").attr("checked", false);
	}
		
	$("#validate-email>input").bind("change",{i:item},function(e){
		if($(this).is(":checked")){
			e.data.i.find("input").addClass("email");
		}else{
			e.data.i.find("input").removeClass("email");
		}
	
	});
}
function validateNumber(item){
	$("#validate-number").show();
	
	if(item.find("input").hasClass("digit")){
		$("#validate-number>input[type=checkbox]").attr("checked", true);
	}else{
		$("#validate-number>input[type=checkbox]").attr("checked", false);
	}
		
	$("#validate-number>input").bind("change",{i:item},function(e){
		if($(this).is(":checked")){
			e.data.i.find("input").addClass("digit");
		}else{
			e.data.i.find("input").removeClass("digit");
		}
	
	});
}
function validateMaxLength(item){
    var attr = item.find("input").attr("maxLength");

	if(typeof attr !== 'undefined' && attr !== false){
		$("#validate-max-len>input[type=checkbox]").attr("checked", true);
		$("#validate-max-len input[type=text]").val(item.find("input,textarea").attr("maxLength"));
	}else{
		$("#validate-max-len>input[type=checkbox]").attr("checked", false);
		$("#validate-max-len input[type=text]").val("");
			$("#validate-max-len input[type=text]").prop('disabled', true);
	}
		
	$("#validate-max-len>input[type=checkbox]").bind("change",{i:item},function(e){
		if($(this).is(":checked")){
			$("#validate-max-len input[type=text]").prop('disabled', false);
			e.data.i.find("input,textarea").attr("maxLength","10");
			
			$("#validate-max-len input[type=text]").val("10");
			$("#validate-max-len input[type=text]").bind("keyup input paste",
			{i:e.data.i},
			function(e){
				e.data.i.find("input,textarea").attr("maxLength",$("#validate-max-len input[type=text]").val());
			});
			
		}else{
			$("#validate-max-len input[type=text]").unbind("keyup input paste");
			$("#validate-max-len input[type=text]").prop('disabled', true);
			$("#validate-max-len input[type=text]").val("");
			e.data.i.find("input,textarea").attr("maxLength",false);
		}
	
	});
}
function validateMinLength(item){
    var attr = item.find("input").attr("minLength");

	if(typeof attr !== 'undefined' && attr !== false){
		$("#validate-min-len>input[type=checkbox]").attr("checked", true);
		$("#validate-min-len input[type=text]").val(item.find("input,textarea").attr("minLength"));
	}else{
		$("#validate-min-len>input[type=checkbox]").attr("checked", false);
		$("#validate-min-len input[type=text]").val("");
			$("#validate-min-len input[type=text]").prop('disabled', true);
	}
		
	$("#validate-min-len>input[type=checkbox]").bind("change",{i:item},function(e){
		if($(this).is(":checked")){
			$("#validate-min-len input[type=text]").prop('disabled', false);
			e.data.i.find("input,textarea").attr("minLength","0");
			
			$("#validate-min-len input[type=text]").val("0");
			$("#validate-min-len input[type=text]").bind("keyup input paste",
			{i:e.data.i},
			function(e){
				e.data.i.find("input,textarea").attr("minLength",$("#validate-min-len input[type=text]").val());
			});
		}else{
			$("#validate-min-len input[type=text]").prop('disabled', true);
			$("#validate-min-len input[type=text]").unbind("keyup input paste");
			$("#validate-min-len input[type=text]").val("");
			e.data.i.find("input,textarea").attr("minLength",false);
		}
	
	});
}
