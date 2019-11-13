package com.shytong.modules.sys.role.dao;
import java.util.List;
import com.shytong.common.exception.ApiBizException;
import com.shytong.modules.sys.role.model.UserRoleDo;
import com.shytong.common.dao.ICommDao;

/**
 * @author 
 * @Description:
 * @date 2018-04-10 13:44:23
 */
public interface IUserRoleDao extends ICommDao<UserRoleDo>  {

    /**
    * 删除角色下的用户
    * @param  roleId
    * @return int
    **/
     int deleteByRoleId(String roleId)  throws ApiBizException;
    /**
    * 删除用户角色
    * @param  userId
    * @return int
    **/
     int deleteByUserId(String userId)  throws ApiBizException;
    /**
    * 获取用户角色列表
    * @param  userId
    * @return List<UserRoleDo>
    **/
     List<UserRoleDo> getByUserId(String userId) ;



}
