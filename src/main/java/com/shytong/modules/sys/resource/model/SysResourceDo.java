package com.shytong.modules.sys.resource.model;
import com.shytong.common.model.CommSqlDto;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * @Package com.shytong.modules.sys.resource.model
 * @Description:
 * @date 2018-04-10 16:55:19
 */
public class SysResourceDo extends CommSqlDto  implements Serializable {
    private static final long serialVersionUID = 1L;
   /**
    *资源id
     **/
    private String id;

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
    *创建时间
     **/
    private Date gmtCreate;

   /**
    *创建者id
     **/
    private String creatorId;

   /**
    *修改时间
     **/
    private Date gmtModified;

   /**
    *修改者id
     **/
    private String editorId;

   /**
    *是否删除
     **/
    private Integer isDeleted;

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
    *子类型
     **/
    private String subType;


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

    public void setGmtCreate(Date gmtCreate){
        this.gmtCreate = gmtCreate;
        }

    public Date getGmtCreate(){
        return   gmtCreate;
        }

    public void setCreatorId(String creatorId){
        this.creatorId = creatorId;
        }

    public String getCreatorId(){
        return   creatorId;
        }

    public void setGmtModified(Date gmtModified){
        this.gmtModified = gmtModified;
        }

    public Date getGmtModified(){
        return   gmtModified;
        }

    public void setEditorId(String editorId){
        this.editorId = editorId;
        }

    public String getEditorId(){
        return   editorId;
        }

    public void setIsDeleted(Integer isDeleted){
        this.isDeleted = isDeleted;
        }

    public Integer getIsDeleted(){
        return   isDeleted;
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

    public void setSubType(String subType){
        this.subType = subType;
        }

    public String getSubType(){
        return   subType;
        }



    @Override
    public String syFields(){
        return "id,org_id,code,type_code,type,name,description,param,info,gmt_create,creator_id,gmt_modified,editor_id,is_deleted,parent_id,op_type,valid_type,valid_value,sub_type";
        }
    @Override
    public String syTableName(){
        return "sy_sys_resource";
    }
    @Override
    public String syPrimaryKey(){
        return "id";
    }
}
