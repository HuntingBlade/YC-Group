package com.shytong.modules.mgr.dao.impl;

import com.shytong.common.model.SyMap;
import com.shytong.core.database.BaseDao;
import com.shytong.modules.mgr.dao.IMgrDao;
import com.shytong.modules.mgr.model.MgrDo;
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

    @Override
    public Integer update(MgrDo mgrDo) {
        return baseDao.update("MgrMapper.update", mgrDo);
    }

    @Override
    public MgrDo isAccountInfoOf(String account) {
        return baseDao.selectOne("MgrMapper.selectMgrDo", account);
    }
}
