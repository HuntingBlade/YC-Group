$(function () {
    // 判断导航栏选中
    var url = window.location.pathname;
    var length = $("#channelList").children("a").length;
    $("#chaId0").addClass("active");
    for (var i = 0; i < length; i++) {
        var href = $("#chaId" + i).attr('href');
        if (url == href) {
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
    });

    var tophtml = "<div id=\"izl_rmenu\" class=\"izl-rmenu\"><a href=\"tencent://Message/?Uin=884127600&websiteName=���߿ͷ�=&Menu=yes\" class=\"btn btn-qq\"></a><div class=\"btn btn-wx\"></div><div class=\"btn btn-phone\"><div class=\"phone\">0591-87588017</div></div><div class=\"btn btn-top\"></div></div>";
    $("#top").html(tophtml);
    $("#izl_rmenu").each(function () {
        $(this).find(".btn-wx").mouseenter(function () {
            $(this).find(".pic").fadeIn("fast");
        });
        $(this).find(".btn-wx").mouseleave(function () {
            $(this).find(".pic").fadeOut("fast");
        });
        $(this).find(".btn-phone").mouseenter(function () {
            $(this).find(".phone").fadeIn("fast");
        });
        $(this).find(".btn-phone").mouseleave(function () {
            $(this).find(".phone").fadeOut("fast");
        });
        $(this).find(".btn-top").click(function () {
            $("html, body").animate({
                "scroll-top": 0
            }, "fast");
        });
    });
    var lastRmenuStatus = false;
    $(window).scroll(function () {//bug
        var _top = $(window).scrollTop();
        if (_top > 200) {
            $("#izl_rmenu").data("expanded", true);
        } else {
            $("#izl_rmenu").data("expanded", false);
        }
        if ($("#izl_rmenu").data("expanded") != lastRmenuStatus) {
            lastRmenuStatus = $("#izl_rmenu").data("expanded");
            if (lastRmenuStatus) {
                $("#izl_rmenu .btn-top").slideDown();
            } else {
                $("#izl_rmenu .btn-top").slideUp();
            }
        }
    });
});