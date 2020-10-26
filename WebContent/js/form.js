$(function () {
    $(".add").click(function () {
        $(this).parent().clone(true).insertAfter($(this).parent());
    });
    $(".del").click(function () {
        const target = $(this).parent();
        if (target.parent().children.length > 1) {
            target.remove();
        }
    });

    $("#menu-img").change(function (e) {
        const file = e.target.files[0];
        reader = new FileReader();
        $preview = $(".preview-img");
        t = this;

        if (file.type.indexOf("image") < 0) {
            return false;
        }

        reader.onload = (function (file) {
            return function (e) {
                $preview.empty();
                $preview.append($('<img>').attr({
                    src: e.target.result,
                    width: "400px",
                    class: "preview-img",
                    title: file.name
                }));
            };
        })(file);
        reader.readAsDataURL(file);
    });
});