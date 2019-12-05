package com.shytong.modules.front.controller;

import com.shytong.common.web.BaseController;
import com.shytong.modules.front.service.IFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.IntegerSyntax;
import javax.servlet.http.HttpServletRequest;
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
    public String groupProfile(HttpServletRequest servletRequest, ModelMap model, Integer channelId) {
        frontService.setHtml(model);
        frontService.setContent(model, 2, 1, 1, "about");
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
        frontService.setContent(model, channelId, pageNum, 8, "qualification");
        return "/view/qualification";
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
        frontService.setContent(model, channelId, pageNum, 8, "case");
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
        frontService.setContent(model, channelId, pageNum, 6, "news");
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
    public String talentsWanted(HttpServletRequest servletRequest, ModelMap model, Integer channelId, Integer pageNum) {
        frontService.setHtml(model);
        frontService.setContent(model, 6, 1, 1, "join");
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
    public String contactUs(HttpServletRequest servletRequest, ModelMap model, Integer channelId, Integer pageNum) {
        frontService.setHtml(model);
        frontService.setContent(model, 7, 1, 1, "contact");
        return "/view/contact";
    }

    /**
     * 文章详情
     *
     * @param servletRequest
     * @param model
     * @param channelId
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(HttpServletRequest servletRequest, ModelMap model, Integer channelId, String articleId) {
        frontService.setHtml(model);
        frontService.getArticleDetail(model, channelId, articleId);
        return "/view/detail";
    }

    /**
     * 管理后台登录页码
     *
     * @param servletRequest
     * @return
     */
    @RequestMapping(value = "/admin/login")
    public String adminLoginPage(HttpServletRequest servletRequest) {
        return "/mgr/login";
    }

    /**
     * 管理后台首页页面
     *
     * @param servletRequest
     * @return
     */
    @RequestMapping(value = "/admin/index")
    public String adminIndexPage(HttpServletRequest servletRequest) {
        return "/mgr/index";
    }

    /**
     * 首页轮播图页面
     *
     * @param servletRequest
     * @return
     */
    @RequestMapping(value = "/admin/settings-carousel", produces = "text/html;charset=utf-8", method = RequestMethod.GET)
    public String adminIndexCarouselPage(HttpServletRequest servletRequest, ModelMap model, Integer pageNum, Integer pageSize) {
        frontService.getSysConfig(model, pageNum, pageSize);
        return "/mgr/settings/carousel/index";
    }

    /**
     * 添加轮播图页面页面
     *
     * @param servletRequest
     * @return
     */
    @RequestMapping(value = "/admin/carousel-add", produces = "text/html;charset=utf-8", method = RequestMethod.GET)
    public String adminAddCarouselPage(HttpServletRequest servletRequest) {
        return "/mgr/settings/carousel/add";
    }

    /**
     * 修改轮播图页面页面
     *
     * @param servletRequest
     * @return
     */
    @RequestMapping(value = "/admin/carousel-edit", produces = "text/html;charset=utf-8", method = RequestMethod.GET)
    public String adminEditCarouselPage(HttpServletRequest servletRequest) {
        return "/mgr/settings/carousel/edit";
    }

    /**
     * 一级类别管理页面
     *
     * @param servletRequest
     * @return
     */
    @RequestMapping(value = "/admin/settings-firstClass", produces = "text/html;charset=utf-8", method = RequestMethod.GET)
    public String adminFirstClassPage(HttpServletRequest servletRequest, ModelMap model, Integer pageNum, Integer pageSize) {
        frontService.getFirstChannel(model, pageNum, pageSize);
        return "/mgr/settings/firstclass/index";
    }

    /**
     * 添加一级类别管理页面
     *
     * @param servletRequest
     * @return
     */
    @RequestMapping(value = "/admin/firstClass-add", produces = "text/html;charset=utf-8", method = RequestMethod.GET)
    public String adminAddFirstClassPage(HttpServletRequest servletRequest, ModelMap model, Integer pageNum, Integer pageSize) {
        return "/mgr/settings/firstclass/add";
    }

    /**
     * 修改一级类别管理页面
     *
     * @param servletRequest
     * @return
     */
    @RequestMapping(value = "/admin/firstClass-edit", produces = "text/html;charset=utf-8", method = RequestMethod.GET)
    public String adminEditFirstClassPage(HttpServletRequest servletRequest, ModelMap model, Integer pageNum, Integer pageSize) {
        return "/mgr/settings/firstclass/edit";
    }

    /**
     * 一级类别管理页面
     *
     * @param servletRequest
     * @return
     */
    @RequestMapping(value = "/admin/settings-secondClass", produces = "text/html;charset=utf-8", method = RequestMethod.GET)
    public String adminSecondClassPage(HttpServletRequest servletRequest, ModelMap model, Integer pageNum, Integer pageSize) {
        return "/mgr/settings/secondclass/index";
    }

    /**
     * 添加二级类别管理页面
     *
     * @param servletRequest
     * @return
     */
    @RequestMapping(value = "/admin/secondClass-add", produces = "text/html;charset=utf-8", method = RequestMethod.GET)
    public String adminAddSecondClassPage(HttpServletRequest servletRequest, ModelMap model, Integer pageNum, Integer pageSize) {
        return "/mgr/settings/secondclass/add";
    }

    /**
     * 修改二级类别管理页面
     *
     * @param servletRequest
     * @return
     */
    @RequestMapping(value = "/admin/secondClass-edit", produces = "text/html;charset=utf-8", method = RequestMethod.GET)
    public String adminAddEditSecondClassPage(HttpServletRequest servletRequest, ModelMap model, Integer pageNum, Integer pageSize) {
        return "/mgr/settings/secondclass/edit";
    }

    /**
     * 模块管理
     *
     * @param servletRequest
     * @return
     */
    @RequestMapping(value = "/admin/modules-model/{id}", produces = "text/html;charset=utf-8", method = RequestMethod.GET)
    public String adminModulesPage(HttpServletRequest servletRequest, ModelMap model, Integer pageNum, @PathVariable String id) {
        System.out.println("id:" + id);
        return "/mgr/modules/model";
    }

}
