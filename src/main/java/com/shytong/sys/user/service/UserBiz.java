package com.shytong.sys.user.service;

import com.shytong.common.exception.ApiBizException;
import com.shytong.constant.SysTemCodeConstant;
import com.shytong.core.auth.AuthenticationToken;
import com.shytong.core.auth.Subject;
import com.shytong.core.auth.SyAuthUtil;
import com.shytong.core.constant.SyConstant;
import com.shytong.core.util.SyRequestUtil;
import com.shytong.core.util.SyValidationUtils;
import com.shytong.core.util.VerifyCodeUtils;
import com.shytong.modules.verifycode.service.IVerifyCodeService;
import com.shytong.sys.user.UserDo;
import com.shytong.sys.user.UserDto;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * @author sytong
 * @Package com.shytong.sys.user.service
 * @Description:
 * @date 2018-01-2415:15
 */
public abstract class UserBiz<T extends UserDto,T1 extends UserDo>   {

    private  UserConfig userConfig;
    @Resource(name = "verifyCodeService")
    IVerifyCodeService verifyCodeService;
    protected   UserBiz(){

    }

    protected   UserBiz(UserConfig userConfig){

    this.userConfig=userConfig;
    }

    protected void setUserConfig(UserConfig userConfig){
        this.userConfig=userConfig;
    }
    protected  abstract void addUser(T userDto);
    protected abstract void checkUserInfo(T userDto)  throws ApiBizException;
    protected abstract void afterRegister(T userDto);
    protected  abstract T1 loginByUser (T userDto)  throws ApiBizException;
    protected  abstract T1 loginByPhone (T userDto)  throws ApiBizException;
    protected  abstract void checkPwd ( T1 t,T userDto)  throws ApiBizException;

    protected  abstract void setPwd (T userDto)  throws ApiBizException;
    protected  abstract T1  edit (T userDto)  throws ApiBizException;

    //验证手机号和验证码是否按格式填写
    protected void check(T userDto) throws ApiBizException {
        SyValidationUtils.checkNull(SyValidationUtils.isNotBlank(userDto.getPhone(),"手机号"));
        SyValidationUtils.checkNull(SyValidationUtils.isLenth(userDto.getSmsCode(),"手机验证码",6,6));
        SyValidationUtils.checkNull(SyValidationUtils.isNotBlank(userDto.getPassword(),"密码"));
    }
    //账号注册
    protected void checkAccount(T userDto) throws ApiBizException {
        SyValidationUtils.checkNull(SyValidationUtils.isLenth(userDto.getUserName(),"账户名称",userConfig.getNameLenMin(),userConfig.getNamelenMax()));
        SyValidationUtils.checkNull(SyValidationUtils.isNotBlank(userDto.getPassword(),"密码"));
    }

    protected  void checkLogin(T userDto,boolean flag)throws ApiBizException {
        SyValidationUtils.checkNull(SyValidationUtils.isNotBlank(userDto.getUserName(),"用户名"));
        SyValidationUtils.checkNull(SyValidationUtils.isNotBlank(userDto.getPassword(),"密码"));

        if(flag){
            SyValidationUtils.checkNull(SyValidationUtils.isNotBlank(userDto.getVerifyCode(),"图形验证码"));
          HttpServletRequest request= SyRequestUtil.getCurrentRequest();
           if(request==null){
               throw new ApiBizException(-1,"系统异常");
           }
           if(!VerifyCodeUtils.checkVerifyCode(request.getSession()
                   ,userConfig.getLoginType(),userDto.getVerifyCode())) {

               throw new ApiBizException(-1,"图形验证码错误");
           };

        }



    }
    protected  void checkSmsLogin(T userDto)throws ApiBizException {
        SyValidationUtils.checkNull(SyValidationUtils.isPhoneRequired(userDto.getPhone(),"手机号"));
        SyValidationUtils.checkNull(SyValidationUtils.isNotBlank(userDto.getSmsCode(),"手机验证码"));




    }
    public  T1 getLoginInfo(){


        return  getLoginInfo(userConfig.getUserType());

    }
    public  T1 checkUserIsLogin() throws ApiBizException {


        return  checkUserIsLogin(userConfig.getUserType());

    }
    public  T1 checkUserIsLogin(String userType) throws ApiBizException {



        HttpServletRequest request= SyRequestUtil.getCurrentRequest();
        if(request==null||request.getSession()==null||request.getSession().getAttribute(userType)==null){
            throw  new ApiBizException(1011,"未登陆");
        }

        return (T1)request.getSession().getAttribute(userType);



    }
    public T1  getLoginInfo(String userType){
        HttpServletRequest request= SyRequestUtil.getCurrentRequest();
        if(request==null||request.getSession()==null){
           return null;
          }


        return (T1)request.getSession().getAttribute(userType);
    }

    public  void updateSession(T1 t) throws ApiBizException {
        HttpServletRequest request= SyRequestUtil.getCurrentRequest();
        if(request==null){
            throw new ApiBizException(-1,"系统异常");
        }
        request.getSession().setAttribute(userConfig.getUserType(),t);



    }

    public  void smsLogin(T userDto)throws ApiBizException {
        T1 t1=this.getLoginInfo();

        if(t1!=null){

            throw new ApiBizException(1010,"已登陆");
        }
        this.checkSmsLogin(userDto);
        if(!verifyCodeService.checkSmsCode(userDto.getPhone()
                ,userDto.getSmsCode()
                ,userConfig.getLoginType()
                ,userConfig.getAppId())){
            throw  new ApiBizException(-1,"短信验证码错误");
        } ;

        T1 o=  this.loginByPhone(userDto);


        login(o,this.userConfig.getUserType());

    }

    private  void login (T1 o,String userType)  throws ApiBizException {
        HttpServletRequest request= SyRequestUtil.getCurrentRequest();
        if(request==null){
            throw new ApiBizException(-1,"系统异常");
        }
        request.getSession().setAttribute(userType,o);

    }
    public  void login(T userDto,boolean checkVcode) throws ApiBizException {



        if(checkVcode){
            SyValidationUtils.checkVerifyCode(this.userConfig.getLoginType(),userDto.getVerifyCode());


        }

        Subject s= SyAuthUtil.getSubject(this.userConfig.getSystemCode());
        AuthenticationToken authenticationToken=new AuthenticationToken(
                userDto.getUserName()
                ,userDto.getPassword(),"login",null);
        if(s.isAuthented()){
            s.loginOut();
            s= SyAuthUtil.getSubject(this.userConfig.getSystemCode());
        }


        s.login(authenticationToken);



    }
    //手机注册
    public void register(T userDto) throws ApiBizException {
        //参数校验
        this.check(userDto);
        //注册信息判断
        if(!verifyCodeService.checkSmsCode(userDto.getPhone()
                ,userDto.getSmsCode()
                ,userConfig.getRegType()
                ,userConfig.getAppId())){
            throw  new ApiBizException(-1,"短信验证码错误");
        } ;
        //验证短信是否正确
        synchronized (this) {
            this.checkUserInfo(userDto);
            //注册
            this.addUser(userDto);
            this.afterRegister(userDto);
        }

    }

    //手机注册
    public void changePwd(T userDto) throws ApiBizException {

       T1 t= this.checkUserIsLogin();
        SyValidationUtils.checkNull(SyValidationUtils.isNotBlank(userDto.getOldPassword(),"原密码"));
        SyValidationUtils.checkNull(SyValidationUtils.isNotBlank(userDto.getPassword(),"新密码"));

        this.checkPwd( t, userDto);
        userDto.setId(t.getId());
        this.setPwd(userDto);

    }

    //手机注册
    public void resetPwd(T userDto) throws ApiBizException {

        T1 t= this.checkUserIsLogin();
        SyValidationUtils.checkNull(SyValidationUtils.isNotBlank(userDto.getPassword(),"新密码"));
        SyValidationUtils.checkNull(SyValidationUtils.isNotBlank(userDto.getSmsCode(),"手机验证码"));
        //注册信息判断
        if(!verifyCodeService.checkSmsCode(t.getPhone()
                ,userDto.getSmsCode()
                ,userConfig.getResetPwdType()
                ,userConfig.getAppId())){
            throw  new ApiBizException(-1,"短信验证码错误");
        } ;
        userDto.setId(t.getId());
        this.setPwd(userDto);

    }

    //手机注册
    public void forgetPwd(T userDto) throws ApiBizException {
        SyValidationUtils.checkNull(SyValidationUtils.isPhoneRequired(userDto.getPhone(),"手机号"));
        SyValidationUtils.checkNull(SyValidationUtils.isNotBlank(userDto.getSmsCode(),"手机验证码"));
        SyValidationUtils.checkNull(SyValidationUtils.isNotBlank(userDto.getPassword(),"新密码"));
        //注册信息判断
        if(!verifyCodeService.checkSmsCode(userDto.getPhone()
                ,userDto.getSmsCode()
                ,userConfig.getResetPwdType()
                ,userConfig.getAppId())){
            throw  new ApiBizException(-1,"短信验证码错误");
        } ;
        T1 user= this.loginByPhone(userDto);
        userDto.setId(user.getId());
//        this.checkPwd( userDto);
        this.setPwd(userDto);

    }
    //手机注册
    public void editUser(T userDto) throws ApiBizException {

        Subject s= SyAuthUtil.getSubject(SysTemCodeConstant.SYSTEM_ORGANISER);
        if(!s.isAuthented()){
            throw new ApiBizException(SyConstant.ERR_AUTH_NO_LOGIN,"请先登录");
        }
        userDto.setId(s.getAuthenticationInfo().getUserID());
        T1  t1 = this.edit(userDto);




    }

    public void  loginOut(){
        Subject s= SyAuthUtil.getSubject(this.userConfig.getSystemCode());
    if(s!=null){
        s.loginOut();

    }

    }





}
