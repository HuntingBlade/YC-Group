package com.shytong.modules.resourceLib.dao;

import com.github.pagehelper.PageInfo;
import com.shytong.common.model.SyMap;


import java.util.Map;

/**
 * @Auther: HC
 * @Date: 2018/10/15 15:20
 * @Description:
 */
public interface ResourceLibDao{

    /**
     * 资源库列表
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo getList(SyMap params, int pageNum, int pageSize);

    /**
     * 获取详情
     * @param
     * @return
     */
    Object findByResId(Map queryParamMap);

}
