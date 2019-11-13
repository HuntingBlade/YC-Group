package com.shytong.modules.sys.user.controller;

import com.github.pagehelper.PageInfo;
import com.shytong.common.annotation.SyResource;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.common.web.BaseController;
import com.shytong.core.auth.Subject;
import com.shytong.core.auth.SyAuthUtil;
import com.shytong.core.database.BaseDao;
import com.shytong.core.database.Dao;
import com.shytong.core.util.SyValidationUtils;
import com.shytong.modules.sys.user.model.SysUserDo;
import com.shytong.modules.sys.user.model.SysUserDto;
import com.shytong.modules.sys.user.service.ISysUserService;
import com.shytong.sys.authorization.AuthSysUser;
import com.shytong.sys.authorization.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/sysUser", produces = "application/json;charset=UTF-8;", consumes = "application/json;")
public class SysUserController extends BaseController {


    @Resource(name = "sysUserService")
    private ISysUserService sysUserService;
    @Autowired
    private BaseDao baseDao;

    @SyResource(system = "SHYTONG")
    @RequestMapping(value = "/viewResources", consumes = "*", method = RequestMethod.GET)
    public String getViewResources() throws ApiBizException {


//        PageInfo pageInfo =new PageInfo();

        PageInfo pageInfo = sysUserService.getViewResources();
        return this.normalRespPage(pageInfo);
    }

    @SyResource(system = "SHYTONG", value = {"0716370412764a9690498281b5c18f88"})
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(HttpServletRequest servletRequest, @RequestBody SysUserDo sysUserDo) throws ApiBizException {

        SyValidationUtils.valid()
                .len(sysUserDo.getNickName(), 256, false, "昵称格式错误")
                .len(sysUserDo.getAccount(), 128, false, "账号格式错误")
                .len(sysUserDo.getPhone(), 11, false, "手机号格式错误")
                .len(sysUserDo.getAvatar(), 256, false, "头像格式错误");
        sysUserService.updateById(sysUserDo, "nickName,account,phone,avatar");
        return this.normalResp();
    }

    @SyResource(system = "SHYTONG", value = {"31702c99953e4859b4a5abce0ddf17ac"})
    @RequestMapping(value = "/detail", consumes = "*", method = RequestMethod.GET)
    public String detail(HttpServletRequest servletRequest, @RequestParam Map queryParamMap) throws ApiBizException {
        SyMap queryParams = new SyMap(queryParamMap);
        SyValidationUtils.valid()
                .len(queryParams.get("id"), 32, true, "用户id格式错误");
        SysUserDo sysUserDo = sysUserService.getById(queryParams.getString("id"));
        return this.normalResp(sysUserDo);
    }

    @RequestMapping(value = "/delete", consumes = "*", method = RequestMethod.GET)
    public String delete(HttpServletRequest servletRequest, @RequestParam Map queryParamMap) throws ApiBizException {
        SyMap queryParams = new SyMap(queryParamMap);
        SyValidationUtils.valid()
                .len(queryParams.get("id"), 32, true, "用户id格式错误");
        sysUserService.deleteById(queryParams.getString("id"));
        return this.normalResp();
    }

    @SyResource(system = "SHYTONG")
    @RequestMapping(value = "/changePwd", method = RequestMethod.POST)
    public String changePwd(@RequestBody SysUserDto sysUserDto) throws ApiBizException {
        SyValidationUtils.valid()
                .len(sysUserDto.getPwd(), 128, true, "密码格式错误")
                .len(sysUserDto.getOldPwd(), 128, false, "旧密码格式错误");
        if (sysUserDto.getId() == null) {
            Subject subject = SyAuthUtil.getSubject();
            String userID = subject.getAuthenticationInfo().getUserID();
            sysUserDto.setId(userID);
        }
        int n = sysUserService.changePwd(sysUserDto);
        return this.normalResp();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody SysUserDto sysUserDto) throws ApiBizException {
        SyValidationUtils.valid()
                .len(sysUserDto.getAccount(), 128, true, "账号格式错误")
                .len(sysUserDto.getPwd(), 128, true, "密码格式错误");

        sysUserService.login(sysUserDto);
        return this.normalResp();
    }

    @SyResource(system = "SHYTONG")
    @RequestMapping(value = "/personal/edit", method = RequestMethod.POST)
    public String updateUserInfo(@RequestBody SysUserDo sysUserDo, AuthSysUser user) throws ApiBizException {

        SyValidationUtils.valid()
                .len(sysUserDo.getNickName(), 256, false, "昵称格式错误")
                .len(sysUserDo.getAvatar(), 256, false, "头像格式错误");
        sysUserDo.setId(user.getUserId());
        sysUserService.editPersonal(sysUserDo, "nickName,avatar");
        return this.normalResp();
    }

    @SyResource(system = "SHYTONG")
    @RequestMapping(value = "/personal/detail", consumes = "*", method = RequestMethod.GET)
    public String getPersonalDetail(HttpServletRequest servletRequest, User user) throws ApiBizException {
        Object userInfo = sysUserService.getPersonal(user);
        return this.normalResp(userInfo);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(HttpServletRequest servletRequest, @RequestBody SysUserDo sysUserDo) throws ApiBizException {
        SyValidationUtils.valid()
                .len(sysUserDo.getNickName(), 256, false, "昵称格式错误")
                .len(sysUserDo.getAccount(), 128, true, "账号格式错误")
                .phone(sysUserDo.getPhone(), false, "手机号格式错误")
                .len(sysUserDo.getPwd(), 128, true, "密码格式错误")
                .len(sysUserDo.getAvatar(), 256, false, "头像格式错误");
        sysUserService.insert(sysUserDo);
        return this.normalResp();
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(HttpServletRequest servletRequest, @RequestBody SyMap params) throws ApiBizException {

        SyValidationUtils.valid()
                .isInt(params.get("pageNum"), true, "pageNum格式错误")
                .isInt(params.get("pageSize"), true, "pageSize格式错误");
        PageInfo pageInfo = sysUserService.listOfPage(params, params.getInteger("pageNum"), params.getInteger("pageSize"));
        return this.normalRespPage(pageInfo);
    }

    @RequestMapping(value = "/loginOut", consumes = "*", method = RequestMethod.GET)
    public String loginOut() throws ApiBizException {
        sysUserService.loginOut();
        return this.normalResp();
    }
}

