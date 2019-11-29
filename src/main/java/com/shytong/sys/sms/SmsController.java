package com.shytong.sys.sms;

import com.shytong.sys.sms.service.ISmsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sytong
 * @Package com.shytong.modules.organiser.controller
 * @Description:
 * @date 2018-01-1616:36
 */
@RestController
@RequestMapping( value = "/sms", produces = "application/json;charset=UTF-8;",  consumes = "application/json;")
public class SmsController {

//    /**
//     * 获取验证码
//     */
//    @Resource
//    ISmsService smsService;
//    @RequestMapping(value = "/sendCode", method = RequestMethod.POST, produces = "*", consumes = "*")
//    public void valiCode(HttpServletRequest request, HttpServletResponse response, @RequestBody SmsMsg s) {
//    }
}