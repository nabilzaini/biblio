function Part (parentId, name, todo = null){
	$('#'+parentId).append('<div id="page-'+name+'"></div>');
	this.$parent = $('#page-'+name);
	this.name = name;
	this.todo = todo;
	
	Part.pages[name] = this;

	this.$parent.hide();
}

Part.current = null;
Part.pages = {};

Part.prototype.load = function() {
	return $.ajax({
        type: "GET",
        url: 'views/' + this.name + '.html',
        async: false
    }).responseText;
}

Part.prototype.show = function() {
	if(Part.current != null && Part.current != this)
		Part.current.hide();
	this.$parent.html(this.load());
	if(this.todo != null)
		this.todo();
	this.$parent.slideDown(400);
	Part.current = this;
	console.log('page '+this.name+' shown');
}

Part.prototype.define = function(todo) {
	this.todo = todo;
}

Part.prototype.hide = function() {
	var self = this;
	this.$parent.slideUp(400);
}

Part.go = function(name) {
	if(name in Part.pages)
		Part.pages[name].show();
}