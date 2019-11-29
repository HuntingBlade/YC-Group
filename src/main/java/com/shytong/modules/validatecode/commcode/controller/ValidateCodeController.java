package com.shytong.modules.validatecode.commcode.controller;

import com.shytong.modules.validatecode.commcode.utils.ValidateCodeUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 验证码控制类
 * @Author: CL
 * @Date: 2019/11/29 21:42
 */
@RestController
@RequestMapping("/code")
public class ValidateCodeController {
    /**
     * 返回验证码图片
     *
     * @param request
     * @param response
     * @param session
     */
    @RequestMapping(value = "/getCaptchaImg", method = RequestMethod.GET)
    public void getCaptchaImg(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        try {
            response.setContentType("image/png");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expire", "0");
            response.setHeader("Pragma", "no-cache");
            ValidateCodeUtil validateCode = new ValidateCodeUtil();
            // getRandomCodeImage方法会直接将生成的验证码图片写入response
            validateCode.getRandomCodeImage(request, response);
            // System.out.println("session里面存储的验证码为："+session.getAttribute("JCCODE"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 生成验证码,返回的是 base64
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getCaptchaBase64", method = RequestMethod.GET)
    public Object getCaptchaBase64(HttpServletRequest request, HttpServletResponse response) {
        Map result = new HashMap(16);
        try {
            response.setContentType("image/png");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expire", "0");
            response.setHeader("Pragma", "no-cache");
            ValidateCodeUtil validateCode = new ValidateCodeUtil();
            // 返回base64
            String base64String = validateCode.getRandomCodeBase64(request, response);
            result.put("url", "data:image/png;base64," + base64String);
            result.put("message", "created successfull");
            System.out.println("结果：" + result.get("url"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
