(function($){

	var body = $('body'),
	header = body.find('.header'),
	scroll_menu = header.find('#scroll_menu'),
	full_menu = $('#collapse');

	if ("ontouchstart" in document.documentElement){
	    body.attr({'id':'touch'});
	}
	else{
		body.attr({'id':'no_touch'});
		if(pageYOffset > 50){
			body.addClass('fixed_menu');
		}
	}
	
	if (body.attr('id') === 'no_touch'){
	
		$(document).on( 'scroll', function() {
			if(pageYOffset > 50){
				body.addClass('fixed_menu');
			}
			else{
				body.removeClass('fixed_menu');
			}
		});
	}
	
	header.find('.logo').on('click' , function(event){
		event.preventDefault();
		var element = $(this);
		$(this).Scroll(element);
	});
	
	scroll_menu.find('.scroller_link').on('click' , function(event){
		event.preventDefault();
		var element = $(this);
		$(this).Scroll(element);
	});
	
	full_menu.find('.scroller_link').on('click' , function(event){
		event.preventDefault();
		var element = $(this);
		$(this).Scroll(element);
	}); 
	
	$.fn.Scroll = function(element){
	
		var	target = element.attr('href'),
		position = ($(target).offset().top) - 100; 

		if(element.hasClass('additional_menu_link')){
			body.removeClass('active_full_menu');
		}
		
		$('html, body').animate({scrollTop: position}, 800);
		return false; 
	};
	
})(jQuery);