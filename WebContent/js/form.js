$(function() {
	$("#login-btn").click(function(e) {
		$("#link-login").on("click", myHandler);
	});
	$("#login-btn").click((function(e) {
		let count = 0;
		return function() {
			count++;
		    if (count == 7) {
		    	$("#link-login").off("click", myHandler);
		    }
		}
	})())
	

	$("#logout-btn").click(function() {
		if (!confirm("ログアウトしますか？")) {
    		return false;
    	} else {
    		return ture;
    	}
	})

	

	$("#menu-img").change(function(e) {
        const file = e.target.files[0];
        reader = new FileReader();
        $preview = $(".preview-img");
        t = this;

        if (file.type.indexOf("image") < 0) {
            return false;
        }

        reader.onload = (function(file) {
            return function(e) {
                $preview.empty();
                $preview.append($('<img>').attr({
                    src: e.target.result,
                    width: "400px",
                    title: file.name
                }));
            };
        })(file);
        reader.readAsDataURL(file);
    });


	
	$(".add").click(function() {
        const addForm = $(this).parent().clone(true).insertAfter($(this).parent());
        $(this).parent().next().find(".select-numbers").val("0.0");
        $(this).parent().next().find(".select-foods").val("1");
    });
    $(".del").click(function() {
        const target = $(this).parent();
        var selectCount = $(".menu-food").length;
        if (selectCount > 1) {
            target.remove();
        }
    });


    
    $("#register").click(function() {
    	var menuFoodLength = $(".menu-food").length;
    	$("#menu-food-length").val(menuFoodLength);
    });

    $("#delete").click(function() {
    	if (!confirm("本当に削除しますか？")) {
    		return false;
    	} else {
    		return ture;
    	}
    });


    function myHandler(e) {
    	e.preventDefault();
    }
});