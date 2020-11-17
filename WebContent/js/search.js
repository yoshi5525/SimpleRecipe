$(function() {
	$("#search-box").on("input", function() {
		$.ajax({
			url: "http://localhost:8080/SimpleRecipe/search",
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

	function buildSearchHTML(menu) {
		var html = `<li class="search-list-item ml-2 py-2"><a href="show?id=${menu.id}">${menu.name}</a></li>`;
		return html;
	}
});