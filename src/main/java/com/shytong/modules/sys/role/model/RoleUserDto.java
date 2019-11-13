package com.shytong.modules.sys.role.model;
import com.shytong.common.model.CommSqlDto;
import java.io.Serializable;
import java.util.List;

/**
 * @author 
 * @Package com.shytong.modules.sys.role.model
 * @Description:
 * @date 2018-04-06 17:34:33
 */
public class RoleUserDto extends CommSqlDto  implements Serializable {
    private static final long serialVersionUID = 1L;
   /**
    *用户id列表
     **/
    private List<String> userIds;

   /**
    *角色ID
     **/
    private String roleId;

   /**
    *用户ID
     **/
    private String userId;

   /**
    *角色ID列表
     **/
    private List<String> roleIds;

   /**
    *id
     **/
    private String id;


    public void setUserIds(List<String> userIds){
        this.userIds = userIds;
        }

    public List<String> getUserIds(){
        return   userIds;
        }

    public void setRoleId(String roleId){
        this.roleId = roleId;
        }

    public String getRoleId(){
        return   roleId;
        }

    public void setUserId(String userId){
        this.userId = userId;
        }

    public String getUserId(){
        return   userId;
        }

    public void setRoleIds(List<String> roleIds){
        this.roleIds = roleIds;
        }

    public List<String> getRoleIds(){
        return   roleIds;
        }

    public void setId(String id){
        this.id = id;
        }

    public String getId(){
        return   id;
        }



}
