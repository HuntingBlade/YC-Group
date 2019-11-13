package com.shytong.modules.wx.controller;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.common.web.BaseController;
import com.shytong.core.database.BaseDao;
import com.shytong.core.util.SyValidationUtils;
import com.shytong.modules.wx.model.WxMpDo;
import com.shytong.modules.wx.service.IWxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
@RestController
@RequestMapping( value = "/api/wxMp", produces = "application/json;charset=UTF-8;",  consumes = "application/json;")
public class WxMpController extends BaseController {


    @Resource(name="wxMpService")
    private IWxMpService wxMpService;
    @Autowired
    private BaseDao baseDao;



    @RequestMapping(value = "/auth2",consumes="*",method = RequestMethod.GET)
    public  String auth2(HttpServletRequest servletRequest,@RequestParam Map queryParamMap,HttpServletResponse httpServletResponse)throws ApiBizException{
        SyMap queryParams = new SyMap( queryParamMap );
        SyValidationUtils.valid()
            .checkBlank(queryParams.get("wxmpid"),false,"微信id不能为空")
            .checkBlank(queryParams.get("mode"),false,"授权类型不能为空")
            .checkBlank(queryParams.get("op"),false,"操作不能为空")
            .checkBlank(queryParams.get("target"),false,"处理后跳转链接不能为空");
        wxMpService.auth2( queryParams, httpServletResponse );
        return this.normalResp(  );





        }
    
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public  String add(HttpServletRequest servletRequest,@RequestBody WxMpDo wxMpDo)throws ApiBizException{
        
        SyValidationUtils.valid()
            .len(wxMpDo.getWxCode(),256,false,"wx_code格式错误")
            .len(wxMpDo.getAppId(),256,false,"appid格式错误")
            .len(wxMpDo.getSecret(),256,false,"密钥格式错误")
            .len(wxMpDo.getServiceUrl(),256,false,"服务url格式错误")
            .len(wxMpDo.getServiceToken(),256,false,"token格式错误")
            .len(wxMpDo.getEncodingKey(),256,false,"消息加密密钥格式错误");
        wxMpService.insert(wxMpDo);
        return this.normalResp();





        }
    
    @RequestMapping(value = "/authorize",consumes="*",method = RequestMethod.GET)
    public  String authorize(HttpServletRequest servletRequest,@RequestParam Map queryParamMap,HttpServletResponse httpServletResponse)throws ApiBizException{
        SyMap queryParams = new SyMap( queryParamMap );
        
        wxMpService.authorize( queryParams, httpServletResponse );
        return this.normalResp(  );





        }
}

