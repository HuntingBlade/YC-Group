package com.shytong.modules.sys.role.controller;

import com.shytong.common.model.SyMap;
import com.shytong.core.auth.Subject;
import com.shytong.core.auth.SyAuthUtil;
import com.shytong.core.util.SyValidationUtils;
import com.shytong.core.database.BaseDao;
import com.shytong.modules.sys.role.model.*;
import com.shytong.modules.sys.user.model.SysUserDo;
import com.shytong.modules.user.model.UserDo;
import com.shytong.sys.authorization.AuthSysUser;
import com.shytong.sys.authorization.User;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

import com.shytong.common.exception.ApiBizException;

import java.util.Map;

import com.shytong.common.web.BaseController;
import org.springframework.web.bind.annotation.*;
import com.shytong.modules.sys.role.service.ISysRoleService;
import com.shytong.common.annotation.SyResource;

@RestController
@RequestMapping(value = "/api/sysRole", produces = "application/json;charset=UTF-8;", consumes = "application/json;")
public class SysRoleController extends BaseController {


    @Resource(name = "sysRoleService")
    private ISysRoleService sysRoleService;
    @Autowired
    private BaseDao baseDao;


    @RequestMapping(value = "/detail", consumes = "*", method = RequestMethod.GET)
    public String detail(HttpServletRequest servletRequest, @RequestParam Map queryParamMap) throws ApiBizException {
        SyMap queryParams = new SyMap(queryParamMap);
        SyValidationUtils.valid()
                .len(queryParams.get("id"), 32, true, "id格式错误");
        SysRoleDo sysRoleDo = sysRoleService.getById(queryParams.getString("id"));
        return this.normalResp(sysRoleDo);
    }

    @SyResource(system = "SHYTONG")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(HttpServletRequest servletRequest, @RequestBody SyMap params) throws ApiBizException {

        SyValidationUtils.valid()
                .isInt(params.get("pageNum"), true, "页码格式错误")
                .isInt(params.get("pageSize"), true, "每页条数格式错误");
        PageInfo pageInfo = sysRoleService.listOfPage(params, params.getInteger("pageNum"), params.getInteger("pageSize"));
        return this.normalRespPage(pageInfo);
    }

    @SyResource(system = "SHYTONG", value = {"362e1db043bc4f1194ea2dba658e7eb0"})
    @RequestMapping(value = "/delete", consumes = "*", method = RequestMethod.GET)
    public String delete(HttpServletRequest servletRequest, @RequestParam Map queryParamMap) throws ApiBizException {
        SyMap queryParams = new SyMap(queryParamMap);
        SyValidationUtils.valid()
                .len(queryParams.get("id"), 32, true, "id格式错误");
        sysRoleService.deleteById(queryParams.getString("id"));
        return this.normalResp();
    }

    @RequestMapping(value = "/getRoleUserList", method = RequestMethod.POST)
    public String getRoleUserList(HttpServletRequest servletRequest, @RequestBody SyMap params) throws ApiBizException {

        SyValidationUtils.valid()
                .isInt(params.get("pageNum"), true, "页码格式错误")
                .isInt(params.get("pageSize"), true, "每页条数格式错误")
                .checkBlank(params.get("roleId"), true, "角色id不能为空");
        PageInfo pageInfo = sysRoleService.getRoleUserList(params, params.getInteger("pageNum"), params.getInteger("pageSize"));
        return this.normalRespPage(pageInfo);
    }

    @RequestMapping(value = "/getUserRoleList", method = RequestMethod.POST)
    public String getUserRoleList(HttpServletRequest servletRequest, @RequestBody SyMap params) throws ApiBizException {

        SyValidationUtils.valid()
                .isInt(params.get("pageNum"), true, "页码格式错误")
                .isInt(params.get("pageSize"), true, "每页条数格式错误")
                .checkBlank(params.get("userId"), true, "用户id不能为空");
        PageInfo pageInfo = sysRoleService.getUserRoleList(params, params.getInteger("pageNum"), params.getInteger("pageSize"));
        return this.normalRespPage(pageInfo);
    }

    @RequestMapping(value = "/getRoleResource", method = RequestMethod.POST)
    public String getRoleResource(HttpServletRequest servletRequest, @RequestBody SyMap params) throws ApiBizException {

        SyValidationUtils.valid()
                .checkBlank(params.get("roleId"), true, "用户id不能为空");
        List<Object> list = sysRoleService.getRoleResources(params);
        return this.normalRespPage(list);
    }

    @RequestMapping(value = "/getRoleChannel", method = RequestMethod.POST)
    public String getRoleChannel(HttpServletRequest servletRequest, @RequestBody SyMap params) throws ApiBizException {

        SyValidationUtils.valid()
                .checkBlank(params.get("roleId"), true, "用户id不能为空");
        List<Object> list = sysRoleService.getRoleChannel(params);
        return this.normalRespPage(list);
    }

    /**
     * 角色栏目展示
     * @param servletRequest
     * @param params
     * @return
     * @throws ApiBizException
     */
    @SyResource(system = "SHYTONG")
    @RequestMapping(value = "/person/getRoleChannel", method = RequestMethod.POST)
    public String getRoleChannelPerson(HttpServletRequest servletRequest, @RequestBody SyMap params) throws ApiBizException {

        Subject s = SyAuthUtil.getSubject();

        SysUserDo u = (SysUserDo) s.getAuthenticationInfo().getUserInfo();

        params.put("userId", u.getId());
        PageInfo pageInfo = sysRoleService.getUserRoleList(params, 1, 100);
        Map map = (HashMap)pageInfo.getList().get(0);
        params.put("roleId", map.get("roleId"));
        List<Object> list = sysRoleService.getRoleChannelPerson(params);
        return this.normalRespPage(list);
    }

    @SyResource(system = "SHYTONG", value = {"7c33bea627ce46d5951f8b3ef9a4eb07"})
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(HttpServletRequest servletRequest, @RequestBody SysRoleDo sysRoleDo) throws ApiBizException {

        SyValidationUtils.valid()
                .len(sysRoleDo.getName(), 256, true, "角色名称格式错误")
                .len(sysRoleDo.getOrgId(), 32, true, "机构id格式错误")
                .len(sysRoleDo.getRemark(), 256, false, "备注格式错误");
        sysRoleService.insert(sysRoleDo);
        return this.normalResp();
    }

    @SyResource(system = "SHYTONG", value = {"01d09e96b1564d0cae658e3165dca26d"})
    @RequestMapping(value = "/addUsers", method = RequestMethod.POST)
    public String addUsers(HttpServletRequest servletRequest, @RequestBody RoleUserDto roleUserDto) throws ApiBizException {

        SyValidationUtils.valid()
                .collection(roleUserDto.getUserIds(), false, "用户id列表格式错误")
                .len(roleUserDto.getRoleId(), 32, false, "角色ID格式错误");
        int n = sysRoleService.addUsers(roleUserDto);
        return this.normalResp();
    }

    @SyResource(system = "SHYTONG")
    @RequestMapping(value = "/addRoles", method = RequestMethod.POST)
    public String addRoles(HttpServletRequest servletRequest, @RequestBody RoleUserDto roleUserDto) throws ApiBizException {

        SyValidationUtils.valid()
                .checkBlank(roleUserDto.getUserId(), true, "用户ID不能为空")
                .collection(roleUserDto.getRoleIds(), true, "角色ID列表格式错误");
        int n = sysRoleService.addRoles(roleUserDto);
        return this.normalResp();
    }

    @SyResource(system = "SHYTONG")
    @RequestMapping(value = "/addResources", method = RequestMethod.POST)
    public String addResources(HttpServletRequest servletRequest, @RequestBody RoleResourceDto roleResourceDto) throws ApiBizException {

        SyValidationUtils.valid()
                .len(roleResourceDto.getRoleId(), 32, true, "角色id格式错误")
                .collection(roleResourceDto.getResources(), true, "资源格式错误");
        int n = sysRoleService.addResources(roleResourceDto);
        return this.normalResp();
    }

    @SyResource(system = "SHYTONG")
    @RequestMapping(value = "/addChannel", method = RequestMethod.POST)
    public String addChannel(HttpServletRequest servletRequest, @RequestBody RoleChannelDto roleChannelDto) throws ApiBizException {

        SyValidationUtils.valid()
                .len(roleChannelDto.getRoleId(), 32, true, "角色id格式错误");
//            .collection(roleChannelDto.getResources(),true,"资源格式错误");
        int n = sysRoleService.addChannelResources(roleChannelDto);
        return this.normalResp();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(HttpServletRequest servletRequest, @RequestBody SysRoleDo sysRoleDo) throws ApiBizException {

        SyValidationUtils.valid()
                .len(sysRoleDo.getId(), 32, true, "id格式错误")
                .len(sysRoleDo.getName(), 256, true, "角色名称格式错误")
                .len(sysRoleDo.getOrgId(), 32, true, "机构id格式错误")
                .len(sysRoleDo.getRemark(), 256, false, "备注格式错误");
        sysRoleService.updateById(sysRoleDo, "id,name,orgId,remark");
        return this.normalResp();
    }
}

