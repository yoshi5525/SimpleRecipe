$(function() {
	const foodLength = $(".registered-food-quantity").length;
	const foods = [];
	for (var i = 0; i < foodLength; i++) {
		foods.push($(".registered-food-quantity").eq(i).val());
	}

	$("#calc-food").on("input", function() {
		const calcFood = ($(this).val()) / 100;
		const calcItem = ($("#calc-items").val()) / 100;
		const calcValue = calcFood - calcItem;

		if (calcValue >= 0) {
			for (var i = 0; i < foodLength; i++) {
				const value = (foods[i] * calcValue).toFixed(1);
				$(".registered-food-quantity").eq(i).val(value);
				$("#calc-message").text("");
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
				const value = (foods[i] * calcValue).toFixed(1);
				$(".registered-food-quantity").eq(i).val(value);
				$("#calc-message").text("");
			}
		} else {
			$("#calc-message").text("食材重量のほうが大きくなるように入力してください!");
		}
	});

	$(function() {
		var foodStuffLine = $("#foodstuff-text").val().match(/･/g).length * 25;
		var recipeLine = $("#recipe-text").val().match(/:/g).length * 25;
		$("#foodstuff-text").height(foodStuffLine);
		$("#recipe-text").height(recipeLine);
	});

//	var numberFormat = [];
//	numberFormat.push($("[data-type='number']"));
//	for (var i = 0; i < numberFormat.length; i++) {
//		numberFormat[i].on("input", function(e) {
//			var target = e.target;
//			var data = target.value[target.value.length-1];
//			if (!data.match(/[0-9]/)) {
//				target.value = target.value.slice(0, target.value.length-1);
//			}
//			target.value = target.value.replace(/,/g, '').replace(/(\d)(?=(\d\d\d)+(?!\d))/g, '$1,');
//		});
//	};
});