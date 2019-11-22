package com.shytong.modules.article.model;


import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 文章实体类
 * @Author: CL
 * @Date: 2019/11/12 20:19
 */
public class ArticleDo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 文章编号
     */
    private String id;
    /**
     * 所属栏目编号
     */
    private Integer channelId;
    /**
     * 文章发布时间
     */
    private Date postTime;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章标题图片
     */
    private String titleImg;
    /**
     * 文章摘要
     */
    private String summary;
    /**
     * 类型（图片，视频，外链）
      */
    private String type;
    /**
     * 作者
     */
    private String author;
    /**
     * 来源
     */
    private String source;
    /**
     * 内容
     */
    private String content;
    /**
     * 是否为推荐
     */
    private String recommend;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 搜索关键字
     */
    private String keyword;
    /**
     * 点击量
     */
    private String clickCount;
    /**
     * 置顶优先级
     */
    private Integer topPrior;
    /**
     * 链接
     */
    private String url;
    /**
     * 状态
     */
    private String status;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
    /**
     * 创建者ID
     */
    private String creatorId;
    /**
     * 修改者ID
     */
    private String editorId;
    /**
     * 是否删除（1-是 / 0-否）
     */
    private Integer isDeleted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getClickCount() {
        return clickCount;
    }

    public void setClickCount(String clickCount) {
        this.clickCount = clickCount;
    }

    public Integer getTopPrior() {
        return topPrior;
    }

    public void setTopPrior(Integer topPrior) {
        this.topPrior = topPrior;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getEditorId() {
        return editorId;
    }

    public void setEditorId(String editorId) {
        this.editorId = editorId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "ArticleDo{" +
                "id='" + id + '\'' +
                ", channelId=" + channelId +
                ", postTime=" + postTime +
                ", title='" + title + '\'' +
                ", titleImg='" + titleImg + '\'' +
                ", summary='" + summary + '\'' +
                ", type='" + type + '\'' +
                ", author='" + author + '\'' +
                ", source='" + source + '\'' +
                ", content='" + content + '\'' +
                ", recommend='" + recommend + '\'' +
                ", sort=" + sort +
                ", keyword='" + keyword + '\'' +
                ", clickCount='" + clickCount + '\'' +
                ", topPrior=" + topPrior +
                ", url='" + url + '\'' +
                ", status='" + status + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", creatorId='" + creatorId + '\'' +
                ", editorId='" + editorId + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
