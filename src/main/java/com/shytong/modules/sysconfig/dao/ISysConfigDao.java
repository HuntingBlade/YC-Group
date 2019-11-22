package com.shytong.modules.sysconfig.dao;

import com.shytong.common.model.SyMap;
import com.shytong.modules.sysconfig.model.SysConfigDo;

import java.util.List;

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
     * @param sysKey
     * @param sysGroup
     * @return
     */
    List getSysConfigList(String sysKey, String sysGroup);
}
