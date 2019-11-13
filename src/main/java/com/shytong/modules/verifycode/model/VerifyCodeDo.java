package com.shytong.modules.verifycode.model;
import com.shytong.common.model.CommSqlDto;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * @author shyTong
 * @Package com.shytong.modules.verifycode.model
 * @Description:
 * @date 2018-01-17 20:33:37
 */
public class VerifyCodeDo extends CommSqlDto {
   /**
    *
     **/
    private String id;

   /**
    *
     **/
    private String code;

   /**
    *
     **/
   @Length(max = 5)
    private String appId;

   /**
    *
     **/
    private String phone;

   /**
    *
     **/
    private String type;

   /**
    *
     **/
    private String status;

   /**
    *
     **/
    private Date expireTime;

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

    public void setCode(String code){
        this.code = code;
        }

    public String getCode(){
        return   code;
        }

    public void setAppId(String appId){
        this.appId = appId;
        }


    public String getAppId(){
        return   appId;
        }

    public void setPhone(String phone){
        this.phone = phone;
        }

    public String getPhone(){
        return   phone;
        }

    public void setType(String type){
        this.type = type;
        }

    public String getType(){
        return   type;
        }

    public void setStatus(String status){
        this.status = status;
        }

    public String getStatus(){
        return   status;
        }

    public void setExpireTime(Date expireTime){
        this.expireTime = expireTime;
        }

    public Date getExpireTime(){
        return   expireTime;
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



}
