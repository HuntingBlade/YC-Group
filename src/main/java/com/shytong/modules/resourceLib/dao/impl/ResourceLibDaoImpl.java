package com.shytong.modules.resourceLib.dao.impl;

import com.github.pagehelper.PageInfo;
import com.shytong.common.model.SyMap;
import com.shytong.core.database.BaseDao;
import com.shytong.modules.resourceLib.dao.ResourceLibDao;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Auther: HC
 * @Date: 2018/10/15 15:20
 * @Description:
 */
@Repository("resourceLibDao")
public class ResourceLibDaoImpl  implements ResourceLibDao  {

    @Resource
    private BaseDao baseDao;

    @Override
    public PageInfo getList(SyMap params, int pageNum, int pageSize) {
        return baseDao.commlistOfPage("resourceLib.selectList",pageNum,pageSize,params);
    }

    @Override
    public Object findByResId(Map queryParamMap) {
        return baseDao.selectOne("resourceLib.selectList",queryParamMap);
    }
}
