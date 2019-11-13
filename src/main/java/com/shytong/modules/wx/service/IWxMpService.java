package com.shytong.modules.wx.service;
import com.shytong.common.dao.ICommService;
import com.shytong.common.model.SyMap;
import com.shytong.modules.wx.model.WxFatDo;
import com.shytong.modules.wx.model.WxMpDo;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 
 * @Description:
 * @date 2018-04-20 10:52:55
 */
public interface IWxMpService extends ICommService<WxMpDo>  {

    /**
    * 获取微信
    * @param  wxCode
    * @return WxFatDo
    **/
     WxFatDo getWxFatDo(String wxCode) ;
    /**
    * 授权回调
    * @param  params
    * @param  httpServletResponse
    * @return void
    **/
     void authorize(SyMap params, HttpServletResponse httpServletResponse) ;
    /**
    * 发起授权
    * @param  params
    * @param  httpServletResponse
    * @return void
    **/
     void auth2(SyMap params, HttpServletResponse httpServletResponse) ;



}
