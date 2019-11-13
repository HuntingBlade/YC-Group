package com.shytong.modules.sys.role.model;
import com.shytong.common.model.CommSqlDto;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * @Package com.shytong.modules.sys.role.model
 * @Description:
 * @date 2018-04-08 12:09:22
 */
public class SysResourceRoleDo extends CommSqlDto  implements Serializable {
    private static final long serialVersionUID = 1L;
   /**
    *id
     **/
    private String id;

   /**
    *角色id
     **/
    private String roleId;

   /**
    *资源id
     **/
    private String resourceId;

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


    public void setId(String id){
        this.id = id;
        }

    public String getId(){
        return   id;
        }

    public void setRoleId(String roleId){
        this.roleId = roleId;
        }

    public String getRoleId(){
        return   roleId;
        }

    public void setResourceId(String resourceId){
        this.resourceId = resourceId;
        }

    public String getResourceId(){
        return   resourceId;
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



    @Override
    public String syFields(){
        return "id,role_id,resource_id,info,gmt_create,creator_id,gmt_modified,editor_id,is_deleted";
        }
    @Override
    public String syTableName(){
        return "sy_sys_resource_role";
    }
    @Override
    public String syPrimaryKey(){
        return "id";
    }
}
