package com.shytong.modules.front.controller;

import com.shytong.modules.front.service.IFrontService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

/**
 * @description: 默认index页面前端控制类
 * @author: CL
 * @time: 2019/11/11 14:28
 */
@Controller
@RequestMapping(value = "/front/public", produces = "text/plain;charset=Utf-8")
public class FrontController {

//    @Autowired
//    private IFrontService frontService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest servletRequest, ModelMap model) {
//        frontService.setHtml(model);
        String title = "首页-亿创电力集团";
        model.addAttribute("logoImg","/img/upload/logo.png");
        model.addAttribute("title", title);
        return "/index/index";
    }


}
