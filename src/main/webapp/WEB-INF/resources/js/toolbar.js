$(document).ready(function(){
    
	var currentUrlLocation = window.location.href;
	var startIndex = currentUrlLocation.indexOf("macropro") + 9;
	currentUrlLocation = currentUrlLocation.substring(startIndex, currentUrlLocation.length) + "/";
	var currentUrlLocationArr = currentUrlLocation.split("/");

    $(".topbar .navbar-nav li").removeClass("active");
    $(".subbar .navbar-nav li").removeClass("active");
	
    var activeTopItem;
    var activeSubItem;
    
	// handle urls with "/"
	if (currentUrlLocationArr[currentUrlLocationArr.length-1] == "" && currentUrlLocationArr[currentUrlLocationArr.length-2] == "") {
		if (currentUrlLocationArr.length == 3) {
			activeTopItem = currentUrlLocationArr[0];
			activeSubItem = "home";
		} else {
			activeTopItem = currentUrlLocationArr[0];
			activeSubItem = currentUrlLocationArr[1];
		}
	} 
	
	// handles urls without "/"
	else {
		if (currentUrlLocationArr.length == 2) {
			activeTopItem = currentUrlLocationArr[0];
			activeSubItem = "home";
		} else {
			activeTopItem = currentUrlLocationArr[0];
			activeSubItem = currentUrlLocationArr[1];
		}
	}
	
	console.log('adding "' + activeTopItem + '" as active topbar list item');
    $('li[id="' + activeTopItem + '"]').addClass("active");
    
    console.log('setting "' + activeSubItem + '" as active subbar list item');
    $('.subbar .navbar-nav li[id="' + activeSubItem + '"]').addClass("active");
})