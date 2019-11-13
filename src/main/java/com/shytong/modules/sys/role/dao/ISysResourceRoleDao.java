package com.shytong.modules.sys.role.dao;
import com.shytong.common.dao.ICommDao;
import com.shytong.modules.sys.role.model.SysChannelRoleDo;
import com.shytong.modules.sys.role.model.SysResourceRoleDo;
import com.shytong.sys.authorization.CommResource;

import java.util.List;

/**
 * @author 
 * @Description:
 * @date 2018-04-11 09:46:58
 */
public interface ISysResourceRoleDao extends ICommDao<SysResourceRoleDo>  {

    /**
    * 获取角色权限列表
    * @param  roleId
    * @return List<CommResource>
    **/
     List<CommResource> getRoleResource(String roleId) ;
    /**
    * 获取角色权限列表
    * @param  roleId
    * @return List<Object>
    **/
     List<Object> getRoleResourceDos(String roleId) ;

    /**
     * 获取角色栏目权限
     * @param roleId
     * @return
     */
     List<Object> getRoleChannel(String roleId) ;

     List<Object> getPersonRoleChannel(String roleId) ;

    /**
     * 增加角色
     * @param sysChannelRoleDo
     * @return
     */
//    Integer insertChannelRole(SysChannelRoleDo sysChannelRoleDo);

}
