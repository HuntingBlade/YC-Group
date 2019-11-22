package com.shytong.modules.urlconfig.service.impl;

import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizRunException;
import com.shytong.common.model.SyMap;
import com.shytong.modules.urlconfig.dao.IUrlConfigDao;
import com.shytong.modules.urlconfig.model.UrlConfigDo;
import com.shytong.modules.urlconfig.service.IUrlConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: CL
 * @Date: 2019/11/21 12:25
 */
@Service
public class UrlConfigServiceImpl implements IUrlConfigService {

    @Autowired
    private IUrlConfigDao urlConfigDao;

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public Integer addUrlConfig(UrlConfigDo urlConfigDo) {
        if (urlConfigDo == null) {
            throw new ApiBizRunException(-1, "参数错误");
        }
        Integer result = urlConfigDao.addUrlConfig(urlConfigDo);
        if (result <= 0) {
            throw new RuntimeException();
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public Integer deletedUrlConfig(Integer urlId) {
        if (urlId <= 0) {
            throw new ApiBizRunException(-1, "参数错误");
        }
        UrlConfigDo urlConfigDo = new UrlConfigDo();
        urlConfigDo.setUrlId(urlId);
        urlConfigDo.setIsDeleted(1);
        Integer result = urlConfigDao.updateUrlConfig(urlConfigDo);
        if (result <= 0) {
            throw new RuntimeException();
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public Integer updateUrlConfig(UrlConfigDo urlConfigDo) {
        if (urlConfigDo == null) {
            throw new ApiBizRunException(-1, "参数错误");
        }
        Integer result = urlConfigDao.updateUrlConfig(urlConfigDo);
        if (result <= 0) {
            throw new RuntimeException();
        }
        return result;
    }

    @Override
    public PageInfo getUrlConfigList(Integer pageNum, Integer pageSize, SyMap params) {
        if (pageNum <= 0 || pageSize <= 0) {
            throw new ApiBizRunException(-1, "参数错误");
        }
        return urlConfigDao.getUrlConfigList(pageNum, pageSize, params);
    }
}
