var Set = function() {}
Set.prototype.add = function(o) { this[o] = true; }
Set.prototype.remove = function(o) { delete this[o]; }

function Node(id){
	this.x = 0;
	this.y = 0;
	this.tcp = new Set();
	this.fcp = new Set();
	this.id = id;
	this.props = {};
	
}
Node.prototype.setType = function(type){
	this.type = type;
}
Node.prototype.addTcp = function(nodeId){
	this.tcp.add(nodeId);
}
Node.prototype.addFcp = function(nodeId){
	this.fcp.add(nodeId);
}
Node.prototype.removeTcp = function(nodeId){
	this.tcp.remove(nodeId);
}
Node.prototype.removeFcp = function(nodeId){
	this.fcp.remove(nodeId);
}
Node.prototype.removeLink = function(nodeId){
	this.tcp.remove(nodeId);
	this.fcp.remove(nodeId);
}
Node.prototype.updatePosition = function(x,y){
	this.x = x;
	this.y = y;
}
Node.prototype.loadMenu = function(){
	$("#"+this.type+"-property").lightbox_me({
        centered: true,
        closeClick : false,
        closeEsc : false,
        closeSelector :".property-cancel-btn,.property-submit-btn"
        });
	
}
