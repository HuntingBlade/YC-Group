package com.shytong.modules.wx.service;

import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.modules.wx.model.WxMpAuth2AccessToken;
import com.shytong.modules.wx.model.WxMpUser;

import javax.servlet.http.HttpServletResponse;

/**
 * @author sytong
 * @Package com.shytong.modules.wx.service
 * @Description:
 * @date 2018-04-178:18
 */
public interface IWxAuthDeal {

    public void dealBaseInfo(SyMap params, WxMpAuth2AccessToken wxMpAuth2AccessToken, HttpServletResponse httpServletResponse);
    public void dealUserInfo(SyMap params, WxMpUser wxMpUser, HttpServletResponse httpServletResponse) throws ApiBizException;
}
