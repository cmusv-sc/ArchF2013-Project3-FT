var searchChange = $('.filter-search input').change(function(){
	var str = $(this).val();
	var element = $('#search-criteria').val();
	
	$(element).show();
	search(element, str);
});

var searchHide = function(element, str){
	var notFound = $(element).filter(function(){ 
		var str = $(this).text();
		return str.indexOf(str) == -1;
	});
	if(element !== '.devices'){
		notFound = notFound.parent();
	}
	notFound.hide();
}