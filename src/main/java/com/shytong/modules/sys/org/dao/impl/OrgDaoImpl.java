

package com.shytong.modules.sys.org.dao.impl;

import com.shytong.common.dao.impl.CommDao;
import com.shytong.common.model.SyMap;
import com.shytong.modules.sys.org.dao.IOrgDao;
import com.shytong.modules.sys.org.model.OrgDo;
import org.springframework.stereotype.Repository;

/**
 * @author 
 * @Package com.shytong.modules.sys.org.dao.impl
 * @Description:
 * @date 2018-04-06 20:49:00
 */
@Repository("orgDao")
public class OrgDaoImpl extends CommDao<OrgDo>  implements IOrgDao {



    public OrgDaoImpl() {
        super("org.insert"
                , "org.updateSelective"
                ,"org.selective"
                ,"org.select"
                ,"org_ext.selectList"
                ,"org.delete");
        super.setCommDo(new OrgDo());

    }
    @Override
    public boolean checkIsExists(String name, String parentId, String id) {

        return  this.isExists("org_ext.valid",
                SyMap.map("name",name)
                        .syPut("parentId",parentId)
                        .syPut("id",id));

    }
}
