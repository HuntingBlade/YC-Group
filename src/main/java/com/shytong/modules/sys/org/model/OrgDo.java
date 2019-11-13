package com.shytong.modules.sys.org.model;
import com.shytong.common.model.CommSqlDto;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * @Package com.shytong.modules.sys.org.model
 * @Description:
 * @date 2018-04-06 22:16:02
 */
public class OrgDo extends CommSqlDto  implements Serializable {
    private static final long serialVersionUID = 1L;
   /**
    *id
     **/
    private String id;

   /**
    *名称
     **/
    private String name;

   /**
    *机构编码
     **/
    private String code;

   /**
    *父id
     **/
    private String parentId;

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

    public void setName(String name){
        this.name = name;
        }

    public String getName(){
        return   name;
        }

    public void setCode(String code){
        this.code = code;
        }

    public String getCode(){
        return   code;
        }

    public void setParentId(String parentId){
        this.parentId = parentId;
        }

    public String getParentId(){
        return   parentId;
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
        return "id,name,code,parent_id,gmt_create,creator_id,gmt_modified,editor_id,is_deleted";
        }
    @Override
    public String syTableName(){
        return "sy_sys_org";
    }
    @Override
    public String syPrimaryKey(){
        return "id";
    }
}
