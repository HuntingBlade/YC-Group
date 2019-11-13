

package com.shytong.modules.sys.role.dao.impl;
import com.shytong.common.dao.impl.CommDao;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.modules.sys.role.dao.ISysRoleDao;
import com.shytong.modules.sys.role.model.SysRoleDo;
import org.springframework.stereotype.Repository;

/**
 * @author 
 * @Package com.shytong.modules.sys.role.dao.impl
 * @Description:
 * @date 2018-04-03 10:28:13
 */
@Repository("sysRoleDao")
public class SysRoleDaoImpl extends CommDao<SysRoleDo>  implements ISysRoleDao {
    public SysRoleDaoImpl() {
        super("sysRole.insert"
                , "sysRole.updateSelective"
                ,"sysRole.selective"
                ,"sysRole.select"
                ,"sysRole_ext.selectList"
                ,"sysRole.delete");
    }

    @Override
    public boolean checkExistsByName(String name,String orgId,String id) throws ApiBizException {


        return  this.isExists("sysRole_ext.valid",
                SyMap.map("name",name)
                        .syPut("orgId",orgId)
                        .syPut("id",id));



    }
}
