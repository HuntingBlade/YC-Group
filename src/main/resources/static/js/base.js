$(function () {
    /**
     * 导航栏选中判断
     * @type {string}
     */
    var url = window.location.pathname;
    if (url == "/") {
        $("#chaId0").addClass("active");
    }
    var length = $("#channelList").children("a").length;
    for (var i = 0; i < length; i++) {
        var href = $("#chaId" + i).attr('href');
        var _href = href.substring(0,href.indexOf("?"));
        if (url === _href) {
            $("#chaId" + i).addClass("active");
            break;
        }
    }

    /**
     * 第一个下拉框
     */
    $("#selectOne").on("change", function () {
        var value = $("option:selected", this).val();
        if (value == 0) {
            return;
        }
        window.open(value);
    });

    /**
     * 第二个下拉框
     */
    $("#selectTwo").on("change", function () {
        var value = $("option:selected", this).val();
        if (value == 0) {
            return;
        }
        window.open(value);
    });

    /**
     * 第三个下拉框
     */
    $("#selectThree").on("change", function () {
        var value = $("option:selected", this).val();
        if (value == 0) {
            return;
        }
        window.open(value);
    })
});

