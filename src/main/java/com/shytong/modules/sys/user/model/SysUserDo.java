package com.shytong.modules.sys.user.model;
import com.shytong.common.model.CommSqlDto;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * @Package com.shytong.modules.sys.user.model
 * @Description:
 * @date 2018-05-23 20:29:37
 */
public class SysUserDo extends CommSqlDto  implements Serializable {
    private static final long serialVersionUID = 1L;
   /**
    *用户id
     **/
    private String id;

   /**
    *昵称
     **/
    private String nickName;

   /**
    *账号
     **/
    private String account;

   /**
    *状态
     **/
    private String status;

   /**
    *手机号
     **/
    private String phone;

   /**
    *密码
     **/
    private String pwd;

   /**
    *头像
     **/
    private String avatar;

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
    *机构id
     **/
    private String orgId;

   /**
    *类型
     **/
    private String type;


    public void setId(String id){
        this.id = id;
        }

    public String getId(){
        return   id;
        }

    public void setNickName(String nickName){
        this.nickName = nickName;
        }

    public String getNickName(){
        return   nickName;
        }

    public void setAccount(String account){
        this.account = account;
        }

    public String getAccount(){
        return   account;
        }

    public void setStatus(String status){
        this.status = status;
        }

    public String getStatus(){
        return   status;
        }

    public void setPhone(String phone){
        this.phone = phone;
        }

    public String getPhone(){
        return   phone;
        }

    public void setPwd(String pwd){
        this.pwd = pwd;
        }

    public String getPwd(){
        return   pwd;
        }

    public void setAvatar(String avatar){
        this.avatar = avatar;
        }

    public String getAvatar(){
        return   avatar;
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

    public void setOrgId(String orgId){
        this.orgId = orgId;
        }

    public String getOrgId(){
        return   orgId;
        }

    public void setType(String type){
        this.type = type;
        }

    public String getType(){
        return   type;
        }



    @Override
    public String syFields(){
        return "id,nick_name,account,status,phone,pwd,avatar,gmt_create,creator_id,gmt_modified,editor_id,is_deleted,org_id,type";
        }
    @Override
    public String syTableName(){
        return "sy_sys_user";
    }
    @Override
    public String syPrimaryKey(){
        return "id";
    }
}
