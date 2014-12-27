$(function() {

	//Task 02
	console.log($('div#footer a:first').attr('title'));

	//Task 03
	console.log($('div.inscreen div#col1').children('p').text());

	//Task 04
	$('ul#menu-top-level-menu').append('<li id="new-button"><a href="#"> New Button </a></li>');

	//Task 05
	$('div#footer').prepend('<div id="dynamiccontent"></div>');

})