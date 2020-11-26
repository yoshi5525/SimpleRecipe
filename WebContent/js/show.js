$(function() {
	const foodLength = $(".registered-food-quantity").length;
	const foods = [];
	for (var i = 0; i < foodLength; i++) {
		foods.push($(".registered-food-quantity").eq(i).val());
	}

	var foodStuffLine = $("#foodstuff-text").val().match(/･/g).length * 30;
	var recipeLine = $("#recipe-text").val().match(/:/g).length * 80;
	$("#foodstuff-text").height(foodStuffLine);
	$("#recipe-text").height(recipeLine);


	$("#calc-food").on("input", function() {
		const calcFood = ($(this).val()) / 100;
		const calcItem = ($("#calc-items").val()) / 100;
		const calcValue = calcFood - calcItem;

		if (calcValue >= 0) {
			for (var i = 0; i < foodLength; i++) {
				if (!($('.food-text').eq(i).text() === "ゆで水") && !($('.food-text').eq(i).text() === "ゆで塩")) {
					const value = (foods[i] * calcValue).toFixed(1);
					$(".registered-food-quantity").eq(i).val(value);
					$("#calc-message").text("");
				}
			}
		} else {
			$("#calc-message").text("食材重量のほうが大きくなるように入力してください!!");
		}
	});

	$("#calc-items").on("input", function() {
		const calcFood = ($("#calc-food").val()) / 100;
		const calcItem = ($(this).val()) / 100;
		const calcValue = calcFood - calcItem;

		if (calcValue >= 0) {
			for (var i = 0; i < foodLength; i++) {
				if (!($('.food-text').eq(i).text() === "ゆで水") && !($('.food-text').eq(i).text() === "ゆで塩")) {
					const value = (foods[i] * calcValue).toFixed(1);
					$(".registered-food-quantity").eq(i).val(value);
					$("#calc-message").text("");
				}
			}
		} else {
			$("#calc-message").text("食材重量のほうが大きくなるように入力してください!");
		}
	});


	if ($(".food-pre-weight").length) {
		var wordFood = " ⇒ 上記の下茹で食材の重量を引いた量";
		var wordItem = " ⇒ 上記の下茹で鍋･ボウルの重量を引いた量";
		$(".food-weight").append(wordFood);
		$(".food-items-weight").append(wordItem);

		$("#calc-pre-food").on("input", function() {
			const calcPreFood = ($(this).val()) / 10;
			const calcPreItem = ($("#calc-pre-items").val()) / 10;
			const calcPreValue = calcPreFood - calcPreItem;

			if (calcPreValue >= 0) {
				for (var i = 0; i < foodLength; i++) {
					if (($('.food-text').eq(i).text() === "ゆで水") || ($('.food-text').eq(i).text() === "ゆで塩")) {
						const value = (foods[i] * calcPreValue).toFixed(1);
						$(".registered-food-quantity").eq(i).val(value);
						$("#calc-pre-message").text("");
					}
				}
			} else {
				$("#calc-pre-message").text("下茹で食材重量のほうが大きくなるように入力してください！！");
			}
		});

		$("#calc-pre-items").on("input", function() {
			const calcPreFood = ($("#calc-pre-food").val()) / 10;
			const calcPreItem = ($(this).val()) / 10;
			const calcPreValue = calcPreFood - calcPreItem;

			if (calcPreValue >= 0) {
				for (var i = 0; i < foodLength; i++) {
					if (($('.food-text').eq(i).text() === "ゆで水") || ($('.food-text').eq(i).text() === "ゆで塩")) {
						const value = (foods[i] * calcPreValue).toFixed(1);
						$(".registered-food-quantity").eq(i).val(value);
						$("#calc-pre-message").text("");
					}
				}
			} else {
				$("#calc-pre-message").text("下茹で食材重量のほうが大きくなるように入力してください！！");
			}
		});
	};


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