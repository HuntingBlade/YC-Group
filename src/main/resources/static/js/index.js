$(function () {
    // 文章字符串截取
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

    layui.use('carousel', function () {
        var carousel = layui.carousel;
        //建造实例
        carousel.render({
            elem: '#test1',
            width: '100%', //设置容器宽度
            height: '100%',
            arrow: 'always' //始终显示箭头
            // ,anim: 'updown' //切换动画方式
        });
    });

    for (var i = 0; i < len; i++) {
        var date = timeTransform("#span" + i);
        $("#span" + i).text(date.substring(5, 10));
        $("#i" + i).text(date.substring(0, 4));
    }
});


function timeTransform(ele) {
    var time = $(ele).text();
    var d = new Date(time);
    var times = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes()
    return times;
}
