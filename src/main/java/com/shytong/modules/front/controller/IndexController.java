package com.shytong.modules.front.controller;

import com.shytong.modules.front.service.IFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 默认index页面前端控制类
 * @author: CL
 * @time: 2019/11/11 14:28
 */
@RestController
@RequestMapping(value = "/front/public", produces = "text/plain;charset=Utf-8")
public class IndexController {

    @Autowired
    private IFrontService frontService;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest servletRequest, ModelMap model) {
        frontService.setHtml(model);
        return "/index/index";
    }


}
