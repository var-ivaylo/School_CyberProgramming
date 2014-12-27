$(function() {
	'use strict';

	var jsonHost = 'http://jsonplaceholder.typicode.com/';

	// Task 02
	console.log($('div#footer > a:first').attr('title'));

	// Task 03
	console.log($('div.inscreen > div:first').children('p').text());

	// Task 04
	$('ul#menu-top-level-menu').append('<li id="menu-item-new-bttn"><a href="#">new button</a></li>');

	// Task 05
	$('div#footer').prepend('<div id="dynamiccontent"/>');

	// Task 06
	$('div#dynamiccontent').append('<input id="textinput" type="text"/>');

	// Task 07
	$('div#dynamiccontent').append('<button id="addbutton"/>');

	// Task 08
	$('div#dynamiccontent').append('<ul id="posts"/>');

	// Task 09
	/*
	$('li#menu-item-new-bttn > a').click(function() {
		alert('hello world');
	});
	*/

	// Task 10
	$('li#menu-item-new-bttn > a').click(function() {
		var $first = $('div.inscreen > div:first').replaceWith($('div.inscreen > div:nth-child(2)'));
		$('div.inscreen > div:first').after($first);
	});

	// Task 11
	$.get(jsonHost + 'posts/', function(posts) {
		var $ul = $('ul#posts'),
			i = 0;

		$.each(posts, function() {
			var $liPost = $('<li/>');

			$liPost.text(this.title);
			$ul.append($liPost);

			if (++i >= 5) {
				return false;
			}
		});
	});

	// Task 12
	/*
	$('button#addbutton').click(function() {
		if ($('input#textinput').val() === '') {
			alert("you must enter text");
		}
	});
	*/

	// Task 13
	$('button#addbutton').click(function() {
		var inputVal = $('input#textinput').val();

		if (inputVal === '') {
			alert("you must enter text");
		} else {
			$.post(jsonHost + 'posts/', {
				title: inputVal,
				body: 'lorem ipsum',
				userId: 1
			});
		}
	});
});