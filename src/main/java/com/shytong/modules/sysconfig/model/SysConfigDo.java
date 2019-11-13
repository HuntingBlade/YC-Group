package com.shytong.modules.sysconfig.model;

import com.shytong.common.model.CommSqlDto;

import java.util.Date;

/**
 * @description: 系统配置实体类
 * @author: CL
 * @time: 2019/11/11 15:42
 */
public class SysConfigDo{
    /**
     * 编号
     */
    private String id;
    /**
     * 分组
     */
    private String sysGroup;
    /**
     * 键
     */
    private String sysKey;
    /**
     * 值
     */
    private String sysValue;
    /**
     * 名称
     */
    private String sysName;
    /**
     * 类型
     */
    private String type;
    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否启用（1-是 / 0-否）
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

    public String getSysGroup() {
        return sysGroup;
    }

    public void setSysGroup(String sysGroup) {
        this.sysGroup = sysGroup;
    }

    public String getSysKey() {
        return sysKey;
    }

    public void setSysKey(String sysKey) {
        this.sysKey = sysKey;
    }

    public String getSysValue() {
        return sysValue;
    }

    public void setSysValue(String sysValue) {
        this.sysValue = sysValue;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
        return "SysConfigDo{" +
                "id='" + id + '\'' +
                ", sysGroup='" + sysGroup + '\'' +
                ", sysKey='" + sysKey + '\'' +
                ", sysValue='" + sysValue + '\'' +
                ", sysName='" + sysName + '\'' +
                ", type='" + type + '\'' +
                ", sort=" + sort +
                ", enable=" + enable +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", creatorId='" + creatorId + '\'' +
                ", editorId='" + editorId + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
