package com.shytong.modules.resourceLib.model;

public class ResourcelibDo {

  private long resId;
  private String resName;
  private String resPath;
  private String resType;
  private java.sql.Timestamp gmtCreate;
  private String creatorId;
  private java.sql.Timestamp gmtModified;
  private String editorId;
  private long isDeleted;


  public long getResId() {
    return resId;
  }

  public void setResId(long resId) {
    this.resId = resId;
  }


  public String getResName() {
    return resName;
  }

  public void setResName(String resName) {
    this.resName = resName;
  }


  public String getResPath() {
    return resPath;
  }

  public void setResPath(String resPath) {
    this.resPath = resPath;
  }


  public String getResType() {
    return resType;
  }

  public void setResType(String resType) {
    this.resType = resType;
  }


  public java.sql.Timestamp getGmtCreate() {
    return gmtCreate;
  }

  public void setGmtCreate(java.sql.Timestamp gmtCreate) {
    this.gmtCreate = gmtCreate;
  }


  public String getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(String creatorId) {
    this.creatorId = creatorId;
  }


  public java.sql.Timestamp getGmtModified() {
    return gmtModified;
  }

  public void setGmtModified(java.sql.Timestamp gmtModified) {
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

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("ResourcelibDo{");
    sb.append("resId=").append(resId);
    sb.append(", resName='").append(resName).append('\'');
    sb.append(", resPath='").append(resPath).append('\'');
    sb.append(", resType='").append(resType).append('\'');
    sb.append(", gmtCreate=").append(gmtCreate);
    sb.append(", creatorId='").append(creatorId).append('\'');
    sb.append(", gmtModified=").append(gmtModified);
    sb.append(", editorId='").append(editorId).append('\'');
    sb.append(", isDeleted=").append(isDeleted);
    sb.append('}');
    return sb.toString();
  }
}
