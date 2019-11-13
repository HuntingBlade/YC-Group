

package com.shytong.modules.sys.role.dao.impl;

import com.shytong.common.dao.impl.CommDao;
import com.shytong.common.model.SyMap;
import com.shytong.core.database.BaseDao;
import com.shytong.modules.sys.role.dao.ISysChannelRoleDao;
import com.shytong.modules.sys.role.dao.ISysResourceRoleDao;
import com.shytong.modules.sys.role.model.RoleChannelDto;
import com.shytong.modules.sys.role.model.SysChannelRoleDo;
import com.shytong.modules.sys.role.model.SysResourceRoleDo;
import com.shytong.sys.authorization.CommResource;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 
 * @Package com.shytong.modules.sys.role.dao.impl
 * @Description:
 * @date 2018-04-08 12:09:22
 */
@Repository("sysChannelRoleDao")
public class SysChannelRoleDaoImpl extends CommDao<SysChannelRoleDo>  implements ISysChannelRoleDao {
    @Resource
    private BaseDao baseDao;

    public SysChannelRoleDaoImpl() {
        super("sysChannelRole.insert"
                , "sysChannelRole.updateSelective"
                ,"sysChannelRole.selective"
                ,"sysChannelRole.select"
                ,"sysChannelRole.selectList"
                ,"sysChannelRole.delete");
        super.setCommDo(new SysChannelRoleDo());
    }

    @Override
    public int update(RoleChannelDto roleChannelDto) {
        return baseDao.update("sysChannelRole.delete",roleChannelDto);
    }

    @Override
    public int insertChannel(SysChannelRoleDo sysChannelRoleDo) {
        return baseDao.insert("sysChannelRole.insert",sysChannelRoleDo);
    }


}
