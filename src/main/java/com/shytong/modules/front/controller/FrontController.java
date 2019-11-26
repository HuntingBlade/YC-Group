package com.shytong.modules.front.controller;

import com.alibaba.fastjson.JSON;
import com.shytong.common.model.SyMap;
import com.shytong.common.web.BaseController;
import com.shytong.modules.front.service.IFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.util.List;
import java.util.Map;

/**
 * @description: 默认index页面前端控制类
 * @author: CL
 * @time: 2019/11/11 14:28
 */
@Controller
@RequestMapping(value = "/front/public", produces = "text/plain;charset=Utf-8")
public class FrontController extends BaseController {

    @Autowired
    private IFrontService frontService;

    /**
     * 首页
     *
     * @param servletRequest
     * @param model
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest servletRequest, ModelMap model) {
        frontService.setHtml(model);
        frontService.setIndexContent(model);
        return "/view/index";
    }

    /**
     * 集团简介
     *
     * @param servletRequest
     * @param model
     * @return
     */
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String groupProfile(HttpServletRequest servletRequest, ModelMap model) {
        frontService.setHtml(model);
        frontService.setAboutContent(model);
        return "/view/about";
    }

    /**
     * 资质荣誉
     *
     * @param servletRequest
     * @param model
     * @return
     */
    @RequestMapping(value = "/qualification", method = RequestMethod.GET)
    public String qualification(HttpServletRequest servletRequest, ModelMap model, Integer channelId, Integer pageNum) {
        frontService.setHtml(model);
        frontService.setQualificationContent(model, channelId, pageNum);
        return "/view/qualification";
    }

    /**
     * 栏目详情
     *
     * @param servletRequest
     * @param map
     * @return
     */
    @RequestMapping(value = "/channelInfo", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getSonChannelInfo(HttpServletRequest servletRequest, @RequestBody Map map) {
        SyMap params = new SyMap(map);
        Integer channelId = params.getInteger("channelId");
        Integer pageNum = params.getInteger("pageNum");
        Integer pageSize = params.getInteger("pageSize");
        Map data = frontService.getSonChannelInfo(channelId, pageNum, pageSize);
        return JSON.toJSONString(data);
    }

    /**
     * 工程业绩
     *
     * @param servletRequest
     * @param model
     * @return
     */
    @RequestMapping(value = "/case", method = RequestMethod.GET)
    public String projectCase(HttpServletRequest servletRequest, ModelMap model, Integer channelId, Integer pageNum) {
        frontService.setHtml(model);
        frontService.setProjectCaseContent(model, channelId, pageNum);
        return "/view/case";
    }

    /**
     * 新闻中心
     *
     * @param servletRequest
     * @param model
     * @return
     */
    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String newsCenter(HttpServletRequest servletRequest, ModelMap model, Integer channelId, Integer pageNum) {
        frontService.setHtml(model);
        frontService.setNewsCenterCaseContent(model, channelId, pageNum);
        return "/view/news";
    }

    /**
     * 诚聘英才
     *
     * @param servletRequest
     * @param model
     * @return
     */
    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String talentsWanted(HttpServletRequest servletRequest, ModelMap model) {
        frontService.setHtml(model);
        return "/view/join";
    }

    /**
     * 联系我们
     *
     * @param servletRequest
     * @param model
     * @return
     */
    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contactUs(HttpServletRequest servletRequest, ModelMap model) {
        frontService.setHtml(model);
        return "/view/news";
    }

    /**
     * 文章详情
     *
     * @param servletRequest
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(HttpServletRequest servletRequest, ModelMap model) {
        frontService.setHtml(model);
        return "/view/detail";
    }
}
