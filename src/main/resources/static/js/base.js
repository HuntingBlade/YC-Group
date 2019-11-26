$(function () {
    // 判断导航栏选中
    var url = window.location.pathname;
    var length = $("#channelList").children("a").length;
    $("#chaId0").addClass("active");
    for (var i = 0; i < length; i++) {
        var href = $("#chaId" + i).attr('href');
        if (url == href){
            $("#chaId0").removeClass("active");
            $("#chaId" + i).addClass("active");
        }
    }

    // 第一个下拉框
    $("#selectOne").on("change", function () {
        var value = $("option:selected", this).val();
        if (value == 0) {
            return;
        }
        window.open(value);
    });

    // 第二个下拉框
    $("#selectTwo").on("change", function () {
        var value = $("option:selected", this).val();
        if (value == 0) {
            return;
        }
        window.open(value);
    });

    // 第三个下拉框
    $("#selectThree").on("change", function () {
        var value = $("option:selected", this).val();
        if (value == 0) {
            return;
        }
        window.open(value);
    })
});