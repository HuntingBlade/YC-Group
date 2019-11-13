package com.shytong.modules.user.service;
import com.shytong.common.dao.ICommService;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.modules.user.model.UserDo;
import com.shytong.modules.user.model.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 
 * @Description:
 * @date 2018-04-16 20:09:10
 */
public interface IUserService extends ICommService<UserDo>  {

    /**
    * 
    * @param  params
    * @param  httpServletResponse
    * @param  httpServletRequest
    * @return void
    **/
     void wxlogin(SyMap params, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) ;


    /**
     *注册
     * @param  userDto
     * @return void
     **/
     void reg(UserDto userDto) throws ApiBizException;

    /**
     * 修改密码
     * @param userDo
     */
    void updatePsw(UserDo userDo, String newPwd)throws ApiBizException;

}
