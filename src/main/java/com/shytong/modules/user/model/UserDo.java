package com.shytong.modules.user.model;
import com.shytong.common.model.CommSqlDto;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * @Package com.shytong.modules.user.model
 * @Description:
 * @date 2018-04-17 10:15:43
 */
public class UserDo extends CommSqlDto  implements Serializable {
    private static final long serialVersionUID = 1L;
   /**
    *id
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
    private Integer status;

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
    *国家
     **/
    private String country;

   /**
    *省份
     **/
    private String province;

   /**
    *城市
     **/
    private String city;

   /**
    *性别
     **/
    private String sex;

   /**
    *union_id
     **/
    private String unionId;

   /**
    *open_id
     **/
    private String openId;


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

    public void setStatus(Integer status){
        this.status = status;
        }

    public Integer getStatus(){
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

    public void setCountry(String country){
        this.country = country;
        }

    public String getCountry(){
        return   country;
        }

    public void setProvince(String province){
        this.province = province;
        }

    public String getProvince(){
        return   province;
        }

    public void setCity(String city){
        this.city = city;
        }

    public String getCity(){
        return   city;
        }

    public void setSex(String sex){
        this.sex = sex;
        }

    public String getSex(){
        return   sex;
        }

    public void setUnionId(String unionId){
        this.unionId = unionId;
        }

    public String getUnionId(){
        return   unionId;
        }

    public void setOpenId(String openId){
        this.openId = openId;
        }

    public String getOpenId(){
        return   openId;
        }



    @Override
    public String syFields(){
        return "id,nick_name,account,status,phone,pwd,avatar,gmt_create,creator_id,gmt_modified,editor_id,is_deleted,country,province,city,sex,union_id,open_id";
        }
    @Override
    public String syTableName(){
        return "sy_user";
    }
    @Override
    public String syPrimaryKey(){
        return "id";
    }
}
