package com.shytong.modules.user.model;
import com.shytong.common.model.CommSqlDto;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * @Package com.shytong.modules.user.model
 * @Description:
 * @date 2018-04-17 11:15:09
 */
public class UserAuthDo extends CommSqlDto  implements Serializable {
    private static final long serialVersionUID = 1L;
   /**
    *id
     **/
    private String id;

   /**
    *用户id
     **/
    private String userId;

   /**
    *类型
     **/
    private Integer type;

   /**
    *系统标识
     **/
    private String sysIdentifier;

   /**
    *标识
     **/
    private String identifier;

   /**
    *凭证
     **/
    private String certificate;

   /**
    *创建时间
     **/
    private Date gmtCreate;

   /**
    *是否删除
     **/
    private Integer isDeleted;


    public void setId(String id){
        this.id = id;
        }

    public String getId(){
        return   id;
        }

    public void setUserId(String userId){
        this.userId = userId;
        }

    public String getUserId(){
        return   userId;
        }

    public void setType(Integer type){
        this.type = type;
        }

    public Integer getType(){
        return   type;
        }

    public void setSysIdentifier(String sysIdentifier){
        this.sysIdentifier = sysIdentifier;
        }

    public String getSysIdentifier(){
        return   sysIdentifier;
        }

    public void setIdentifier(String identifier){
        this.identifier = identifier;
        }

    public String getIdentifier(){
        return   identifier;
        }

    public void setCertificate(String certificate){
        this.certificate = certificate;
        }

    public String getCertificate(){
        return   certificate;
        }

    public void setGmtCreate(Date gmtCreate){
        this.gmtCreate = gmtCreate;
        }

    public Date getGmtCreate(){
        return   gmtCreate;
        }

    public void setIsDeleted(Integer isDeleted){
        this.isDeleted = isDeleted;
        }

    public Integer getIsDeleted(){
        return   isDeleted;
        }



    @Override
    public String syFields(){
        return "id,user_id,type,sys_identifier,identifier,certificate,gmt_create,is_deleted";
        }
    @Override
    public String syTableName(){
        return "sy_user_auth";
    }
    @Override
    public String syPrimaryKey(){
        return "id";
    }
}
