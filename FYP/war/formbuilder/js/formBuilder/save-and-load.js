$(document).ready(function(){
	//Load form at start
	overlaying();
	
	$(".form-item").draggable({
		containment: "parent",
		stop: function(event, ui){
			selectFormItem($(ui.draggable).attr("id"));
		}
		}).click(function(){
			selectFormItem($(this).attr("id"));
		});
	$("#form-save").click(function(){
		
		//deselect the item
		$(".form-item.selected .remove-button").remove();
		$(".form-item.selected").removeClass('selected');
		$("#formBuilder-leftPanel ul li:nth-child(2)").hide();
		$("#formBuilder-leftPanel").tabs("option", "active", 0);
		
		//remove overlay
		$(".form-item .overlay").remove();
		
		//save the form
		var form = $('#formBuilder-stage').html();
		
		//add overlay back
		overlaying();
		
		//sending request
		$.post("http://localhost:8888/formBuilder"
			,{
				FormHtml : form, 
				Title : $("#form-title").html(),
				Constraint : ""}
			, function(data) {
				alert(data);
			});
	})
});

//add overlay to new item
function overlaying(){
	$(".form-item").each(function(i,e){
		if($(e).children(".overlay").length == 0)
			$(e).append("<div class='overlay' ></div>");
	});
}
