package com.shytong.sys.user.service;

import com.shytong.common.exception.ApiBizException;
import com.shytong.core.util.SyValidationUtils;
import com.shytong.sys.user.UserDto;


/**
 * @author sytong
 * @Package com.shytong.sys.user.service
 * @Description:
 * @date 2018-01-2415:13
 */
public abstract  class UserService<T extends UserDto>   {


    private  UserConfig userConfig;
//    @Resource(name = "verifyCodeService")
//    IVerifyCodeService verifyCodeService;
    protected   UserService(){

    }

    protected   UserService(UserConfig userConfig){

    }
    protected  abstract void addUser(T userDto);
    protected abstract void checkUserInfo()  throws ApiBizException;
    protected abstract void afterRegister(T userDto);
    //验证手机号和验证码是否按格式填写
    protected void check(T userDto) throws ApiBizException {
        SyValidationUtils.checkNull(SyValidationUtils.isNotBlank(userDto.getPhone(),"手机号"));
        SyValidationUtils.checkNull(SyValidationUtils.isLenth(userDto.getSmsCode(),"手机验证码",4,4));
        SyValidationUtils.checkNull(SyValidationUtils.isNotBlank(userDto.getPassword(),"密码"));
    }
    //手机注册
    protected void register(T userDto) throws ApiBizException {
        //参数校验
        this.check(userDto);
        //注册信息判断
//        if(!verifyCodeService.checkSmsCode(userDto.getPhone()
//                ,userDto.getSmsCode()
//                ,userConfig.getRegType()
//                ,userConfig.getAppId())){
//
//            return;
//        } ;
        //验证短信是否正确
        synchronized (this) {
            this.checkUserInfo();
            //注册
            this.addUser(userDto);
            this.afterRegister(userDto);
        }

    }

}
