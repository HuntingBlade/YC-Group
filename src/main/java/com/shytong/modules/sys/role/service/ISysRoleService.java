package com.shytong.modules.sys.role.service;
import com.github.pagehelper.PageInfo;
import com.shytong.common.dao.ICommService;
import com.shytong.common.exception.ApiBizException;
import com.shytong.modules.sys.role.model.RoleChannelDto;
import com.shytong.modules.sys.role.model.RoleResourceDto;
import com.shytong.modules.sys.role.model.RoleUserDto;
import com.shytong.modules.sys.role.model.SysRoleDo;

import java.util.List;
import java.util.Map;

/**
 * @author 
 * @Description:
 * @date 2018-04-11 09:47:50
 */
public interface ISysRoleService extends ICommService<SysRoleDo>  {

    /**
    * 角色添加用户
    * @param  roleUserDto
    * @return int
    **/
     int addUsers(RoleUserDto roleUserDto)  throws ApiBizException;
    /**
    * 用户添加角色
    * @param  roleUserDto
    * @return int
    **/
     int addRoles(RoleUserDto roleUserDto)  throws ApiBizException;
    /**
    * 角色添加资源
    * @param  roleResourceDto
    * @return int
    **/
     int addResources(RoleResourceDto roleResourceDto)  throws ApiBizException;

    /**
     * 角色栏目添加RoleChannelDto
     * @param roleResourceDto
     * @return
     * @throws ApiBizException
     */
     int addChannelResources(RoleChannelDto roleResourceDto)  throws ApiBizException;
    /**
    * 获取用户角色列表
    * @param  params
    * @param  pageNum
    * @param  pageSize
    * @return PageInfo
    **/
     PageInfo getUserRoleList(Map<String, Object> params, int pageNum, int pageSize)  throws ApiBizException;
    /**
    * 获取角色用户列表
    * @param  params
    * @param  pageNum
    * @param  pageSize
    * @return PageInfo
    **/
     PageInfo getRoleUserList(Map<String, Object> params, int pageNum, int pageSize)  throws ApiBizException;
    /**
    * 获取角色权限
    * @param  params
    * @return List<Object>
    **/
     List<Object> getRoleResources(Map<String, Object> params)  throws ApiBizException;

    /**
     * 获取角色栏目权限
     * @param params
     * @return
     * @throws ApiBizException
     */
     List<Object> getRoleChannel(Map<String, Object> params)  throws ApiBizException;


     List<Object> getRoleChannelPerson(Map<String, Object> params)  throws ApiBizException;



}
