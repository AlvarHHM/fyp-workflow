//

var form = "";
$(document).ready(function(){
	$("#form-save").click(function(){
		
		//deselect the item
		$(".form-item.selected .remove-button").remove();
		$(".form-item.selected").removeClass('selected');
		$("#formBuilder-leftPanel ul li:nth-child(2)").hide();
		$("#formBuilder-leftPanel").tabs("option", "active", 0);
		
		//remove overlay and save the form
		$(".form-item .overlay").remove();
		form = $('#formBuilder-stage').html();
		
		//add overlay to new item
		$(".form-item").each(function(i,e){
			if($(e).children(".overlay").length == 0)
				$(e).append("<div class='overlay' ></div>");
		});
		
		//sending request
		var params = {
					"formHtml" : form , 
					"formTitle" : $("#form-title").html(),
					"formScript" : ""};
		post_to_url(
			"http://localhost:8888/formBuilder",
			//"http://www.hashemian.com/tools/form-post-tester.php",
			params, "post");
	})
	$("#form-load").click(function(){
		
		//add overlay to new item
		$(".form-item").each(function(i,e){
			if($(e).children(".overlay").length == 0)
				$(e).append("<div class='overlay' ></div>");
		});
	});
});

function post_to_url(path, params, method) {
    method = method || "post"; 

    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);

    for(var key in params) {
        if(params.hasOwnProperty(key)) {
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", key);
            hiddenField.setAttribute("value", params[key]);

            form.appendChild(hiddenField);
         }
    }

    document.body.appendChild(form);
    form.submit();
}
//Code for reading a saved form
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