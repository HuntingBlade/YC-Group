package com.shytong.modules.validatecode.commcode.service;

import javax.servlet.http.HttpSession;

/**
 * @Description: 验证码业务层类
 * @Author: CL
 * @Date: 2019/11/29 21:57
 */
public interface IValidateCodeService {

    /**
     * 验证码校验
     * @param code
     * @param session
     * @return
     */
    boolean getCheckCaptcha(String code, HttpSession session);

}
