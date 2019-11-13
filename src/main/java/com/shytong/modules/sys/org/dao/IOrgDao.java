package com.shytong.modules.sys.org.dao;
import com.shytong.modules.sys.org.model.OrgDo;
import com.shytong.common.dao.ICommDao;

/**
 * @author 
 * @Description:
 * @date 2018-04-06 19:34:31
 */
public interface IOrgDao extends ICommDao<OrgDo>  {
    /**
    * 验证机构是否存在
    * @param  name
    * @param  parentId
    * @param  id
    * @return boolean
    **/
     boolean checkIsExists(String name, String parentId, String id) ;



}
