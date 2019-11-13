package com.shytong.modules.front.dao;


/**
 * @Author: CL
 * @Date: 2019/11/11
 */
public interface IFrontDao {

    /**
     * 获取参数表的参数值
     * @param sysKey   参数 键
     * @param sysGroup 参数 分组
     * @return
     */
    Object getSysConfig(String sysKey, String sysGroup);
}
