package com.shytong.modules.carousel.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 轮播实体类
 * @Author: CL
 * @Date: 2019/11/12 20:15
 */
public class CarouselDo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 轮播编号
     */
    private String id;
    /**
     * 轮播标题
     */
    private String title;
    /**
     * 轮播来源
     */
    private String source;
    /**
     * 轮播类型（图片,视频）
     */
    private String type;
    /**
     * 链接
     */
    private String url;
    /**
     * 轮播显示时间
     */
    private String showTime;
    /**
     * 轮播默认下标开始播放
     */
    private Integer defaultIndex;
    /**
     * 轮播间隔时间
     */
    private String interval;
    /**
     * 轮播循环状态
     */
    private Integer cycleType;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态
     */
    private String status;
    /**
     * 是否启用(1-是 / 0-否)
     */
    private Integer enable;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public Integer getDefaultIndex() {
        return defaultIndex;
    }

    public void setDefaultIndex(Integer defaultIndex) {
        this.defaultIndex = defaultIndex;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public Integer getCycleType() {
        return cycleType;
    }

    public void setCycleType(Integer cycleType) {
        this.cycleType = cycleType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
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
        return "CarouselDo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", showTime='" + showTime + '\'' +
                ", defaultIndex=" + defaultIndex +
                ", interval='" + interval + '\'' +
                ", cycleType=" + cycleType +
                ", sort=" + sort +
                ", status='" + status + '\'' +
                ", enable=" + enable +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", creatorId='" + creatorId + '\'' +
                ", editorId='" + editorId + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
