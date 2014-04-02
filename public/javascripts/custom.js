$(function() {
	$.fn.editable.defaults.mode = 'inline';
});

var makeEditable = function(event, name, callUrl) {
	event.stopPropagation();
	$('#' + name).editable({
		type : 'text',
		pk : name,
		url : callUrl,
		success : function() {
			location.reload();
		}
	});
	$('#' + name).click();
}