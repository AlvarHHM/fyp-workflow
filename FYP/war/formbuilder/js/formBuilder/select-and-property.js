var selected_item = "";
$(document).ready(function() {
    $("#formBuilder-stage").click(function(e) {
        if (e.target===this) {
            $(".form-item.selected .remove-button").remove();
            $(".form-item.selected").removeClass('selected');
			
            $("#formBuilder-leftPanel>ul>li:nth-child(2)").hide();
            $("#formBuilder-leftPanel").tabs("option", "active", 0);
        }
    });
    $(".sample-choice").hide();
    $("#formBuilder-leftPanel>ul>li:nth-child(2)").hide();

	$("#formName").val($("#form-title").html());
	$("#formName").bind("keyup input paste", function() {
		$("#form-title").html($("#formName").val());
	});
});

function selectFormItem(itemId) {

    var item = $("#"+itemId);
    var x = $(".form-item-property#X [type='text']");
    var y = $(".form-item-property#Y [type='text']");
    var label = $(".form-item-property#item-label [type='text']");
    var labelShow = $(".form-item-property#item-label [type='checkbox']");
	
    $(".form-item.selected .remove-button").remove();
    $(".form-item.selected").removeClass('selected');
    item.addClass('selected');
    item.children(".overlay").append("<div class='remove-button'><button>X</button></div>");

	//Remove Button
    $(".form-item.selected .remove-button button").bind(
		"click", {i: item},
			function(e) {
				e.data.i.remove();
				$("#formBuilder-leftPanel>ul>li:nth-child(2)").hide();
				$("#formBuilder-leftPanel").tabs("option", "active", 0);
		});
		
	//X text
    x
            .val(item.css("left").replace("px",""))
            .unbind("keyup input paste").bind("keyup input paste", {
				i: item,
				t: x
			}, function(e) {
				e.data.i.css("left", e.data.t.val()+"px");
			});

	//Y text
    y
            .val(item.css("top").replace("px",""))
            .unbind("keyup input paste").bind("keyup input paste", {
				i: item,
				t: y
			}, function(e) {
				e.data.i.css("top", e.data.t.val()+"px");
			});

	//Label text
    label
            .val(item.find(".item-text").html())
            .unbind("keyup input paste").bind("keyup input paste", {
        i: item,
        t: label
    }, function(e) {
        e.data.i.find(".item-text").html(e.data.t.val());
    });

	//Label Checkbox
    if (item.find(".item-text:visible").length===0) {
        label.attr("disabled", true);
        labelShow.attr("checked", false);
    } else {
        label.attr("disabled", false);
        labelShow.attr("checked", true);
		}
    labelShow.unbind("change").bind("change", {
        i: item,
        l: label,
        t: labelShow
    }, function(e) {
        if (e.data.t.attr("checked")) {
            label.attr("disabled", false);
            e.data.i.find(".item-text").css("display", "block");
        } else {
            label.attr("disabled", true);
            e.data.i.find(".item-text").css("display", "none");
        }});

	//Css Text
	displayStyle();
	
	//Show property Panel
    $("#formBuilder-leftPanel>ul>li:nth-child(2)").show();
    $("#formBuilder-leftPanel").tabs("option", "active", 1);
	
    //change the choice type of the property
    setChoiceType();
	
	//Show correct validation for the element
	showValidation();
}

//change the choice type of the property
function setChoiceType() {
	$("#auto-fill").hide();
    var itemType = $(".selected").attr("id").split('-')[0].toUpperCase();
    switch (true) {
        case (/TEXTFIELD/).test(itemType):
            //INPUT TYPE='TEXT'
            $("#item-text-size,#textDefaultValue,#validation").show();
			$("#auto-fill").show();
            $("#radio-list,#checkbox-list,#combobox-list").hide();

			
		if($(".selected input").hasClass("auto-name")){
			$("#auto-fill-name>input[type=checkbox]").attr("checked", true);
		}else{
			$("#auto-fill-name>input[type=checkbox]").attr("checked", false);
		}
			
		$("#auto-fill-name>input").bind("change",{i:$(".selected input")},function(e){
			if($(this).is(":checked")){
				e.data.i.addClass("auto-name");
			}else{
				e.data.i.removeClass("auto-name");
			}
		
		});
		
		$("#item-text-defaultValue input").val($(".selected input").val());
            $("#item-text-defaultValue input")
                    .unbind("keyup input paste").bind("keyup input paste", function() {
                $(".selected input").attr("value", $("#item-text-defaultValue input").val());
            });

            break;
        case (/RADIOBUTTON/).test(itemType):
            $("#item-text-size,#textDefaultValue").hide();
            $("#radio-list,#validation").show();
			
            $("#combobox-list").hide();
            $("#checkbox-list").hide();
			
            var container = $("#radio-list #radio-choices");
            var choices = $(".selected fieldset .choice");
            container.children().not(".sample-choice").remove();
            choices.each(function(index) {
                addChoice($(this), index, "RADIO");
            });
            break;
        case (/CHECKBOX/).test(itemType):
            $("#item-text-size,#textDefaultValue").hide();
            $("#checkbox-list,#validation").show();
			
            $("#combobox-list").hide();
            $("#radio-list").hide();
			
            var container = $("#checkbox-list #checkbox-choices");
            var choices = $(".selected fieldset .choice");
            container.children().not(".sample-choice").remove();
            choices.each(function(index) {
                addChoice($(this), index, "CHECKBOX");
            });
            break;
        case (/COMBOBOX/).test(itemType):
            //is a combobox
            $("#item-text-size,#textDefaultValue").hide();
            $("#combobox-list,#validation").show();
			
            $("#radio-list,#checkbox-list").hide();
			
            var container = $("#combobox-list #combobox-choices");
            var choices = $(".selected option");
            container.children().not(".sample-choice").remove();
            choices.each(function(index) {
                addChoice($(this), index, "COMBOBOX");
            });
            break;
        case (/TEXTAREA/).test(itemType):
			$("#validation").show();
		
            $("#item-text-size,#textDefaultValue").hide();
            $("#radio-list,#checkbox-list,#combobox-list").hide();
            break;
        case (/DATE/).test(itemType):
			$("#validation").show();
		
            $("#item-text-size,#textDefaultValue").hide();
            $("#radio-list,#checkbox-list,#combobox-list").hide();
            break;
		case (/LABEL/).test(itemType):
        case (/HEADING/).test(itemType):
        case (/UPLOAD/).test(itemType):
        default:
			$("#validation").hide();
		
            $("#item-text-size,#textDefaultValue").hide();
            $("#radio-list,#checkbox-list,#combobox-list").hide();
            break;

    }
	/*
	$("#formBuilder-leftPanel #options").show();
	if ($("#formBuilder-leftPanel #options>fieldset>div:visible").length===0)
		$("#formBuilder-leftPanel #options").hide();
    */
}
function addChoice(choice, index, type) {
    if (/RADIO/.test(type)) {
        var container = $("#radio-list #radio-choices");
        var c = "#radio-list #radio-choices";
        var i = "<div class='choice'><input name='"+$(".selected").attr("id")+"[]' type='radio'/><label>Radio Button</label></div>";
        var target = choice.children("label");
    } else if (/CHECKBOX/.test(type)) {
        var container = $("#checkbox-list #checkbox-choices");
        var c = "#checkbox-list #checkbox-choices";
        var i = "<div class='choice'><input name='"+$(".selected").attr("id")+"[]' type='checkbox'/><label>Checkbox</label></div>";
        var target = choice.children("label");
    } else if (/COMBOBOX/.test(type)) {
        var container = $("#combobox-list #combobox-choices");
        var c = "#combobox-list #combobox-choices";
        var i = "<option class='choice'>Combox Option</option>";
        var target = choice;
    }


    if (index>0) {
        var newChoice = container.children(".sample-choice").clone().insertAfter(
                container.children(".choice").eq(index-1));
    } else
        var newChoice = container.children(".sample-choice").clone()
                .insertAfter(container.children(".sample-choice"));
    //display text and set trigger
    newChoice.children("input[type='text']")
            .val(target.html())
            .unbind("keyup input paste").bind("keyup input paste", {
        i: target,
        t: newChoice.children("input[type='text']")
    }, function(e) {
        e.data.i.html(e.data.t.val());
    });

    //set button trigger

    newChoice.children("button.add")
            .unbind("click").bind("click", {
        i: newChoice,
        c: c,
        e: i,
        t: type
    }, function(e) {
        var container = $(e.data.c);
        var position = container.children(".choice").index(e.data.i);
        $(".selected .choice").eq(position)
                .after(e.data.e);
        addChoice($(".selected .choice").eq(position+1), position+1, e.data.t);
    });

    newChoice.children("button.remove")
            .unbind("click").bind("click", {
        i: newChoice,
        c: c
    }, function(e) {
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

function getChoiceHtml(itemType) {
    var html = "";
    if (itemType!==null) {
        var itemType = itemType.toUpperCase();
    }
    switch (true) {
        case (/RADIO/).test(itemType):
            html = "<span class='choice'><input type='radio'/><label>Radio Button</label></span>";
            break;
        case (/CHECKBOX/).test(itemType):
            html = "<span><input class='choice'type='checkbox'/><label>Checkbox</label></span>";
            break;
		case (/COMBOBOX/).test(itemType):
            html = "<option class='choice'></option>";
            break;
        default:
            break;
    }
    return html;
}

function displayStyle(){
	var item = $(".selected");
    var itemType = item.attr("id").split('-')[0].toUpperCase();
	
	$(".formItem-properties-group#css>div").hide();
	$(".formItem-properties-group#css>div button").unbind("click")
	
	getStyle(item.find(".item-text"),$("#text-css"));
			
	switch (true) {
        case (/TEXTFIELD/).test(itemType):
        case (/DATE/).test(itemType):
			getStyle(item.find("input[type=text]"),$("#textbox-css"));
            break;
        case (/RADIOBUTTON/).test(itemType):
			getStyle(item.find(".choice"),$("#choice-css"));
			getStyle(item.find("input[type=radio]"),$("#choice-box-css"));
			getStyle(item.find(".choice label"),$("#choice-label-css"));
            break;
        case (/CHECKBOX/).test(itemType):
			getStyle(item.find(".choice"),$("#choice-css"));
			getStyle(item.find("input[type=checkbox]"),$("#choice-box-css"));
			getStyle(item.find(".choice label"),$("#choice-label-css"));
            break;
        case (/COMBOBOX/).test(itemType):
			getStyle(item.find("select"),$("#choice-css"));
            break;
        case (/TEXTAREA/).test(itemType):
			getStyle(item.find("textarea"),$("#textbox-css"));
            break;
        case (/UPLOAD/).test(itemType):
			getStyle(item.find("input[type=file]"),$("#textbox-css"));
			getStyle(item.find("button"),$("#button-css"));
			getStyle(item.find("progress"),$("#progress-css"));
            break;
        case (/HEADING/).test(itemType):
		case (/LABEL/).test(itemType):
        default:
            break;

    }
}

function getStyle(target,cssDiv){
	var style = target.attr("style");
	var cssText = cssDiv.find(".itemCSS");
	
	if (typeof style !== 'undefined' && style !== false) {
		cssText.val(style);
	}else{
		cssText.val("");
	}
	cssDiv.find("button")
		.bind("	click", 
				{
					tar: target,
					text: cssText
				},function(e){
					e.data.tar.attr("style",e.data.text.val());
				});
	cssDiv.show();
}
