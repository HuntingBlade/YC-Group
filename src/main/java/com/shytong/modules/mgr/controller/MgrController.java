package com.shytong.modules.mgr.controller;

import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.common.web.BaseController;
import com.shytong.core.util.SyValidationUtils;
import com.shytong.modules.mgr.service.IMgrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Description: 管理员控制类
 * @Author: CL
 * @Date: 2019/11/29 22:26
 */
@RequestMapping(value = "/mgr", produces = "application/json;charset=utf-8", consumes = "application/json")
@RestController
public class MgrController extends BaseController {

    @Autowired
    private IMgrService mgrService;

    /**
     * 管理后台登录
     *
     * @param servletRequest
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST, consumes = "*")
    public String adminLogin(HttpServletRequest servletRequest, HttpSession session, ModelMap model, @RequestBody Map map) throws ApiBizException {
        // 参数校验
        SyMap params = new SyMap(map);
        String username = params.getString("username");
        String password = params.getString("password");
        String code = params.getString("code");
        SyValidationUtils.valid()
                .len(username, 18, true, "用户名格式错误")
                .len(password, 18, true, "密码格式错误")
                .len(code, 4, true, "验证码格式错误");
        String res = mgrService.login(params, session);
        return this.normalResp(res);
    }
}
