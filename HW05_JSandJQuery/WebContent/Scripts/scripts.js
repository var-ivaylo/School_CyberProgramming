itemCounter = 1;
$('#button1').click(function() {
	$('#list1').append('<li><a>Item ' + itemCounter + ' </a></li>');
	itemCounter++;
});

// returns all div childs of div
$("div:only-of-type");

//selects element with id col1
$("#col1");

//selects element of the class inscreen
$(".inscreen");

//returns all elements
$("*");

//return the last h2 element
$("h2:last");

//returns all header elements
$(":header");

//returns elements containing 'Sample Text'
$(":cantains('Sample Text')");

//returns the forth element in a list
$("ul li:eq(3)");

//returns all input elements
$(":input");

//returns all elements of type button
$(":button");

//returns all elements parent elements
$(":parent");