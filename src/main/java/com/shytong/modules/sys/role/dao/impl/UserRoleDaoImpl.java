

package com.shytong.modules.sys.role.dao.impl;
import com.shytong.common.dao.impl.CommDao;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.modules.sys.role.dao.IUserRoleDao;
import com.shytong.modules.sys.role.model.UserRoleDo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 
 * @Package com.shytong.modules.sys.role.dao.impl
 * @Description:
 * @date 2018-04-06 16:54:38
 */
@Repository("userRoleDao")
public class UserRoleDaoImpl extends CommDao<UserRoleDo>  implements IUserRoleDao {
    public UserRoleDaoImpl() {
        super("userRole.insert"
                , "userRole.updateSelective"
                ,"userRole.selective"
                ,"userRole.select"
                ,"userRole_ext.selectList"
                ,"userRole.delete");
    }

    @Override
    public int deleteByRoleId(String roleId) throws ApiBizException {



        return this.commUpdate("userRole_ext.deleteByRoleId",roleId);
    }

    @Override
    public int deleteByUserId(String userId) throws ApiBizException {

        return this.commUpdate("userRole_ext.deleteByUserId",userId);
    }

    @Override
    public List<UserRoleDo> getByUserId(String userId)  {



        return this.commlist("userRole_ext.selectUserRoleList", SyMap.map("userId",userId));
    }
}
