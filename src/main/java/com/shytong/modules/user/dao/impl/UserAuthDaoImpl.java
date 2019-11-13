

package com.shytong.modules.user.dao.impl;
import org.springframework.stereotype.Service;
import com.shytong.common.dao.impl.CommDao;
import com.shytong.modules.user.dao.IUserAuthDao;
import com.shytong.modules.user.model.UserAuthDo;
import org.springframework.stereotype.Repository;

/**
 * @author 
 * @Package com.shytong.modules.user.dao.impl
 * @Description:
 * @date 2018-04-17 11:15:09
 */
@Repository("userAuthDao")
public class UserAuthDaoImpl extends CommDao<UserAuthDo>  implements IUserAuthDao {
    public UserAuthDaoImpl() {
        super("userAuth.insert"
                , "userAuth.updateSelective"
                ,"userAuth.selective"
                ,"userAuth.select"
                ,"userAuth_ext.selectList"
                ,"userAuth.delete");
        super.setCommDo(new UserAuthDo());
    }
}
