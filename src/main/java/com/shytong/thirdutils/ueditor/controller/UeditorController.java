package com.shytong.thirdutils.ueditor.controller;

import com.baidu.ueditor.ActionEnter;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: CL
 * @Date: 2019/12/18 9:06
 */
@Controller
public class UeditorController {
    @RequestMapping(value = "/config")
    public void config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        String rootPath = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/third/ueditor/jsp";
        System.out.println(rootPath);
        try {
            response.setCharacterEncoding("UTF-8");
            String exec = new ActionEnter(request, rootPath).exec();
            System.out.println(exec);
            PrintWriter writer = response.getWriter();
            writer.write(new ActionEnter(request, rootPath).exec());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
