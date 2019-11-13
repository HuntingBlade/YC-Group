package com.shytong.modules.sys.role.dao;
import com.shytong.common.dao.ICommDao;
import com.shytong.modules.sys.role.model.RoleChannelDto;
import com.shytong.modules.sys.role.model.SysChannelRoleDo;
import com.shytong.modules.sys.role.model.SysResourceRoleDo;
import com.shytong.sys.authorization.CommResource;

import java.util.List;

/**
 * @author 
 * @Description:
 * @date 2018-04-11 09:46:58
 */
public interface ISysChannelRoleDao extends ICommDao<SysChannelRoleDo>  {


    int update(RoleChannelDto roleChannelDto);

    int insertChannel(SysChannelRoleDo sysChannelRoleDo);
}
