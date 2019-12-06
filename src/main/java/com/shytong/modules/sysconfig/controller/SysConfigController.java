package com.shytong.modules.sysconfig.controller;

import com.shytong.common.annotation.SyResource;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.common.web.BaseController;
import com.shytong.constant.SysTemCodeConstant;
import com.shytong.core.util.SyValidationUtils;
import com.shytong.modules.sysconfig.model.SysConfigDo;
import com.shytong.modules.sysconfig.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @description: 系统配置控制类
 * @author: CL
 * @time: 2019/11/11 16:49
 */

@RestController
@RequestMapping(value = "/api/sysConfig", produces = "application/json;charset=UTF-8", consumes = "application/json")
public class SysConfigController extends BaseController {

    @Autowired
    private ISysConfigService sysConfigService;

    /**
     * 添加系统配置
     *
     * @param servletRequest
     * @param sysConfigDo
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/add/{type}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    public String addSysConfig(HttpServletRequest servletRequest, @RequestBody SysConfigDo sysConfigDo, @PathVariable String type) throws ApiBizException {
        String result = sysConfigService.insert(sysConfigDo, type);
        return this.normalResp(result);
    }

    /**
     * 删除系统配置
     *
     * @param servletRequest
     * @param params
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/deleted", method = RequestMethod.GET)
    @SyResource(system = SysTemCodeConstant.SYSTEM_MANAGER)
    public String deletedSysConfig(HttpServletRequest servletRequest, @RequestParam Map params) throws ApiBizException {
        SyMap map = new SyMap(params);
        SyValidationUtils.valid()
                .len(map.get("id"), 32, false, "id格式错误");
        Integer result = sysConfigService.deleted(map);
        return this.normalResp(result);
    }

    /**
     * 修改系统配置项
     *
     * @param servletRequest
     * @param sysConfigDo
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    @SyResource(system = SysTemCodeConstant.SYSTEM_MANAGER)
    public String updateSysConfig(HttpServletRequest servletRequest, @RequestBody SysConfigDo sysConfigDo) throws ApiBizException {
        String result = sysConfigService.update(sysConfigDo);
        return this.normalResp(result);
    }

    /**
     * 获取系统配置参数列表
     *
     * @param servletRequest
     * @param params
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @SyResource(system = SysTemCodeConstant.SYSTEM_MANAGER)
    public String findSysConfig(HttpServletRequest servletRequest, @RequestBody SyMap params) {
        List list = sysConfigService.getList(params);
        return this.normalRespPage(list);
    }

}
