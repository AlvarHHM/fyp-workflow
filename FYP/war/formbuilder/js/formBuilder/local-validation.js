
$(function(){

});
function showValidation(){
	var item = $(".selected");
    var itemType = item.attr("id").split('-')[0].toUpperCase();
	
	$("#validation>div").hide();
	$("#validation input").unbind("change keyup input paste");
	
        case (/CHECKBOX/).test(itemType):
			//Validate required on checkbox is buggy
			break;
			
        case (/TEXTFIELD/).test(itemType):
			validateEmail(item,itemType);
			validateNumber(item,itemType);
			validateMinLength(item,itemType);
			validateMaxLength(item,itemType);
			
			$("#validate-required>input").bind("change",function(){
				if($(this).is(":checked"){
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
				
			$("#validate-required>input").bind("change",function(){
				if($(this).is(":checked"){
					item.find("input").addClass("required");
				}else{
					item.find("input").removeClass("required");
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
				
			$("#validate-required>input").bind("change",function(){
				if($(this).is(":checked"){
					item.find("textarea").addClass("required");
				}else{
					item.find("textarea").removeClass("required");
				}
			
			});
			break;
        case (/RADIOBUTTON/).test(itemType):
			$("#validate-required").show();
			
			if(item.find("choice:first input[type=radio]").hasClass("required")){
				$("#validate-required>input[type=checkbox]").attr("checked", true);
			}else{
				$("#validate-required>input[type=checkbox]").attr("checked", false);
			}
				
			$("#validate-required>input").bind("change",function(){
				if($(this).is(":checked"){
					item.find("choice:first input[type=radio]").addClass("required");
				}else{
					item.find("choice:first input[type=radio]").removeClass("required");
				}
			
			});
        case (/COMBOBOX/).test(itemType):
			$("#validate-required").show();
			
			if(item.find("select").hasClass("required")){
				$("#validate-required>input[type=checkbox]").attr("checked", true);
			}else{
				$("#validate-required>input[type=checkbox]").attr("checked", false);
			}
				
			$("#validate-required>input").bind("change",function(){
				if($(this).is(":checked"){
					item.find("select").addClass("required");
				}else{
					item.find("select").removeClass("required");
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
		
	$("#validate-email>input").bind("change",function(){
		if($(this).is(":checked"){
			item.find("input").addClass("email");
		}else{
			item.find("input").removeClass("email");
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
		
	$("#validate-number>input").bind("change",function(){
		if($(this).is(":checked"){
			item.find("input").addClass("digit");
		}else{
			item.find("input").removeClass("digit");
		}
	
	});
}
function validateMaxLength(item){
	if(item.find("input").hasAttr("maxLength")){
		$("#validate-max-len>input[type=checkbox]").attr("checked", true);
		$("#validate-max-len>input[type=text]").val(item.find("input").attr("maxLength"));
	}else{
		$("#validate-max-len>input[type=checkbox]").attr("checked", false);
	}
		
	$("#validate-number>input").bind("change",function(){
		if($(this).is(":checked"){
			$("#validate-max-len>input[type=text]").prop('disabled', false);
			$("#validate-max-len>input[type=text]").unbind("keyup input paste");
			
			$("#validate-max-len>input[type=text]").val("");
		}else{
			$("#validate-max-len>input[type=text]").prop('disabled', true);
			item.find("input").attr("maxLength","10");
			
			$("#validate-max-len>input[type=text]").val("10");
			$("#validate-max-len>input[type=text]").bind("keyup input paste",
			{i:item},
			function(e){
				e.data.i.attr("maxLength",$("#validate-max-len>input[type=text]").val());
			});
		}
	
	});
}
function validateMinLength(item){
	if(item.find("input").hasAttr("minLength")){
		$("#validate-number>input[type=checkbox]").attr("checked", true);
		$("#validate-number>input[type=text]").val(item.find("input").attr("minLength"));
	}else{
		$("#validate-number>input[type=checkbox]").attr("checked", false);
	}
		
	$("#validate-number>input").bind("change",function(){
		if($(this).is(":checked"){
			$("#validate-min-len>input[type=text]").prop('disabled', false);
			$("#validate-min-len>input[type=text]").unbind("keyup input paste");
			
			$("#validate-min-len>input[type=text]").val("");
		}else{
			$("#validate-min-len>input[type=text]").prop('disabled', true);
			item.find("input").attr("minLength","0");
			
			$("#validate-min-len>input[type=text]").val("0");
			$("#validate-min-len>input[type=text]").bind("keyup input paste",
			{i:item},
			function(e){
				e.data.i.attr("minLength",$("#validate-min-len>input[type=text]").val());
			});
		}
	
	});
}
