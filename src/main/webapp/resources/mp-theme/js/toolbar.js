$(document).ready(function(){
    
    var activeTopItem = localStorage.getItem('activeTopItem');
    if (activeTopItem != 'null') {
        console.log('adding "' + activeTopItem + '" as active topbar list item');
    	$('li[id="' + activeTopItem + '"]').addClass("active");
    } else {
        console.log('adding "myhome" as active topbar list item');
    	$('li[id="myhome"]').addClass("active");
    }

    var activeSubItem = window.location.href.split("/").pop()
    $(".subbar .navbar-nav li").removeClass("active");
    if (activeSubItem == "") {
        console.log('setting "home" as active subbar list item');
    	$('.subbar .navbar-nav li[id="home"]').addClass("active");
    } else {
        console.log('setting "' + activeSubItem + '" as active subbar list item');
    	$('.subbar .navbar-nav li[id="' + activeSubItem + '"]').addClass("active");
    }
    
    /*var activeSubItem = localStorage.getItem('activeSubItem');
    if (activeSubItem != 'null') {
        console.log('adding "' + activeSubItem + '" as active subbar list item*');
    	$('li[id="' + activeSubItem + '"]').addClass("active");
    } else {
        console.log('setting "home" as active subbar list item');
    	$('.subbar .navbar-nav li[id="home"]').addClass("active");
    }*/

    $('.topbar .navbar-nav li a').click(function() {
    	$(".topbar .navbar-nav li").removeClass("active");
    	localStorage.setItem('activeTopItem', $(this).parent().attr('id'));
    	$(this).parent().addClass("active");
    	console.log('new active topbar item: ' + $(this).parent().attr('id'))
    	
    	// reset active list item in sub bar when there's a new active
    	// list item in top bar
    	/*localStorage.setItem('activeSubItem', null);
    	$(".subbar .navbar-nav li").removeClass("active");*/
    });

    /*$('.subbar .navbar-nav li a').click(function() {
    	$(".subbar .navbar-nav li").removeClass("active");
    	localStorage.setItem('activeSubItem', $(this).parent().attr('id'));
    	$(this).parent().addClass("active");
    	console.log('new active subbar item: ' + $(this).parent().attr('id'))
    });*/
})