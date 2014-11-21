function reloadSideBar(){
	var books = ws.callMethod('allBooks', []);
	var txt = '';
	if($.isArray(books)){
		books.sort(function(a,b){
			return b.num - a.num;
		});
		var i = 0;
		var size = (5 < books.length) ? 5 : books.length;
		while (i < size){
			txt += '<li class="list-group-item"><a href="index.html#p:book_list|n:'+books[i].num+'">'+books[i].title+'</a></li>';
			i ++;
		}
	} else if(books){
		txt = '<li class="list-group-item"><a href="index.html#p:book_list|n:'+books.num+'">'+books.title+'</a></li>';
	} else {
		txt = '<li class="list-group-item"> Il n\'y a pas encore de livre </li>';
	}
	$('#last_books').html(txt);

	var authors = ws.callMethod('allAuthors', []);
	txt = '';
	if($.isArray(authors)){
		authors.sort(function(a,b){
			return b.num - a.num;
		});
		var i = 0;
		var size = (5 < authors.length) ? 5 : authors.length;
		while (i < size){
			txt += '<li class="list-group-item"><a href="index.html#p:author_list|n:'+authors[i].num+'">'+authors[i].lastname+' '+authors[i].firstname+'</a></li>';
			i ++;
		}
	} else if(authors){
		txt = '<li class="list-group-item"><a href="index.html#p:author_list|n:'+authors.num+'">'+authors.lastname+' '+authors.firstname+'</a></li>';
	} else {
		txt = '<li class="list-group-item"> Il n\'y a pas encore d\'auteur </li>';
	}
	$('#top_authors').html(txt);
}
reloadSideBar();