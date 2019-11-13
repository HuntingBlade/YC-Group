package com.shytong.modules.front.dao.impl;

import com.shytong.common.model.SyMap;
import com.shytong.core.database.BaseDao;
import com.shytong.modules.front.dao.IFrontDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: CL
 * @time: 2019/11/11 14:50
 */
@Repository
public class FrontDaoImpl implements IFrontDao {
    @Autowired
    private BaseDao baseDao;

    @Override
    public Object getSysConfig(String sysKey, String sysGroup) {
        SyMap syMap = new SyMap();
        syMap.put("sysKey", sysKey);
        syMap.put("sysGroup", sysGroup);
        return baseDao.selectOne("front.findSysConfigByKeyAndGroup", syMap);
    }
}
