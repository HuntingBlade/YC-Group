package com.shytong.modules.verifycode.controller;

import com.shytong.common.exception.ApiBizException;
import com.shytong.common.web.BaseController;
import com.shytong.constant.VerifyCodeConsts;
import com.shytong.core.util.SyValidationUtils;
import com.shytong.core.util.VerifyCodeUtils;
import com.shytong.modules.verifycode.dto.SmsMsg;
import com.shytong.modules.verifycode.service.IVerifyCodeService;
import com.shytong.sys.sms.service.ISmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author sytong
 * @Package com.shytong.sys
 * @Description:
 * @date 2018-01-1617:24
 */
@RestController
@RequestMapping(value = "/verifycode", produces = "application/json;charset=UTF-8;", consumes = "application/json;")

public class VerifyCodeController extends BaseController {
    @Resource(name = "verifyCodeService")
    IVerifyCodeService verifyCodeService;
    @Autowired
    ISmsService iSmsService;
    @Value("${sy.appmode}")
    private String appmode;

    /**
     * 获取验证码
     */
    @RequestMapping(value = "/code", method = RequestMethod.GET, produces = "*", consumes = "*")
    public void valiCode(HttpServletRequest request, HttpServletResponse response, String type) throws ApiBizException {


        SyValidationUtils.checkNull(type);
//        if(this.validatorService.isInRequired(type,"",VerifyCodeConsts.ORG_REG,VerifyCodeConsts.ORG_LOGIN,VerifyCodeConsts.ORG_RESETPWD)!=null){
//           return ;
//        }
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        // request
        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);

        // 存入会话session
        HttpSession session = request.getSession(true);
        session.setAttribute("verifyCode_" + type, verifyCode.toLowerCase());
        // 生成图片
        int w = 200, h = 80;
        try {
            VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    /**
     * 获取验证码
     */
    @RequestMapping(value = "/smscode", method = RequestMethod.POST)
    public String smscode(HttpServletRequest request, @RequestBody SmsMsg smsMsg) throws ApiBizException {

        return this.sendMsg(request, smsMsg, 5, true);
    }

    /**
     * 获取验证码
     */
    @RequestMapping(value = "/smscode2", method = RequestMethod.POST)
    public String smscode2(HttpServletRequest request, @RequestBody SmsMsg smsMsg) throws ApiBizException {


        return this.sendMsg(request, smsMsg, 5, true);
    }


    private String sendMsg(HttpServletRequest request, SmsMsg smsMsg, int time, boolean checkVcode) throws ApiBizException {
        if (checkVcode) {
            this.checkNull(SyValidationUtils.isNotBlank(smsMsg.getVerifyCode(), "图形验证码"));
        }


        this.checkNull(SyValidationUtils.isPhoneRequired(smsMsg.getPhone(), "手机号"));
        this.checkNull(SyValidationUtils.isInRequired(smsMsg.getType(), "短信类型"
                , VerifyCodeConsts.ORG_REG, VerifyCodeConsts.ORG_LOGIN, VerifyCodeConsts.ORG_RESETPWD));
        if (checkVcode) {
            if (!VerifyCodeUtils.checkVerifyCode(request.getSession(), smsMsg.getType(), smsMsg.getVerifyCode())) {
                return this.normalResp(-1, "图形验证码不正确");
            }
            ;
        }
        String code = verifyCodeService.sendSmsCode(smsMsg, time);

        if ("dep".equals(appmode)) {
            return this.normalResp(code);
        }

        return this.normalResp();
    }


}
