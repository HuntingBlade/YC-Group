package com.shytong.modules.front.service;

import org.springframework.ui.ModelMap;

/**
 * @Author:CL
 * @Date:2019/11/14
 */
public interface IFrontService {
    /**
     * 设置页面的头部和尾部
     *
     * @param model
     */
    void setHtml(ModelMap model);

    /**
     * 设置页面的内容
     *
     * @param model
     */
    void setIndexContent(ModelMap model, Integer channelId);
}
