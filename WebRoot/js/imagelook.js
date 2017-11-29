jQuery(document).ready(function(){
	
	
	///// ANIMATE IMAGE HOVER /////
	jQuery('.field a').hover(function(){
		jQuery(this).find('img').stop().animate({opacity: 0.75});
	}, function(){
		jQuery(this).find('img').stop().animate({opacity: 1});
	});

	
    ///// PREVIEW IMAGE /////
	jQuery('.field a').colorbox();

});