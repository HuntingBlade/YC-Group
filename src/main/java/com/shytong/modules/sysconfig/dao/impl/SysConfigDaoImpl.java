package com.shytong.modules.sysconfig.dao.impl;

import com.shytong.common.model.SyMap;
import com.shytong.core.database.BaseDao;
import com.shytong.modules.sysconfig.dao.ISysConfigDao;
import com.shytong.modules.sysconfig.model.SysConfigDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: CL
 * @Date: 2019/11/11 17:32
 */
@Repository
public class SysConfigDaoImpl implements ISysConfigDao {

    @Autowired
    private BaseDao baseDao;

    @Override
    public Integer insert(SysConfigDo sysConfigDo) {
        return baseDao.insert("sysConfigMapper.insert", sysConfigDo);
    }

    @Override
    public Integer update(SysConfigDo sysConfigDo) {
        return baseDao.update("sysConfigMapper.update", sysConfigDo);
    }

    @Override
    public List getList(SyMap params) {
        return baseDao.selectList("sysConfigMapper.selectList", params);
    }

    @Override
    public Object getSysConfig(String sysKey, String sysGroup) {
        SyMap syMap = new SyMap();
        syMap.put("sysKey", sysKey);
        syMap.put("sysGroup", sysGroup);
        return baseDao.selectOne("sysConfigMapper.findSysConfigByKeyAndGroup", syMap);
    }

    @Override
    public List getSysConfigList(String sysKey, String sysGroup) {
        SyMap syMap = new SyMap();
        syMap.put("sysKey", sysKey);
        syMap.put("sysGroup", sysGroup);
        return baseDao.selectList("sysConfigMapper.findSysConfigByKeyAndGroup", syMap);
    }
}
