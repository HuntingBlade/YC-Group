package com.shytong.modules.mgr.service;

import com.shytong.common.model.SyMap;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author: CL
 * @Date: 2019/11/29 22:04
 */
public interface IMgrService {

    /**
     * 用户登录
     *
     * @param params
     * @param session
     * @return
     */
    String login(SyMap params, HttpSession session);

    /**
     * 修改密码
     * @param map
     * @return
     */
    String updatePassWord(Map map);
}
