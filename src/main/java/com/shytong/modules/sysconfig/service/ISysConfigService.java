package com.shytong.modules.sysconfig.service;

import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.modules.sysconfig.model.SysConfigDo;

import java.util.List;

/**
 * @Author: CL
 * @Date: 2019/11/11 17:22
 */
public interface ISysConfigService {

    /**
     * 添加系统配置
     *
     * @param sysConfigDo
     * @return
     * @throws ApiBizException
     * @author CL
     */
    Integer insert(SysConfigDo sysConfigDo) throws ApiBizException;

    /**
     * 删除系统配置
     * @param map
     * @return
     */
    Integer deleted(SyMap map);

    /**
     * 修改系统配置
     * @param sysConfigDo
     * @return
     */
    Integer update(SysConfigDo sysConfigDo);

    /**
     * 获取参数列表
     * @param params
     * @return
     */
    List getList(SyMap params);
}
