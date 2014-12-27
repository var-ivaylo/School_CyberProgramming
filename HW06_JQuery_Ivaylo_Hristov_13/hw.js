$(function() {
	'use strict';

	// Task 02
	console.log($('div#footer a:first').attr('title'));

	// Task 03
	console.log($('div.inscreen div:first').children('p').text());

	// Task 04
	$('ul#menu-top-level-menu').append('<li id="menu-item-new-bttn"><a href="#">new button</a></li>');

	// Task 05
	$('div#footer').prepend('<div id="dynamiccontent"/>');
});