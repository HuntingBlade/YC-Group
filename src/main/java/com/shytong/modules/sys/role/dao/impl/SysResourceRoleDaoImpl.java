

package com.shytong.modules.sys.role.dao.impl;

import com.shytong.common.dao.impl.CommDao;
import com.shytong.common.model.SyMap;
import com.shytong.modules.sys.role.dao.ISysResourceRoleDao;
import com.shytong.modules.sys.role.model.SysChannelRoleDo;
import com.shytong.modules.sys.role.model.SysResourceRoleDo;
import com.shytong.sys.authorization.CommResource;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 
 * @Package com.shytong.modules.sys.role.dao.impl
 * @Description:
 * @date 2018-04-08 12:09:22
 */
@Repository("sysResourceRoleDao")
public class SysResourceRoleDaoImpl extends CommDao<SysResourceRoleDo>  implements ISysResourceRoleDao {
    public SysResourceRoleDaoImpl() {
        super("sysResourceRole.insert"
                , "sysResourceRole.updateSelective"
                ,"sysResourceRole.selective"
                ,"sysResourceRole.select"
                ,"sysResourceRole_ext.selectList"
                ,"sysResourceRole.delete");
        super.setCommDo(new SysResourceRoleDo());
    }

    @Override
    @Cacheable(value = "roleResource")
    public List<CommResource> getRoleResource(String roleId) {


        return commlist("sysResourceRole_ext.selectRoleResources", SyMap.map("roleId",roleId));
    }

    @Override
    public List<Object> getRoleResourceDos(String roleId) {
        return commlist("sysResourceRole_ext.selectRoleResourcesToDo",SyMap.map("roleId",roleId));

    }

    @Override
    public List<Object> getRoleChannel(String roleId) {
        return commlist("sysResourceRole_ext.selectRoleChannelToDo",SyMap.map("roleId",roleId));

    }

    @Override
    public List<Object> getPersonRoleChannel(String roleId) {
        return commlist("sysResourceRole_ext.getPersonRoleChannel",SyMap.map("roleId",roleId));

    }



}
