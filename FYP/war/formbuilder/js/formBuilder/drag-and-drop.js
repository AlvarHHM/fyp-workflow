$(document).ready(function() {
    $("#formBuilder-stage").droppable({
        accept: ".formBuilder-component-container",
        drop: function(event, ui) {
            var pos = ui.draggable.position();
            var itemType = ui.draggable.attr("id").split('-')[1];
            addFormItem(itemType, pos.left, pos.top);

            $(ui.draggable).css({
                "left": "0px",
                "top": "0px"
            });
        }
    });
    $(".formBuilder-component-container").draggable({
        revert: true,
        revertDuration: 0
    });
});

function addFormItem(itemType, x, y) {
    var id = genFormItemId(itemType);
    $("#formBuilder-stage")
            .append("<div id='" + id + "' class='form-item'></div>");
    $("#" + id)
            .append("<div>" + getItemHtml(itemType) + "</div>")
            .append("<div class='overlay' ></div>")
            .draggable({
        containment: "parent"
    }).click(function() {
        selectFormItem(id);
    }).bind("drag dragstop", function() {
        selectFormItem($(this).attr("id"));
    });
    var container = $("#formBuilder-stage");
    var left = x - container.position().left - 1;
    var top = y - container.position().top + 14;

    $("#formBuilder-stage>:last-child").css({
        "position": "absolute",
        "top": top + "px",
        "left": left + "px"
    });
    selectFormItem(id);
}

function getItemHtml(itemType) {
    var html = "";
    var itemType = itemType.toUpperCase();
    switch (true) {
        case (/HEADING/).test(itemType):
            html += "<label class='label'>Heading</label>";
            break;
        case (/TEXTFIELD/) .test(itemType):
            html += "<label class='label'>Text Field</label><input type='text'/>";
            break;
        case (/RADIOBUTTON/) .test(itemType):
            html += "<fieldset><legend class='label'>Radio Group</legend>"
                    + "<div class='choice'><input type='radio'/><label>Radio Button</label></div></fieldset>";
            break;
        case (/CHECKBOX/) .test(itemType):
            html += "<fieldset><legend class='label'>Checkbox Group</legend>"
                    + "<div class='choice'><input type='checkbox'/><label>Checkbox</label></div></fieldset>";
            break;
        case (/COMBOBOX/) .test(itemType):
            html += "<label class='label'>Select</label><select>"
                    + "<option class='choice'>Combox Option</option></select>";
            break;
        case (/TEXTAREA/) .test(itemType):
            html += "<label class='label'>Textarea</label><textarea></textarea>";
            break;
        default:
            break;
    }
    return html;
}

function genFormItemId(itemType) {
    var id = $(".form-item[id|='" + itemType + "']").length + 1;
    while ($(".form-item[id='" + itemType + "-" + id + "']").length > 0) {
        id = id + 1;
    }
    return itemType + "-" + id;
}



