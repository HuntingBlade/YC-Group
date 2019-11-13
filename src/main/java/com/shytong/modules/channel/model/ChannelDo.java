package com.shytong.modules.channel.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 栏目实体类
 * @author: CL
 * @time: 2019/11/12 16:54
 */
public class ChannelDo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 栏目编号
     */
    private Integer id;
    /**
     * 栏目名称
     */
    private String name;
    /**
     * 父栏目编号
     */
    private Integer parentId;
    /**
     * 分组
     */
    private String group;
    /**
     * 内容页名称
     */
    private String pageName;
    /**
     * 索引页名称
     */
    private String indexName;
    /**
     * 索引页数量
     */
    private String indexCount;
    /**
     * 页面模板
     */
    private String pageTemplate;
    /**
     * 列表页名称
     */
    private String listName;
    /**
     * 列表页数量
     */
    private String listCount;
    /**
     * 列表页模板
     */
    private String listTemplate;
    /**
     * 排序
     */
    private Integer sort;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getIndexCount() {
        return indexCount;
    }

    public void setIndexCount(String indexCount) {
        this.indexCount = indexCount;
    }

    public String getPageTemplate() {
        return pageTemplate;
    }

    public void setPageTemplate(String pageTemplate) {
        this.pageTemplate = pageTemplate;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getListCount() {
        return listCount;
    }

    public void setListCount(String listCount) {
        this.listCount = listCount;
    }

    public String getListTemplate() {
        return listTemplate;
    }

    public void setListTemplate(String listTemplate) {
        this.listTemplate = listTemplate;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
        return "ChannelDo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", group='" + group + '\'' +
                ", pageName='" + pageName + '\'' +
                ", indexName='" + indexName + '\'' +
                ", indexCount='" + indexCount + '\'' +
                ", pageTemplate='" + pageTemplate + '\'' +
                ", listName='" + listName + '\'' +
                ", listCount='" + listCount + '\'' +
                ", listTemplate='" + listTemplate + '\'' +
                ", sort=" + sort +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", creatorId='" + creatorId + '\'' +
                ", editorId='" + editorId + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
