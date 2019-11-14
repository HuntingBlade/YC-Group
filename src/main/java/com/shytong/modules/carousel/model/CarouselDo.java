package com.shytong.modules.carousel.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 轮播实体类
 * @Author: CL
 * @Date: 2019/11/12 20:15
 */
public class CarouselDo implements Serializable {
    private static final long serialVersionUID = -9142055067436900946L;
    /**
     * 轮播编号
     */
    private String id;
    /**
     * 所属栏目编号
     */
    private Integer channelId;
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
    private Integer isUp;
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

    public Integer getIsUp() {
        return isUp;
    }

    public void setIsUp(Integer isUp) {
        this.isUp = isUp;
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
                ", channelId=" + channelId +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", sort=" + sort +
                ", status='" + status + '\'' +
                ", isUp=" + isUp +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", creatorId='" + creatorId + '\'' +
                ", editorId='" + editorId + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
