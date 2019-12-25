package com.shytong.modules.mgr.service.impl;

import com.shytong.common.model.SyMap;
import com.shytong.common.resultcode.ResultCode;
import com.shytong.modules.mgr.dao.IMgrDao;
import com.shytong.modules.mgr.model.MgrDo;
import com.shytong.modules.mgr.service.IMgrService;
import com.shytong.modules.validatecode.commcode.service.IValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author: CL
 * @Date: 2019/11/29 22:09
 */
@Service
public class MgrServiceImpl implements IMgrService {

    @Autowired
    private IMgrDao mgrDao;
    @Autowired
    private IValidateCodeService validateCodeService;

    @Override
    public String login(SyMap params, HttpSession session) {
        if (session == null) {
            throw new RuntimeException("session格式错误");
        }
        // 验证码判断
        boolean code = validateCodeService.getCheckCaptcha(params.getString("code"), session);
        if (!code) {
            return ResultCode.VALIDATE_CODE_ERROR;
        }
        Integer result = mgrDao.login(params);
        if (result != 1) {
            return ResultCode.USER_ACCOUNT_ERROR;
        }
        session.setAttribute("sessionId", params.getString("username") + "-" + params.get("password"));
        return ResultCode.SUCCESS;
    }

    @Override
    public String updatePassWord(Map map) {
        String account = (String) map.get("account");
        String pwd = (String) map.get("pwd");
        String newPwd = (String) map.get("newPwd");
        String rePwd = (String) map.get("rePwd");
        if (!newPwd.equals(rePwd)) {
            return ResultCode.PARAMETER_ERROR;
        }
        MgrDo mgrDo = new MgrDo();
        mgrDo.setAccount(account);
        mgrDo.setPwd(pwd);
        Integer result = mgrDao.update(mgrDo);
        if (result < 0) {
            throw new RuntimeException();
        }
        return ResultCode.SUCCESS;
    }
}