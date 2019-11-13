package com.shytong.modules.user.service;

import com.shytong.common.exception.ApiBizException;
import com.shytong.modules.user.model.UserDo;

/**
 * @author 
 * @Description:
 * @date 2018-04-16 20:09:10
 */
public interface IUserBizService   {

    UserDo addUserByunionId(UserDo userDo) throws ApiBizException;

}
