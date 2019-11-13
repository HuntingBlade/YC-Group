package com.shytong.modules.sys.role.model;


import com.shytong.common.model.CommSqlDto;

import java.util.Date;

public class SysChannelRoleDo extends CommSqlDto {

  private String scrId;
  private String roleId;
  private String channelId;
  private String validValue;
  private String validType;
  private String info;
  private Date gmtCreate;
  private String creatorId;
  private Date gmtModified;
  private String editorId;
  private long isDeleted;


  public String getScrId() {
    return scrId;
  }

  public void setScrId(String scrId) {
    this.scrId = scrId;
  }


  public String getRoleId() {
    return roleId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }


  public String getChannelId() {
    return channelId;
  }

  public void setChannelId(String channelId) {
    this.channelId = channelId;
  }


  public String getValidValue() {
    return validValue;
  }

  public void setValidValue(String validValue) {
    this.validValue = validValue;
  }


  public String getValidType() {
    return validType;
  }

  public void setValidType(String validType) {
    this.validType = validType;
  }


  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }


  public Date getGmtCreate() {
    return gmtCreate;
  }

  public void setGmtCreate(Date gmtCreate) {
    this.gmtCreate = gmtCreate;
  }


  public String getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(String creatorId) {
    this.creatorId = creatorId;
  }


  public Date getGmtModified() {
    return gmtModified;
  }

  public void setGmtModified(Date gmtModified) {
    this.gmtModified = gmtModified;
  }


  public String getEditorId() {
    return editorId;
  }

  public void setEditorId(String editorId) {
    this.editorId = editorId;
  }


  public long getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(long isDeleted) {
    this.isDeleted = isDeleted;
  }

}
