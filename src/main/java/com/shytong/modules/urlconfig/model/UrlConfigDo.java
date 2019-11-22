package com.shytong.modules.urlconfig.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 链接配置实体类
 * @Author: CL
 * @Date: 2019/11/21 12:07
 */
public class UrlConfigDo implements Serializable {
    private static final long serialVersionUID = 8521631257739038256L;

    /**
     * 链接编号
     */
    private Integer urlId;
    /**
     * 链接名称
     */
    private String urlName;
    /**
     * 链接分组
     */
    private String urlGroup;
    /**
     * 链接图片
     */
    private String urlImg;
    /**
     * 链接地址
     */
    private String urlAddress;
    /**
     * 链接类型
     */
    private String urlType;
    /**
     * 链接排序
     */
    private Integer urlSort;
    /**
     * 链接状态
     */
    private Integer urlState;
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

    public Integer getUrlId() {
        return urlId;
    }

    public void setUrlId(Integer urlId) {
        this.urlId = urlId;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getUrlGroup() {
        return urlGroup;
    }

    public void setUrlGroup(String urlGroup) {
        this.urlGroup = urlGroup;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getUrlAddress() {
        return urlAddress;
    }

    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public Integer getUrlSort() {
        return urlSort;
    }

    public void setUrlSort(Integer urlSort) {
        this.urlSort = urlSort;
    }

    public Integer getUrlState() {
        return urlState;
    }

    public void setUrlState(Integer urlState) {
        this.urlState = urlState;
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
        return "UrlConfigDo{" +
                "urlId=" + urlId +
                ", urlName='" + urlName + '\'' +
                ", urlGroup='" + urlGroup + '\'' +
                ", urlImg='" + urlImg + '\'' +
                ", urlAddress='" + urlAddress + '\'' +
                ", urlType='" + urlType + '\'' +
                ", urlSort=" + urlSort +
                ", urlState=" + urlState +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", creatorId='" + creatorId + '\'' +
                ", editorId='" + editorId + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
