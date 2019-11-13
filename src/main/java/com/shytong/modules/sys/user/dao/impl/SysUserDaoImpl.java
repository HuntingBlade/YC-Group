

package com.shytong.modules.sys.user.dao.impl;

import com.shytong.common.dao.impl.CommDao;
import com.shytong.common.model.SyMap;
import com.shytong.modules.sys.user.dao.ISysUserDao;
import com.shytong.modules.sys.user.model.SysUserDo;
import org.springframework.stereotype.Repository;

/**
 * @author 
 * @Package com.shytong.modules.sys.user.dao.impl
 * @Description:
 * @date 2018-04-06 22:17:28
 */
@Repository("sysUserDao")
public class SysUserDaoImpl extends CommDao<SysUserDo>  implements ISysUserDao {
    public SysUserDaoImpl() {
        super("sysUser.insert"
                , "sysUser.updateSelective"
                ,"sysUser.selective"
                ,"sysUser.select"
                ,"sysUser_ext.selectList"
                ,"sysUser.delete");
        super.setCommDo(new SysUserDo());
    }
    @Override
    public SysUserDo getByAccount(String account,String type,String orgId ) {


        SysUserDo sysUserDo=this.findByXml("sysUser_ext.getUserByfields", SyMap.map("account",account).syPut("type",type).syPut("orgId",orgId));

        return sysUserDo;
    }
}
