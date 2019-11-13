package com.shytong.modules.user.service.impl;

import com.shytong.common.exception.ApiBizException;
import com.shytong.modules.user.dao.IUserAuthDao;
import com.shytong.modules.user.dao.IUserDao;
import com.shytong.modules.user.model.UserDo;
import com.shytong.modules.user.service.IUserBizService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author sytong
 * @Package com.shytong.modules.user.service.impl
 * @Description:
 * @date 2018-04-1711:16
 */

@Service("userBizService")
public class UserBizServiceImpl  implements IUserBizService{

    @Resource(name ="userDao")
    private IUserDao userDao;
    @Resource(name ="userAuthDao")
    private IUserAuthDao userAuthDao;
    @Override
    @Transactional(rollbackFor = { Exception.class,RuntimeException.class })
    public UserDo addUserByunionId(UserDo userDo) throws ApiBizException {

        UserDo oldUserDo= userDao.findByUnionId(userDo.getUnionId());

        if(oldUserDo!=null){
            return  oldUserDo;
        }
        userDao.insert(userDo);
        return userDo;
    }
}
