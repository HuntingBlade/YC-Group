package com.shytong.modules.channel.controller;

import com.github.pagehelper.PageInfo;
import com.shytong.common.annotation.SyResource;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.common.web.BaseController;
import com.shytong.constant.SysTemCodeConstant;
import com.shytong.core.util.SyValidationUtils;
import com.shytong.modules.channel.model.ChannelDo;
import com.shytong.modules.channel.service.IChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @description: 栏目控制类
 * @author: CL
 * @time: 2019/11/12 17:05
 */
@RestController
@RequestMapping(value = "/api/channel", produces = "application/json;charset=utf-8", consumes = "application/json")
public class ChannelController extends BaseController {

    @Autowired
    private IChannelService channelService;

    /**
     * 添加栏目项
     *
     * @param servletRequest
     * @param channelDo
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @SyResource(system = SysTemCodeConstant.SYSTEM_MANAGER)
    public String insertChannel(HttpServletRequest servletRequest, @RequestBody ChannelDo channelDo) throws ApiBizException {
        SyValidationUtils.valid()
                .len(channelDo.getName(), 255, true, "名称格式错误");
        Integer result = channelService.insertChannel(channelDo);
        return this.normalResp(result);
    }

    /**
     * 删除栏目项
     *
     * @param servletRequest
     * @param map
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/deleted", method = RequestMethod.GET, consumes = "*")
    @SyResource(system = SysTemCodeConstant.SYSTEM_MANAGER)
    public String deletedChannel(HttpServletRequest servletRequest, @RequestParam Map map) throws ApiBizException {
        SyMap params = new SyMap(map);
        SyValidationUtils.valid()
                .isInt(params.get("id"), 20, true, "id格式错误");
        Integer result = channelService.deletedChannelById(params);
        return this.normalResp(result);
    }

    /**
     * 修改栏目项
     *
     * @param servletRequest
     * @param channelDo
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "*")
    @SyResource(system = SysTemCodeConstant.SYSTEM_MANAGER)
    public String updateChannel(HttpServletRequest servletRequest, @RequestBody ChannelDo channelDo) throws ApiBizException {
        SyValidationUtils.valid()
                .len(channelDo.getName(), 255, true, "名称格式错误");
        Integer result = channelService.updateChannel(channelDo);
        return this.normalResp(result);
    }

    /**
     * 获取栏目列表
     *
     * @param servletRequest
     * @param map
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/select", method = RequestMethod.POST, consumes = "*")
    @SyResource(system = SysTemCodeConstant.SYSTEM_MANAGER)
    public String selectList(HttpServletRequest servletRequest, @RequestBody Map map) throws ApiBizException {
        SyMap params = new SyMap(map);
        SyValidationUtils.valid()
                .isInt(params.get("pageNum"), 11, true, "页码格式错误")
                .isInt(params.get("pageSize"), 11, true, "每页大小格式错误");
        PageInfo<ChannelDo> result = channelService.selectList(params);
        return this.normalRespPage(result);
    }
}
