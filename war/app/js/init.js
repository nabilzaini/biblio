/** Initializations */

// Webservice
var ws = new SoapClient('http://localhost:8888/biblio', 'ws.jee.isima.com');

ws.setMethod('addAuthor', ['string', 'string', 'string']);
ws.setMethod('editAuthor', ['int', 'string', 'string', 'string']);
ws.setMethod('deleteAuthor', ['int']);
ws.setMethod('getAuthor', ['int']);
ws.setMethod('findAuthor', ['string', 'string']);
ws.setMethod('allAuthors');
ws.setMethod('getBooksByAuthor', ['int']);

ws.setMethod('addBook', ['int', 'string', 'double', 'string']);
ws.setMethod('editBook', ['int', 'string', 'double', 'string']);
ws.setMethod('deleteBook', ['int']);
ws.setMethod('getBook', ['int']);
ws.setMethod('findBook', ['string', 'string']);
ws.setMethod('allBooks');
ws.setMethod('getAuthorOfBook', ['int']);

// Links handling
$(window).on('hashchange', function(e){
	Parameter.update(e.originalEvent.newURL);
    var page = Parameter.get('p');
	Part.go(page);
});