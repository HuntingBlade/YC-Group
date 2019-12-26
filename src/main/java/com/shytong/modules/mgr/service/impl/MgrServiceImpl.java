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
    public String updatePassWord(Map map, HttpSession session) {
        String account = (String) map.get("account");
        String pwd = (String) map.get("pwd");
        String newPwd = (String) map.get("newPwd");
        String rePwd = (String) map.get("rePwd");
        // 判断新密码是否与确认密码一致
        if (!newPwd.equals(rePwd)) {
            return ResultCode.USER_NEW_PWD_NOT_SAME_ERROR;
        }
        // 判断账号是否存在
        MgrDo tempMgrDo = mgrDao.isAccountInfoOf(account);
        if (tempMgrDo == null) {
            return ResultCode.USER_NOT_EXIST;
        }
        // 原账号密码是否正确
        SyMap params = new SyMap();
        params.put("username", account);
        params.put("password", pwd);
        Integer tempStatus = mgrDao.login(params);
        if (tempStatus <= 0) {
            // 账号密码错误
            return ResultCode.USER_PASSWORD_ERROR;
        }
        MgrDo mgrDo = new MgrDo();
        mgrDo.setAccount(account);
        mgrDo.setPwd(rePwd);
        Integer result = mgrDao.update(mgrDo);
        if (result < 0) {
            throw new RuntimeException();
        }
        session.removeAttribute("sessionId");
        return ResultCode.SUCCESS;
    }
}