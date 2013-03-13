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
	})
	$("#form-load").click(function(){
		
		//add overlay to new item
		$(".form-item").each(function(i,e){
			if($(e).children(".overlay").length == 0)
				$(e).append("<div class='overlay' ></div>");
		});
	});
});
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