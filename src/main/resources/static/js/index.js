$(function () {
    // 首页文章标题字符串截取
    var titleTemp = $("#recommendArticleTitle").text();
    var summaryTemp = $("#recommendArticleSummary").text();
    $("#recommendArticleTitle").text(titleTemp.substring(0, 29) + "...");
    $("#recommendArticleSummary").text(summaryTemp.substring(0, 72) + "...");
    var len = $("#newsList").children("li").length;
    for (var i = 1; i <= len; i++) {
        var newsTitle = $("#newsListTitle" + i).text();
        if (newsTitle.length > 19) {
            $("#newsListTitle" + i).text(newsTitle.substring(0, 20) + "...");
        }
    }

    // 首页文章的日期字符串截取
    for (var i = 0; i < len; i++) {
        var str = $("#span" + i).text();
        $("#span" + i).text(str.substring(5, 10));
        $("#i" + i).text(str.substring(0, 4));
    }

    // 设置首页轮播
    layui.use('carousel', function () {
        var carousel = layui.carousel;
        //建造实例
        carousel.render({
            elem: '#test1',
            width: '100%', //设置容器宽度
            height: '100%',
            arrow: 'always' //始终显示箭头
        });
    });
});