package com.shytong.modules.sys.role.dao;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.dao.ICommDao;
import com.shytong.modules.sys.role.model.SysRoleDo;

/**
 * @author 
 * @Description:
 * @date 2018-04-10 14:08:38
 */
public interface ISysRoleDao extends ICommDao<SysRoleDo>  {

    /**
    * 验证机构下角色名是否存在
    * @param  name
    * @param  orgId
    * @param  id
    * @return boolean
    **/
     boolean checkExistsByName(String name, String orgId, String id)  throws ApiBizException;



}
