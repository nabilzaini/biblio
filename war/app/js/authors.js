var authorList = new Part('content','author_list');
authorList.define(function(){
	// what to do ?!
});

var authorAdd = new Part('content','author_add');
authorAdd.define(function(){
	// what to do ?!
});

var authorDetails = new Part('content','author_details');
authorDetails.define(function(){
	var num = Parameter.get('n');
	if(num == null)
		$('#num').html('Inconnu');
	else
		$('#num').html(num);
});