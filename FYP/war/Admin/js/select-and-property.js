var selected_item = "";
$(document).ready(function() {
	$("#form-design-container").click(function(e){
		if(e.target == this){
			$(".form-item.selected .remove-button").remove();
			$(".form-item.selected").removeClass('selected');
			$("#property-tabs ul li:nth-child(2)").hide();
			$( "#property-tabs" ).tabs( "option", "active", 0 );
		}
	});
	$(".sample-choice").hide();
	$("#property-tabs ul li:nth-child(2)").hide();
	
	//Code for reading the saved form
	/*
	$(".form-item").append(
		"<div class='overlay' ></div>");
	$(".form-item").draggable({
		containment: "parent",
		stop: function(event, ui){
			selectFormItem($(ui.draggable).attr("id"));
		}
	}).click(function(){
		selectFormItem($(this).attr("id"));
	});
	*/
});

function selectFormItem(itemId){
    
    var item = $("#"+itemId);
    var x = $(".form-item-property#X [type='text']");
    var y = $(".form-item-property#Y [type='text']");
    var label = $(".form-item-property#item-label [type='text']");
	
	$(".form-item.selected .remove-button").remove();
    $(".form-item.selected").removeClass('selected');
    item.addClass('selected');
	item.children(".overlay").append("<div class='remove-button'><button>X</button></div>");
	
	$(".form-item.selected .remove-button button").bind(
		"click",{i:item},
		function(e){
			e.data.i.remove();
			$("#property-tabs ul li:nth-child(2)").hide();
			$( "#property-tabs" ).tabs( "option", "active", 0 );
		});
	
    x
//		.attr("enable",true)
		.val(item.css("left"))
		.unbind("keyup input paste").bind("keyup input paste",{
			i:item,
			t:x
		},function(e){
			e.data.i.css("left",e.data.t.val());
		});
	
    y
//		.attr("enable",true)
		.val(item.css("top"))
		.unbind("keyup input paste").bind("keyup input paste",{
			i:item,
			t:y
		},function(e){
			e.data.i.css("top",e.data.t.val());
		});
	
    label
//		.attr("enable",true)
		.val(item.find(".label").html())
		.unbind("keyup input paste").bind("keyup input paste",{
			i:item,
			t:label
		},function(e){
			e.data.i.find(".label").html(e.data.t.val());
		});
	
	
	$("#property-tabs ul li:nth-child(2)").show();
    $( "#property-tabs" ).tabs( "option", "active", 1 );
    //change the choice type of the property
	setChoiceType(item.attr("type"));
}

//change the choice type of the property
function setChoiceType(){
    if($(".selected input").attr("type")){
        var type=$(".selected input").attr("type").toUpperCase();;
		if(/TEXT/.test(type)){
			//INPUT TYPE='TEXT'
			$("#item-text-size,#textDefaultValue,#item-required").show();
			$("#radio-list,#checkbox-list,#item-boxLayout,#combobox-list").hide();
			
			$("#size-min input")
				.unbind("keyup input paste").bind("keyup input paste",function(){       
					$(".selected input").attr('min',$("#size-min input").val());
				});
			$("#size-max input")
				.unbind("keyup input paste").bind("keyup input paste",function(){   
					$(".selected input").attr('max',$("#size-max input").val());
				});
			$("#textDefaultValue input")
				.unbind("keyup input paste").bind("keyup input paste",function(){ 
					$(".selected input").attr("value",$("#textDefaultValue input").val());
				});
				
			$("#item-required input")
				.unbind("change").bind("change",function(){
					if($("#item-required").is(':checked')){
						$(".selected input").addClass("required");
					}else{
						$(".selected input").removeClass("required");
					}
				});
		}else{
			$("#item-text-size,#textDefaultValue,#item-required,#item-boxLayout").hide();
			$("#combobox-list").hide();
			if(/RADIO/.test(type)){
				$("#radio-list").show();
				$("#checkbox-list").hide();
				var container = $("#radio-list #radio-choices");
				var choices = $(".selected fieldset .choice");
				container.children().not(".sample-choice").remove();
				choices.each(function(index){
					addChoice($(this),index,"RADIO");
				});
			}if(/CHECKBOX/.test(type)){
				$("#checkbox-list").show();
				$("#radio-list").hide();
				var container = $("#checkbox-list #checkbox-choices");
				var choices = $(".selected fieldset .choice");
				container.children().not(".sample-choice").remove();
				choices.each(function(index){
					addChoice($(this),index,"CHECKBOX");
				});
			}
		}
    }else if($(".selected select").length>0){
        //is a combobox
		$("#item-text-size,#textDefaultValue,#item-required,#item-boxLayout").hide();
		$("#radio-list,#checkbox-list").hide();
		$("#combobox-list").show();
		var container = $("#combobox-list #combobox-choices");
		var choices = $(".selected option");
		container.children().not(".sample-choice").remove();
		choices.each(function(index){
			addChoice($(this),index,"COMBOBOX");
		});
		/*
        $("#radio-choices input[type=radio] ,#radio-choices input[type=checkbox]").each(function(){
            $(this).css("display","none")
        });
		*/
		//"<option class='choice'></option>"
    }else{
		$("#item-text-size,#textDefaultValue,#item-required,#item-boxLayout").hide();
		$("#radio-list,#checkbox-list,#combobox-list").hide();
	}
	$("#options").show();
	if($("#options>fieldset>div:visible").length==0)
		$("#options").hide();
}
function addChoice(choice,index,type){
	if(/RADIO/.test(type)){
		var container = $("#radio-list #radio-choices");
		var c = "#radio-list #radio-choices";
		var i = "<div class='choice'><input type='radio'/><label>Radio Button</label></div>";
		var target = choice.children("label");
	}else if(/CHECKBOX/.test(type)){
		var container = $("#checkbox-list #checkbox-choices");
		var c = "#checkbox-list #checkbox-choices";
		var i = "<div class='choice'><input type='checkbox'/><label>Checkbox</label></div>";
		var target = choice.children("label");
	}else if(/COMBOBOX/.test(type)){
		var container = $("#combobox-list #combobox-choices");
		var c = "#combobox-list #combobox-choices";
		var i = "<option class='choice'>Combox Option</option>";
		var target = choice;
	}
		
		
	if(index>0){
		var newChoice = container.children(".sample-choice").clone().insertAfter(
						container.children(".choice").eq(index-1));
	}else
		var newChoice = container.children(".sample-choice").clone()
							.insertAfter(container.children(".sample-choice"));
	//display text and set trigger
	newChoice.children("input[type='text']")
		.val(target.html())
		.unbind("keyup input paste").bind("keyup input paste",{
			i:target,
			t:newChoice.children("input[type='text']")
		},function(e){
			e.data.i.html(e.data.t.val());
		});
			
	//set button trigger
	
	newChoice.children("button.add")
		.unbind("click").bind("click",{
			i:newChoice,
			c:c,
			e:i,
			t:type
		},function(e){
			var container = $(e.data.c);
			var position = container.children(".choice").index(e.data.i);
			$(".selected .choice").eq(position)
				.after(e.data.e);
			addChoice($(".selected .choice").eq(position+1),position+1,e.data.t);
		});
	
	newChoice.children("button.remove")
		.unbind("click").bind("click",{
			i:newChoice,
			c:c
		},function(e){
			var container = $(e.data.c);
			var position = container.children(".choice").index(e.data.i);
			$(".selected .choice").eq(position).remove();
			e.data.i.remove();
		});
		
	//show choice
	newChoice.removeClass("sample-choice")
		.addClass("choice")
		.show();
}

function getChoiceHtml(itemType){
	var html="";
	if(itemType!=null){
		var itemType=itemType.toUpperCase();
	}
	switch(true){
		case /RADIO/.test(itemType):
			html="<span class='choice'><input type='radio'/><label>Radio Button</label></span>";
			break;
		case /CHECKBOX/.test(itemType):
			html="<span><input class='choice'type='checkbox'/><label>Checkbox</label></span>";
			break;
		default:
			//combobox
			html="<option class='choice'></option>";
			break;
	}
	return html;
}