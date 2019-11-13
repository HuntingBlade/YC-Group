package com.shytong.modules.sys.user.service;
import com.shytong.modules.sys.user.model.SysUserDto;
import com.shytong.common.exception.ApiBizException;
import com.shytong.sys.authorization.User;
import com.shytong.common.dao.ICommService;
import com.github.pagehelper.PageInfo;
import com.shytong.modules.sys.user.model.SysUserDo;

/**
 * @author 
 * @Description:
 * @date 2018-05-23 14:03:57
 */
public interface ISysUserService extends ICommService<SysUserDo>  {

    /**
    * 修改密码
    * @param  sysUserDto
    * @return int
    **/
     int changePwd(SysUserDto sysUserDto)  throws ApiBizException;
    /**
    * 登陆
    * @param  sysUserDto
    * @return int
    **/
     int login(SysUserDto sysUserDto)  throws ApiBizException;
    /**
    * 获取个人信息
    * @param  user
    * @return Object
    **/
     Object getPersonal(User user)  throws ApiBizException;
    /**
    * 登出
    * @return Integer
    **/
     Integer loginOut()  throws ApiBizException;
    /**
    * 获取用户视图资源
    * @return PageInfo
    **/
     PageInfo getViewResources()  throws ApiBizException;
    /**
    * 修改个人细腻些
    * @param  sysUserDo
    * @param  fields
    * @return int
    **/
     int editPersonal(SysUserDo sysUserDo, String fields)  throws ApiBizException;



}
