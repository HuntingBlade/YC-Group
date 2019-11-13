package com.shytong.modules.sys.role.model;
import com.shytong.common.model.CommSqlDto;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * @Package com.shytong.modules.sys.role.model
 * @Description:
 * @date 2018-04-06 16:56:51
 */
public class UserRoleDo extends CommSqlDto  implements Serializable {
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
    *角色ID
     **/
    private String roleId;

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

    public void setUserId(String userId){
        this.userId = userId;
        }

    public String getUserId(){
        return   userId;
        }

    public void setRoleId(String roleId){
        this.roleId = roleId;
        }

    public String getRoleId(){
        return   roleId;
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


}
