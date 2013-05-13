var Set = function() {
	
}
Set.prototype.add = function(o) {
	this[o] = true;
}
Set.prototype.remove = function(o) {
	delete this[o];
}
Set.prototype.recoverFromObject = function(object){
	for(var prop in object){
		this.add(prop);
	}
}

function Node(id) {
	this.x = 0;
	this.y = 0;
	this.tcp = new Set();
	this.fcp = new Set();
	this.id = id;
	this.props = {};
}
Node.prototype.setType = function(type) {
	this.type = type;
}
Node.prototype.addTcp = function(nodeId) {
	this.tcp.add(nodeId);
}
Node.prototype.addFcp = function(nodeId) {
	this.fcp.add(nodeId);
}
Node.prototype.removeTcp = function(nodeId) {
	this.tcp.remove(nodeId);
}
Node.prototype.removeFcp = function(nodeId) {
	this.fcp.remove(nodeId);
}
Node.prototype.removeLink = function(nodeId) {
	this.tcp.remove(nodeId);
	this.fcp.remove(nodeId);
}
Node.prototype.updatePosition = function(x, y) {
	this.x = x;
	this.y = y;
}
Node.prototype.loadMenu = function() {
	var props= this.props;
	
	$("#" + this.type + "-property").lightbox_me({
		centered : true,
		closeClick : false,
		closeEsc : false,
		closeSelector : ".property-cancel-btn,.property-submit-btn",
		onLoad : function() {
			if($.isEmptyObject(props)){
				$(".property-value").val("");
				
			}
			
			$.each(props,function(i,v){
				$("#" + i).val(v);
//				console.log("#" + i+"|"+v);
				if($("#" + i)[0].tagName == "SELECT"){
					$("#" + i).change();
				}
			});
		}
	});
}
Node.prototype.recoverFromObject= function(object){
	this.props = object.props;
	var tset = new Set();
	tset.recoverFromObject(object.tcp);
	this.tcp = tset;
	var fset = new Set();
	fset.recoverFromObject(object.fcp);
	this.fcp = fset;
	this.id = object.id;
	this.type = object.type;
	this.x = object.x;
	this.y = object.y;
	
	
}
