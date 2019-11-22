package com.shytong.modules.urlconfig.dao;

import com.github.pagehelper.PageInfo;
import com.shytong.common.model.SyMap;
import com.shytong.modules.urlconfig.model.UrlConfigDo;

import java.util.List;

/**
 * @Author: CL
 * @Date: 2019/11/21 12:26
 */
public interface IUrlConfigDao {

    /**
     * 添加链接配置项
     *
     * @param urlConfigDo
     * @return
     */
    Integer addUrlConfig(UrlConfigDo urlConfigDo);

    /**
     * 修改链接配置项
     *
     * @param urlConfigDo
     * @return
     */
    Integer updateUrlConfig(UrlConfigDo urlConfigDo);

    /**
     * 获取链接配置列表项
     *
     * @param pageNum
     * @param pageSize
     * @param params
     * @return
     */
    PageInfo getUrlConfigList(Integer pageNum, Integer pageSize, SyMap params);

    /**
     * 根据分组获取链接配置列表
     * @param urlGroup
     * @return
     */
    List getUrlConfigListByGroup(String urlGroup);
}
