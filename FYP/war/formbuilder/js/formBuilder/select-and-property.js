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
				getStyle();
			});

	//Y text
    y
            .val(item.css("top").replace("px",""))
            .unbind("keyup input paste").bind("keyup input paste", {
				i: item,
				t: y
			}, function(e) {
				e.data.i.css("top", e.data.t.val()+"px");
				getStyle();
			});

	//Label text
    label
            .val(item.find(".label").html())
            .unbind("keyup input paste").bind("keyup input paste", {
        i: item,
        t: label
    }, function(e) {
        e.data.i.find(".label").html(e.data.t.val());
    });

	//Label Checkbox
    if (item.find(".label:visible").length===0) {
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
            e.data.i.find(".label").css("display", "inline");
        } else {
            label.attr("disabled", true);
            e.data.i.find(".label").css("display", "none");
        }});

	//Css Text
	getStyle();
		
	//Show property Panel
    $("#formBuilder-leftPanel>ul>li:nth-child(2)").show();
    $("#formBuilder-leftPanel").tabs("option", "active", 1);
    //change the choice type of the property
    setChoiceType(item.attr("type"));
}

//change the choice type of the property
function setChoiceType() {


    var itemType = $(".selected").attr("id").split('-')[0].toUpperCase();
    switch (true) {
        case (/TEXTFIELD/).test(itemType):
            //INPUT TYPE='TEXT'
            $("#item-text-size,#textDefaultValue,#item-required,#validation").show();
            $("#radio-list,#checkbox-list,#item-boxLayout,#combobox-list").hide();

            $("#size-min input")
                    .unbind("keyup input paste").bind("keyup input paste", function() {
                $(".selected input").attr('min', $("#size-min input").val());
            });
            $("#size-max input")
                    .unbind("keyup input paste").bind("keyup input paste", function() {
                $(".selected input").attr('max', $("#size-max input").val());
            });
            $("#textDefaultValue input")
                    .unbind("keyup input paste").bind("keyup input paste", function() {
                $(".selected input").attr("value", $("#textDefaultValue input").val());
            });

            $("#item-required input")
                    .unbind("change").bind("change", function() {
                if ($("#item-required").is(':checked')) {
                    $(".selected input").addClass("required");
                } else {
                    $(".selected input").removeClass("required");
                }
            });
            break;
        case (/RADIOBUTTON/).test(itemType):
            $("#item-text-size,#textDefaultValue,#item-required,#item-boxLayout").hide();
            $("#combobox-list").hide();

            $("#radio-list,#validation").show();
            $("#checkbox-list").hide();
            var container = $("#radio-list #radio-choices");
            var choices = $(".selected fieldset .choice");
            container.children().not(".sample-choice").remove();
            choices.each(function(index) {
                addChoice($(this), index, "RADIO");
            });
            break;
        case (/CHECKBOX/).test(itemType):
            $("#item-text-size,#textDefaultValue,#item-required,#item-boxLayout").hide();
            $("#combobox-list").hide();

            $("#checkbox-list,#validation").show();
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
            $("#item-text-size,#textDefaultValue,#item-required,#item-boxLayout").hide();
            $("#radio-list,#checkbox-list").hide();
			
            $("#combobox-list,#validation").show();
            var container = $("#combobox-list #combobox-choices");
            var choices = $(".selected option");
            container.children().not(".sample-choice").remove();
            choices.each(function(index) {
                addChoice($(this), index, "COMBOBOX");
            });
            break;
        case (/HEADING/).test(itemType):
			$("#validation").hide();
		
            $("#item-text-size,#textDefaultValue,#item-required,#item-boxLayout").hide();
            $("#radio-list,#checkbox-list,#combobox-list").hide();
            break;
		case (/LABEL/).test(itemType):
			$("#validation").hide();
		
            $("#item-text-size,#textDefaultValue,#item-required,#item-boxLayout").hide();
            $("#radio-list,#checkbox-list,#combobox-list").hide();
            break;
        case (/TEXTAREA/).test(itemType):
			$("#validation").show();
		
            $("#item-text-size,#textDefaultValue,#item-required,#item-boxLayout").hide();
            $("#radio-list,#checkbox-list,#combobox-list").hide();
            break;
        case (/DATE/).test(itemType):
			$("#validation").show();
		
            $("#item-text-size,#textDefaultValue,#item-required,#item-boxLayout").hide();
            $("#radio-list,#checkbox-list,#combobox-list").hide();
            break;
        case (/UPLOAD/).test(itemType):
			$("#validation").hide();
		
            $("#item-text-size,#textDefaultValue,#item-required,#item-boxLayout").hide();
            $("#radio-list,#checkbox-list,#combobox-list").hide();
            break;
        default:
            break;

    }

            $("#formBuilder-leftPanel #options").show();
            if ($("#formBuilder-leftPanel #options>fieldset>div:visible").length===0)
                $("#formBuilder-leftPanel #options").hide();
    
}
function addChoice(choice, index, type) {
    if (/RADIO/.test(type)) {
        var container = $("#radio-list #radio-choices");
        var c = "#radio-list #radio-choices";
        var i = "<div class='choice'><input type='radio'/><label>Radio Button</label></div>";
        var target = choice.children("label");
    } else if (/CHECKBOX/.test(type)) {
        var container = $("#checkbox-list #checkbox-choices");
        var c = "#checkbox-list #checkbox-choices";
        var i = "<div class='choice'><input type='checkbox'/><label>Checkbox</label></div>";
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
        case/RADIO/.test(itemType):
            html = "<span class='choice'><input type='radio'/><label>Radio Button</label></span>";
            break;
        case/CHECKBOX/.test(itemType):
            html = "<span><input class='choice'type='checkbox'/><label>Checkbox</label></span>";
            break;
        default:
            //combobox
            html = "<option class='choice'></option>";
            break;
    }
    return html;
}

function getStyle(){
	var style = $(".selected").attr("style");
	if (typeof style !== 'undefined' && style !== false) {
		$("#itemCSS").val(style);
	}else{
		$("#itemCSS").val("position:absolute;");
	}
}
function setStyle(){
	$(".selected").attr("style",$("#itemCSS").val());
}