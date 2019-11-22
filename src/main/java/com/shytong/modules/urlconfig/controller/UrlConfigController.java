package com.shytong.modules.urlconfig.controller;

import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.common.web.BaseController;
import com.shytong.core.util.SyIdUtils;
import com.shytong.core.util.SyValidationUtils;
import com.shytong.modules.urlconfig.model.UrlConfigDo;
import com.shytong.modules.urlconfig.service.IUrlConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Description: 链接配置控制类
 * @Author: CL
 * @Date: 2019/11/21 12:13
 */
@RestController
@RequestMapping(value = "/url/config", produces = "application/json;charset=utf-8", consumes = "application/json")
public class UrlConfigController extends BaseController {

    @Autowired
    private IUrlConfigService urlConfigService;

    /**
     * 添加链接配置
     *
     * @param servletRequest
     * @param urlConfigDo
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUrlConfig(HttpServletRequest servletRequest, @RequestBody UrlConfigDo urlConfigDo) throws ApiBizException {
        SyValidationUtils.valid()
                .len(urlConfigDo.getUrlName(), 255, true, "链接名称格式错误")
                .len(urlConfigDo.getUrlState(), 11, true, "链接状态格式错误");
        Integer result = urlConfigService.addUrlConfig(urlConfigDo);
        return this.normalResp(result);
    }

    /**
     * 删除链接配置
     *
     * @param servletRequest
     * @param map
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/deleted", method = RequestMethod.GET,consumes = "*")
    public String deletedUrlConfig(HttpServletRequest servletRequest, @RequestParam Map map) {
        // 参数未校验
        SyMap params = new SyMap(map);
        Integer urlId = params.getInteger("urlId");
        Integer result = urlConfigService.deletedUrlConfig(urlId);
        return this.normalResp(result);
    }

    /**
     * 修改链接配置
     *
     * @param servletRequest
     * @param urlConfigDo
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUrlConfig(HttpServletRequest servletRequest, @RequestBody UrlConfigDo urlConfigDo) {
        // 参数未校验
        Integer result = urlConfigService.updateUrlConfig(urlConfigDo);
        return this.normalResp(result);
    }

    /**
     * 获取链接配置列表
     *
     * @param servletRequest
     * @param map
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String getUrlConfigList(HttpServletRequest servletRequest, @RequestBody Map map) {
        SyMap params = new SyMap(map);
        Integer pageNum = params.getInteger("pageNum");
        Integer pageSize = params.getInteger("pageSize");
        PageInfo result = urlConfigService.getUrlConfigList(pageNum, pageSize, params);
        return this.normalRespPage(result);
    }

}
