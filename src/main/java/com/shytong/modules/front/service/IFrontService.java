package com.shytong.modules.front.service;

import org.springframework.ui.ModelMap;

/**
 * @author CL
 */
public interface IFrontService {
    /**
     * 设置页面的头部和尾部
     * @param model
     */
    void setHtml(ModelMap model);
}
