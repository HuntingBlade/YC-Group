$(function () {
    var url = window.location.href;
    var defaultUrl = "http://" + location.host + "/";
    if (url == defaultUrl) {
        $("#chaId0").addClass("active");
    }
    var length = $("#channelList").children("a").length;
    for (var i = 0; i < length; i++) {
        var href = "http://" + window.location.host + $("#chaId" + i).attr('href');
        if (url === href) {
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

