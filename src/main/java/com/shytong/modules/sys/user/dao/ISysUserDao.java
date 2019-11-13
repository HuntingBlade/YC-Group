
package com.shytong.modules.sys.user.dao;
import com.shytong.common.dao.ICommDao;
import com.shytong.modules.sys.user.model.SysUserDo;

/**
 * @author 
 * @Package com.shytong.modules.sys.user.dao
 * @Description:
 * @date 2018-04-02 21:31:01
 */
public interface ISysUserDao extends ICommDao<SysUserDo> {

    public SysUserDo getByAccount(String account, String type, String orgId);

}
