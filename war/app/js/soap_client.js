/** SOAP requests sender and response parser class.
*/

/**
 * Make new SOAP client class.
 * @param {String} url       The webservice url
 * @param {String} namespace The webservice namespace
 * @param {Object} methods   methods of the webservice
*/
function SoapClient (url, namespace, methods = {}){
	this.url = url;
	this.ns = namespace;
	this.methods = methods;
}

/**
 * Define or modify a method of the webservice
 * @param {String} name 	the method's name
 * @param {Array} args 	the list of argument types
*/
SoapClient.prototype.setMethod = function(name, args = []){
	this.methods[name] = args;
}

/**
 * Call a method of the webservice and return the result as javascript object.
 * @param  {String}   	name     name of the method to call
 * @param  {Array}   	values   list of argument values
*/
SoapClient.prototype.callMethod = function(name, values){
	if(name in this.methods) {
		if(this.methods[name].length == values.length) {
			return this.sendRequest(name, values);
		} else 
			throw "Number of arguments is not equal";
	} else 
		throw "Unknown method '" + name + "'";
}

/**
 * sends the soap request
 * @param  {String}   	name     name of the method to call
 * @param  {Array}   	values   list of argument values
*/
SoapClient.prototype.sendRequest = function (name, values){
	var xmlRequest = this.makeRequest(name, values);
	var response = $.ajax({
        url: this.url,
        contentType: 'text/xml; charset="utf-8"',
        type: 'POST',
        dataType: 'xml',
        data: xmlRequest,
        async: false
    }).responseText;
    return this.decodeResponse(response);
}

/**
 * make the soap request
 * @param  {String}   	method  name of the method to call
 * @param  {Array}   	values 	list of argument values
*/
SoapClient.prototype.makeRequest = function(method, values){
	var params = '';
	var size = values.length;
	for(var i = 0; i < size; i++)
		params += this.getTemplate('arg.xml', {
			index: i,
			type: this.methods[method][i],
			value: values[i]
		});
	return this.getTemplate('request.xml', {
		ns: this.ns,
		name: method,
		args: params
	});
}

/**
 * Loads a text file and replaces placeholders with values
 * @param  {String} name     Name of the file to load
 * @param  {Object} bindings values by which replace every placeholder
 * @return {String}          the resulting string after replacements
*/
SoapClient.prototype.getTemplate = function(name, bindings) {
	var tpl = $.ajax({
        type: "GET",
        url: 'js/templates/' + name,
        async: false
    }).responseText;
    for (var key in bindings) {
        if (bindings.hasOwnProperty(key)) {
        	// console.log('replacing '+key+' by '+bindings[key]);
            tpl = tpl.replace(new RegExp('\\${'+key+'}', 'g'), bindings[key]);
        }
    }
    return tpl;
}

/**
 * Convert a soap response to a javascript object
 * @param  {String} response 	soap response as XML string
 * @return {Object} 			Corresponding javascript object
*/
SoapClient.prototype.decodeResponse = function(response){
	var self = this;
	var result = [];
	response = $.parseXML(response);
    response = $(response);
    response.find('return').each(function(){
    	result.push(self.decode($(this)));
    });

    if(result.length == 1)
    	result = result[0];

    return result;
}

SoapClient.prototype.decode = function($scope) {
	var self = this;
	if($scope.children('*').length == 0)
		return $scope.html();
	var obj = {};
	$scope.children('*').each(function(){
		var name = $(this).prop('tagName').toLowerCase();
		var value = self.decode($(this));
		obj[name] = value;
	});
	return obj;
};