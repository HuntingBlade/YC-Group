

package com.shytong.modules.sys.resource.dao.impl;
import org.springframework.stereotype.Service;
import com.shytong.common.dao.impl.CommDao;
import com.shytong.modules.sys.resource.dao.ISysResourceDao;
import com.shytong.modules.sys.resource.model.SysResourceDo;
import org.springframework.stereotype.Repository;

/**
 * @author 
 * @Package com.shytong.modules.sys.resource.dao.impl
 * @Description:
 * @date 2018-04-03 11:57:11
 */
@Repository("sysResourceDao")
public class SysResourceDaoImpl extends CommDao<SysResourceDo>  implements ISysResourceDao {
    public SysResourceDaoImpl() {
        super("sysResource.insert"
                , "sysResource.updateSelective"
                ,"sysResource.selective"
                ,"sysResource.select"
                ,"sysResource_ext.selectList"
                ,"sysResource.delete");
    }
}
