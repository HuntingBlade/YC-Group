package com.shytong.sys.authorization;

import java.io.Serializable;

/**
 * @author 
 * @Package com.shytong.modules.sys.resource.model
 * @Description:
 * @date 2018-04-09 19:16:11
 */
public class CommResource  implements Serializable {
    private static final long serialVersionUID = 1L;
   /**
    *资源id
     **/
    private String id;

    private String subType;

   /**
    *机构id
     **/
    private String orgId;

   /**
    *编码
     **/
    private String code;

   /**
    *识别类型编码
     **/
    private String typeCode;

   /**
    *资源类型
     **/
    private String type;

   /**
    *资源名称
     **/
    private String name;

   /**
    *介绍
     **/
    private String description;

   /**
    *参数配置
     **/
    private String param;

   /**
    *信息
     **/
    private String info;



   /**
    *父id
     **/
    private String parentId;

   /**
    *操作类型
     **/
    private String opType;

   /**
    *有效性类型1 无限，2次数，3时间
     **/
    private String validType;

   /**
    *有效值
     **/
    private String validValue;

    /**
     *子活动id
     **/
    private String subActId;


    public void setId(String id){
        this.id = id;
        }

    public String getId(){
        return   id;
        }

    public void setOrgId(String orgId){
        this.orgId = orgId;
        }

    public String getOrgId(){
        return   orgId;
        }

    public void setCode(String code){
        this.code = code;
        }

    public String getCode(){
        return   code;
        }

    public void setTypeCode(String typeCode){
        this.typeCode = typeCode;
        }

    public String getTypeCode(){
        return   typeCode;
        }

    public void setType(String type){
        this.type = type;
        }

    public String getType(){
        return   type;
        }

    public void setName(String name){
        this.name = name;
        }

    public String getName(){
        return   name;
        }

    public void setDescription(String description){
        this.description = description;
        }

    public String getDescription(){
        return   description;
        }

    public void setParam(String param){
        this.param = param;
        }

    public String getParam(){
        return   param;
        }

    public void setInfo(String info){
        this.info = info;
        }

    public String getInfo(){
        return   info;
        }



    public void setParentId(String parentId){
        this.parentId = parentId;
        }

    public String getParentId(){
        return   parentId;
        }

    public void setOpType(String opType){
        this.opType = opType;
        }

    public String getOpType(){
        return   opType;
        }

    public void setValidType(String validType){
        this.validType = validType;
        }

    public String getValidType(){
        return   validType;
        }

    public void setValidValue(String validValue){
        this.validValue = validValue;
        }

    public String getValidValue(){
        return   validValue;
        }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getSubActId() {
        return subActId;
    }

    public void setSubActId(String subActId) {
        this.subActId = subActId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommResource that = (CommResource) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;
        if (validType != null ? !validType.equals(that.validType) : that.validType != null) return false;
        return validValue != null ? validValue.equals(that.validValue) : that.validValue == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (validType != null ? validType.hashCode() : 0);
        result = 31 * result + (validValue != null ? validValue.hashCode() : 0);
        return result;
    }
}
