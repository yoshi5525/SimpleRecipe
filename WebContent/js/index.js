$(function() {
	var grayDisplay = $('#gray-display');
	var largeVideo = $('.video');
	grayDisplay.hide();
	largeVideo.hide();


	$('.visual-btn').click(function() {
		var windowWidth = $(window).width() * 0.6;;
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
	})
});