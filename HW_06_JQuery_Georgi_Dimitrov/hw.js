$(function() {

	//Task 02
	console.log($('div#footer a:first').attr('title'));

	//Task 03
	console.log($('div.inscreen div#col1').children('p').text());

	//Task 04
	$('ul#menu-top-level-menu').append('<li id="new-button"><a href="#"> New Button </a></li>');

	//Task 05
	$('div#footer').prepend('<div id="dynamiccontent"></div>');

	//Task 06
	$('div#dynamiccontent').append('<input id="textinput" type="text"/>');

	//Task 07
	$('div#dynamiccontent').append('<button id="addbutton"/>');

	//Task 08
	$('div#dynamiccontent').append('<ul id="posts"/>');

	//Task 09
	$('li#new-button a').click(function() {
		alert('hello world');
	});

	//Task 10
	$('li#new-button a').click(function() {
		var $first = $('div.inscreen div:first');
		var $second = $('div.inscreen div:nth-child(2)');

		$('div.inscreen div:first').replaceWith($second);
		$('div.inscreen div:first').after($first);
	});

	//Task 11
	$.get("http://jsonplaceholder.typicode.com/posts", function(postsData) {
		var $lists = $('#posts');
		for (var i = 0; i < 5; i++) {
			$lists.append('<li>' + postsData[i].title + '</li>');
		};
	});

	//Task 12
	$('#addbutton').click(function(){
		if ($('#textinput').val() === '') {
			alert("you must enter text");
		};
	});
	



})