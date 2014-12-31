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
	$('#addbutton').text('ADD');

	//Task 17
	$('div#dynamiccontent').append('<input id="textinput2" type="text"/>');

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
		}
		else {
			$.post("http://jsonplaceholder.typicode.com/posts", {
				title: $('#textinput').val(),
				body: 'random',
				userId: 123
			}, function(postRes) {
				$.get("http://jsonplaceholder.typicode.com/posts/" + postRes.id, function(post) {
					var $liPost = $('<li/>');
					var $xBtn = $('<button/>').text('X');

					$xBtn.click(function() {
						if (confirm('Are you sure you want to delete this?')) {
							$.ajax({
								method: 'DELETE',
								url: 'http://jsonplaceholder.typicode.com/posts/' + postRes.id,
								success: function() {
									$xBtn.parent().remove();
								}
							});
						}
					});

					$liPost.text(post.title);
					$liPost.append($xBtn);

					$('ul#posts').append($liPost);
				});
			});
		};
	});

	//Task 18
	$('#textinput2').change(function(){
		$('#posts').empty();

		var data = $(this).val();
		var destination = 'http://jsonplaceholder.typicode.com/posts?userId=' + data;
		$.get(destination, function(getData){
			$.each(getData, function(){
				var $liPost = $('<li/>');
				var $xBtn = $('<button/>').text('X');

				var postData = this;

				$xBtn.click(function() {
					if (confirm('Are you sure you want to delete this?')) {
						$.ajax({
							method: 'DELETE',
							url: 'http://jsonplaceholder.typicode.com/posts/' + postData.id,
							success: function() {
								$xBtn.parent().remove();
							}
						});
					}
				});

				$liPost.text(postData.title);
				$liPost.append($xBtn);

				$('#posts').append($liPost);
			});
		});
	});

});