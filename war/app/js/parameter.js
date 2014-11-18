function Parameter() {}
Parameter.params = {};

Parameter.update = function(url){
	// var url = document.URL;
	var params = url.split('#');
	if(params.length < 2)
		return null;
	var temp = params[1].split('|');
	Parameter.params = {};
	temp.forEach(function(e){
		e = e.split(':');
		Parameter.params[e[0]] = e[1];
	});
	console.log(Parameter.params);
}

Parameter.get = function (name){
	if(name in Parameter.params)
		return Parameter.params[name];
	return null;
}