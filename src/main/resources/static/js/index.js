$(function () {
    // 字符串截取
    var titleTemp = $("#recommendArticleTitle").text();
    var summaryTemp = $("#recommendArticleSummary").text();
    $("#recommendArticleTitle").text(titleTemp.substring(0, 29) + "...");
    $("#recommendArticleSummary").text(summaryTemp.substring(0, 72) + "...");
});