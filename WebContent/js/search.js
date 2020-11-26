$(function() {
	var darkDisplay = $('#dark-display');
	darkDisplay.hide();

	var searchIcon = $('.search-icon');
	searchIcon.click(function() {
		darkDisplay.fadeIn(400);

		$(".search-box").on("input", function() {
			$.ajax({
				url: "http://localhost:8080/SimpleRecipe/searchAjax",
				type: "GET",
				data: {searchName: $(this).val()},
			})
			.done (function(menus) {
				var insertHTML = "";
				if ($(".search-list-item-dark") !== null) {
					$(".search-list-item-dark").remove();
				}

				if (menus.length !== 0) {
					$.each(menus, function(i, menu) {
						insertHTML += buildSearchHTMLDark(menu);
					});
					$(".result-list").append(insertHTML);
					$(".result-box").addClass("active");
				} else {
					$(".result-box").removeClass("active");
				}
			})
			.fail (function() {
				alert("検索失敗");
			})
		});
	});
	$('#close-btn').click(function() {
		darkDisplay.fadeOut(400);
	});

	$(window).resize(function() {
		var windowWidth = $(window).width();
		if(windowWidth >= 768) {
			darkDisplay.fadeOut(400);
		}
	});




	$("#search-box").on("input", function() {
		$.ajax({
			url: "http://localhost:8080/SimpleRecipe/searchAjax",
			type: "GET",
			data: {searchName: $(this).val()},
		})
		.done (function(menus) {
			var insertHTML = "";
			if ($(".search-list-item") !== null) {
				$(".search-list-item").remove();
			}

			if (menus.length !== 0) {
				$.each(menus, function(i, menu) {
					insertHTML += buildSearchHTML(menu);
				});
				$("#result-list").append(insertHTML);
				$("#result-box").css('display', 'block');
			} else {
				$("#result-box").css('display', 'none');
			}
		})
		.fail (function() {
			alert("検索失敗");
		})
	});

	function buildSearchHTML(menu) {
		var html = `<li class="search-list-item ml-2 py-2"><a href="show?id=${menu.id}">${menu.name}</a></li>`;
		return html;
	}

	function buildSearchHTMLDark(menu) {
		var html = `<li class="search-list-item-dark ml-2 py-2"><a href="show?id=${menu.id}">${menu.name}</a></li>`;
		return html;
	}
});