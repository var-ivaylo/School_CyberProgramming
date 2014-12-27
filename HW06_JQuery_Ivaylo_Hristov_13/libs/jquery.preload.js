jQuery.preloadImages = function() {
  var a = (typeof arguments[0] == 'object')? arguments[0] : arguments;
  for(var i = a.length -1; i >= 0; i--) {
   jQuery(document.createElement("img")).attr("src", arguments[i]);
  }
 }