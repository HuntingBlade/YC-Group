package com.shytong.modules.validatecode.commcode.service.impl;

import com.shytong.modules.validatecode.commcode.service.IValidateCodeService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @Description: 验证码业务实现类
 * @Author: CL
 * @Date: 2019/11/29 22:01
 */
@Service
public class ValidateCodeServiceImpl implements IValidateCodeService {

    @Override
    public boolean getCheckCaptcha(String code, HttpSession session) {
        try {
            // 不区分大小写进行验证码校验
            String sessionCode = String.valueOf(session.getAttribute("JCCODE")).toLowerCase();
            String receivedCode = code.toLowerCase();
            return !sessionCode.equals("") && !receivedCode.equals("") && sessionCode.equals(receivedCode);
        } catch (Exception e) {
            return false;
        }
    }
}
