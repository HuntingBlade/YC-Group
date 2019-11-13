package com.shytong.modules.verifycode.service;

import com.shytong.common.exception.ApiBizException;
import com.shytong.modules.verifycode.dto.SmsMsg;
import com.shytong.modules.verifycode.model.VerifyCodeDo;

/**
 * @author shyTong
 * @Package com.shytong.modules.verifycode.service
 * @Description:
 * @date 2018-01-17 16:58:08
 */
public interface IVerifyCodeService {
    public  int addVerifyCode(VerifyCodeDo verifyCodeDo, int time) throws ApiBizException;
    public  String sendSmsCode(SmsMsg smsMsg, int time) throws ApiBizException;
    public  boolean checkSmsCode(String phone, String code, String type, String appID);
}
