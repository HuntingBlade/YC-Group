package com.shytong.modules.sys.role.model;
import com.shytong.common.model.CommSqlDto;
import java.io.Serializable;
import java.util.List;
import com.shytong.modules.sys.resource.model.SysResourceDo;

/**
 * @author 
 * @Package com.shytong.modules.sys.role.model
 * @Description:
 * @date 2018-04-08 12:15:29
 */
public class RoleResourceDto extends CommSqlDto  implements Serializable {
    private static final long serialVersionUID = 1L;
   /**
    *角色id
     **/
    private String roleId;

   /**
    *资源
     **/
    private List<SysResourceDo> resources;


    public void setRoleId(String roleId){
        this.roleId = roleId;
        }

    public String getRoleId(){
        return   roleId;
        }

    public void setResources(List<SysResourceDo> resources){
        this.resources = resources;
        }

    public List<SysResourceDo> getResources(){
        return   resources;
        }


}
