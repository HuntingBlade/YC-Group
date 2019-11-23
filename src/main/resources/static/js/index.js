$(function () {
    // 文章字符串截取
    var titleTemp = $("#recommendArticleTitle").text();
    var summaryTemp = $("#recommendArticleSummary").text();
    $("#recommendArticleTitle").text(titleTemp.substring(0, 29) + "...");
    $("#recommendArticleSummary").text(summaryTemp.substring(0, 72) + "...");

    // 日期字符串截取
    var len = $("#newsList").children("li").length;
    console.log("长度", len);
    for (var i = 1; i <= len; i++) {
        var day = $("#dayFormat" + i).text();
        console.log("dayText", day);
        var year = $("#yearFormat" + i).text();
        $("#dayFormat" + i).text(day.substring(4, 9));
        $("#yearFormat" + i).text(year.substring(0, 4));
    }

});