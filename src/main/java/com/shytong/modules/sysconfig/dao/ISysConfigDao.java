package com.shytong.modules.sysconfig.dao;

import com.github.pagehelper.PageInfo;
import com.shytong.common.model.SyMap;
import com.shytong.modules.sysconfig.model.SysConfigDo;

import java.util.List;
import java.util.Map;

/**
 * @Author: CL
 * @Date: 2019/11/11 17:28
 */
public interface ISysConfigDao {

    /**
     * 添加系统配置
     *
     * @param sysConfigDo
     * @return
     * @author CL
     */
    Integer insert(SysConfigDo sysConfigDo);

    /**
     * 更新系统配置
     *
     * @param sysConfigDo
     * @return
     */
    Integer update(SysConfigDo sysConfigDo);

    /**
     * 获取参数列表
     *
     * @param params
     * @return
     */
    List getList(SyMap params);

    /**
     * 获取参数表的参数值
     *
     * @param sysKey
     * @param sysGroup
     * @return
     */
    Object getSysConfig(String sysKey, String sysGroup);

    /**
     * 根据ID获取参数表的参数值
     *
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo getSysConfigList(SyMap params, Integer pageNum, Integer pageSize);

    /**
     * 批量删除配置
     * @param array
     * @return
     */
    Integer delSysConfigsById(String[] array);

    /**
     * 根据ID获取参数表的参数值
     *
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo getSysConfigAllList(SyMap params, Integer pageNum, Integer pageSize);
}
