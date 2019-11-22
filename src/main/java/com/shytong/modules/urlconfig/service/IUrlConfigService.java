package com.shytong.modules.urlconfig.service;

import com.github.pagehelper.PageInfo;
import com.shytong.common.model.SyMap;
import com.shytong.modules.urlconfig.model.UrlConfigDo;

/**
 * @Author: CL
 * @Date: 2019/11/21 12:19
 */
public interface IUrlConfigService {

    /**
     * 添加链接配置项
     * @param urlConfigDo
     * @return
     */
    Integer addUrlConfig(UrlConfigDo urlConfigDo);

    /**
     * 删除链接配置项
     * @param urlId
     * @return
     */
    Integer deletedUrlConfig(Integer urlId);

    /**
     * 修改链接配置项
     * @param urlConfigDo
     * @return
     */
    Integer updateUrlConfig(UrlConfigDo urlConfigDo);


    /**
     * 获取链接配置列表项
     * @param pageNum
     * @param pageSize
     * @param params
     * @return
     */
    PageInfo getUrlConfigList(Integer pageNum, Integer pageSize, SyMap params);


}
