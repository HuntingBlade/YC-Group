package com.shytong.modules.user.controller;

import com.shytong.common.annotation.SyResource;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.common.web.BaseController;
import com.shytong.constant.SysTemCodeConstant;
import com.shytong.constant.VerifyCodeConsts;
import com.shytong.core.auth.AuthenticationToken;
import com.shytong.core.auth.Subject;
import com.shytong.core.auth.SyAuthUtil;
import com.shytong.core.database.BaseDao;
import com.shytong.core.util.SyBeanUtil;
import com.shytong.core.util.SyIdUtils;
import com.shytong.core.util.SyStringUtils;
import com.shytong.core.util.SyValidationUtils;
import com.shytong.modules.user.model.UserDo;
import com.shytong.modules.user.model.UserDto;
import com.shytong.modules.user.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/user", produces = "application/json;charset=UTF-8;", consumes = "application/json;")
public class UserController extends BaseController {


    @Resource(name = "userService")
    private IUserService userService;
    @Autowired
    private BaseDao baseDao;

    /**
     * 信息登录
     * @param queryParamMap
     * @param servletRequest
     * @param httpServletResponse
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/wxLogin", consumes = "*", method = RequestMethod.GET)
    public String wxLogin(@RequestParam Map queryParamMap, HttpServletRequest servletRequest, HttpServletResponse httpServletResponse) throws ApiBizException {
        SyMap queryParams = new SyMap(queryParamMap);
        userService.wxlogin(queryParams, httpServletResponse, servletRequest);
        return this.normalResp();
    }

    /**
     * 登录
     *
     * @param userDto
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody UserDto userDto) throws ApiBizException {
        SyValidationUtils.valid()
                .checkBlank(userDto.getAccount(), true, "用户名")
                .checkBlank(userDto.getPwd(), true, "密码不能为空");
        Subject s = SyAuthUtil.getSubject(SysTemCodeConstant.SYSTEM_USER);
        AuthenticationToken authenticationToken = new AuthenticationToken(userDto.getAccount(), userDto.getPwd(), "login", "");
        if (s.isAuthented()) {
            s.loginOut();
            s = SyAuthUtil.getSubject(SysTemCodeConstant.SYSTEM_USER);
        }
        s.login(authenticationToken);
        return this.normalResp();
    }

    /**
     * 注销
     *
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/loginOut", method = RequestMethod.GET, consumes = "*")
    @SyResource(system = "USER")
    public String loginOut() throws ApiBizException {
        Subject s = SyAuthUtil.getSubject();
        s.loginOut();
        return this.normalResp();
    }

    /**
     * 注册
     *
     * @param userDto
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String reg(@RequestBody UserDto userDto) throws ApiBizException {
        SyValidationUtils.valid().checkBlank(userDto.getAccount(), true, "用户名")
                .checkBlank(userDto.getPwd(), true, "密码不能为空");
        userService.reg(userDto);
        return this.normalResp();
    }

    /**
     * 获取用户信息
     *
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET, consumes = "*")
    @SyResource(system = "USER")
    public String getUserInfo() throws ApiBizException {
        Subject s = SyAuthUtil.getSubject();
        UserDo u = (UserDo) s.getAuthenticationInfo().getUserInfo();
        return this.normalResp(u);
    }

    /**
     * 修改密码
     *
     * @param
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST, consumes = "*")
    @SyResource(system = "USER")
    public String updatePwd(@RequestBody SyMap syMap) throws ApiBizException {
        Subject s = SyAuthUtil.getSubject();
        String newPwd = syMap.getString("newPwd");
        String oldPwd = syMap.getString("oldPwd");
        UserDo u = (UserDo) s.getAuthenticationInfo().getUserInfo();
        if (!(u.getPwd().equals(oldPwd))) {
            throw new ApiBizException(-1, "原密码错误");
        }
        userService.updatePsw(u, newPwd);
        return this.normalResp();
    }


}

