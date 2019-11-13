
package com.shytong.modules.user.dao;
import com.shytong.common.dao.ICommDao;
import com.shytong.modules.user.model.UserDo;

/**
 * @author 
 * @Package com.shytong.modules.user.dao
 * @Description:
 * @date 2018-04-16 16:44:21
 */
public interface IUserDao extends ICommDao<UserDo> {

    UserDo findByIdentifier(String type, String sysIdentifier, String identifier);

    UserDo findByOpenId(String openId);
    UserDo findByUnionId(String unionId);
}
