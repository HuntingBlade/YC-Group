package com.shytong.modules.mgr.dao.impl;

import com.shytong.common.model.SyMap;
import com.shytong.core.database.BaseDao;
import com.shytong.modules.mgr.dao.IMgrDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Author: CL
 * @Date: 2019/11/29 21:27
 */
@Repository
public class MgrDaoImpl implements IMgrDao {

    @Autowired
    private BaseDao baseDao;

    @Override
    public Integer login(SyMap params) {
        return baseDao.selectOne("MgrMapper.login", params);
    }
}
