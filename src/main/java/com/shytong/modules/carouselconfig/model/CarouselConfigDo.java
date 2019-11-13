package com.shytong.modules.carouselconfig.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author CL
 * @description 轮播配置实体类
 * @date 2019/11/13
 */
public class CarouselConfigDo implements Serializable {
    /**
     * 编号
     */
    private String id;
    /**
     * 样式
     */
    private String style;
    /**
     * 开始下标
     */
    private Integer startIndex;
    /**
     * 间隔时间
     */
    private String intervalTime;
    /**
     * 循环类型
     */
    private String cycleTyle;
    /**
     * 最大展示图片数量
     */
    private Integer maxShowCount;
    /**
     * 是否启用
     */
    private Integer isUp;
    /**
     * 排序
     */
    private Integer sort;
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public String getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(String intervalTime) {
        this.intervalTime = intervalTime;
    }

    public String getCycleTyle() {
        return cycleTyle;
    }

    public void setCycleTyle(String cycleTyle) {
        this.cycleTyle = cycleTyle;
    }

    public Integer getMaxShowCount() {
        return maxShowCount;
    }

    public void setMaxShowCount(Integer maxShowCount) {
        this.maxShowCount = maxShowCount;
    }

    public Integer getIsUp() {
        return isUp;
    }

    public void setIsUp(Integer isUp) {
        this.isUp = isUp;
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
        return "CarouselConfigDo{" +
                "id='" + id + '\'' +
                ", style='" + style + '\'' +
                ", startIndex=" + startIndex +
                ", intervalTime='" + intervalTime + '\'' +
                ", cycleTyle='" + cycleTyle + '\'' +
                ", maxShowCount=" + maxShowCount +
                ", isUp=" + isUp +
                ", sort=" + sort +
                ", status='" + status + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", creatorId='" + creatorId + '\'' +
                ", editorId='" + editorId + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
