package com.shytong.modules.wx.model;
import com.shytong.common.model.CommSqlDto;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * @Package com.shytong.modules.wx.model
 * @Description:
 * @date 2018-04-16 19:04:41
 */
public class WxMpDo extends CommSqlDto  implements Serializable {
    private static final long serialVersionUID = 1L;
   /**
    *id
     **/
    private String id;

   /**
    *wx_code
     **/
    private String wxCode;

   /**
    *appid
     **/
    private String appId;

   /**
    *密钥
     **/
    private String secret;

   /**
    *服务url
     **/
    private String serviceUrl;

   /**
    *token
     **/
    private String serviceToken;

   /**
    *消息加密密钥
     **/
    private String encodingKey;

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


    public void setId(String id){
        this.id = id;
        }

    public String getId(){
        return   id;
        }

    public void setWxCode(String wxCode){
        this.wxCode = wxCode;
        }

    public String getWxCode(){
        return   wxCode;
        }

    public void setAppId(String appId){
        this.appId = appId;
        }

    public String getAppId(){
        return   appId;
        }

    public void setSecret(String secret){
        this.secret = secret;
        }

    public String getSecret(){
        return   secret;
        }

    public void setServiceUrl(String serviceUrl){
        this.serviceUrl = serviceUrl;
        }

    public String getServiceUrl(){
        return   serviceUrl;
        }

    public void setServiceToken(String serviceToken){
        this.serviceToken = serviceToken;
        }

    public String getServiceToken(){
        return   serviceToken;
        }

    public void setEncodingKey(String encodingKey){
        this.encodingKey = encodingKey;
        }

    public String getEncodingKey(){
        return   encodingKey;
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



    @Override
    public String syFields(){
        return "id,wx_code,app_id,secret,service_url,service_token,encoding_key,gmt_create,creator_id,gmt_modified,editor_id,is_deleted";
        }
    @Override
    public String syTableName(){
        return "sy_wx_mp";
    }
    @Override
    public String syPrimaryKey(){
        return "id";
    }
}
