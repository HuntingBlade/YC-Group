package com.shytong.modules.urlconfig.dao.impl;

import com.github.pagehelper.PageInfo;
import com.shytong.common.model.SyMap;
import com.shytong.core.database.BaseDao;
import com.shytong.modules.urlconfig.dao.IUrlConfigDao;
import com.shytong.modules.urlconfig.model.UrlConfigDo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: CL
 * @Date: 2019/11/21 13:39
 */
@Repository
public class UrlConfigDaoImpl implements IUrlConfigDao {

    @Autowired
    private BaseDao baseDao;


    @Override
    public Integer addUrlConfig(UrlConfigDo urlConfigDo) {
        return baseDao.insert("UrlConfigMapper.addUrlConfig", urlConfigDo);
    }

    @Override
    public Integer updateUrlConfig(UrlConfigDo urlConfigDo) {
        return baseDao.update("UrlConfigMapper.updateUrlConfig", urlConfigDo);
    }

    @Override
    public PageInfo getUrlConfigList(Integer pageNum, Integer pageSize, SyMap params) {
        return baseDao.commlistOfPage("UrlConfigMapper.getUrlConfigList", pageNum, pageSize, params);
    }

    @Override
    public List getUrlConfigListByGroup(String urlGroup) {
        return baseDao.selectList("UrlConfigMapper.getUrlConfigListByGroup", urlGroup);
    }
}
