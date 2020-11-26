$(function() {
	var grayDisplay = $('#gray-display');
	var largeVideo = $('.video');
	grayDisplay.hide();
	largeVideo.hide();


	$('.visual-btn').click(function() {
		var windowWidth = $(window).width() * 0.6;
		$('video').css('width', windowWidth);
		$(window).resize(function() {
			var windowWidth = $(window).width() * 0.6;
			$('video').css('width', windowWidth);
		});

		grayDisplay.show();
		largeVideo.show();

		grayDisplay.click(function() {
			largeVideo.hide();
			grayDisplay.hide();
		});
		return false;
	});

	$("#video").on('ended', function() {
		largeVideo.hide();
		grayDisplay.hide();
	});


	var pageTop = $('#page_top');
	pageTop.hide();

	$(window).scroll(function() {
		if ($(this).scrollTop() > 100) {
			pageTop.fadeIn(300);
	    } else {
	    	pageTop.fadeOut(300);
	    }
	});

	pageTop.click(function() {
		$('body, html').animate({
			scrollTop: 0
	    }, 500);
	    return false;
	});
});