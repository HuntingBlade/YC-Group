package com.shytong.modules.sys.user.model;
import com.shytong.common.model.CommSqlDto;
import java.io.Serializable;

/**
 * @author 
 * @Package com.shytong.modules.sys.user.model
 * @Description:
 * @date 2018-05-23 20:30:31
 */
public class SysUserDto extends CommSqlDto  implements Serializable {
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
    *图形验证码
     **/
    private String verifyCode;

   /**
    *短信验证码
     **/
    private String smsCode;

   /**
    *旧密码
     **/
    private String oldPwd;

   /**
    *机构id
     **/
    private String orgId;

   /**
    *类型
     **/
    private String type;

   /**
    *系统码
     **/
    private String systemCode;


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

    public void setVerifyCode(String verifyCode){
        this.verifyCode = verifyCode;
        }

    public String getVerifyCode(){
        return   verifyCode;
        }

    public void setSmsCode(String smsCode){
        this.smsCode = smsCode;
        }

    public String getSmsCode(){
        return   smsCode;
        }

    public void setOldPwd(String oldPwd){
        this.oldPwd = oldPwd;
        }

    public String getOldPwd(){
        return   oldPwd;
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

    public void setSystemCode(String systemCode){
        this.systemCode = systemCode;
        }

    public String getSystemCode(){
        return   systemCode;
        }


}
