package com.shytong.modules.front.service;

import org.springframework.ui.ModelMap;

import java.util.List;

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
     * 设置首页页面的内容
     *
     * @param model
     * @param channelId
     */
    void setIndexContent(ModelMap model, Integer channelId);

    /**
     * 设置集团简介内容
     *
     * @param model
     * @param channelId
     */
    void setAboutContent(ModelMap model, Integer channelId);

    /**
     * 设置资质荣誉内容
     *
     * @param model
     * @param channelId
     * @param pageNum
     */
    void setQualificationContent(ModelMap model, Integer channelId, Integer pageNum);

    /**
     * 获取子栏目信息
     * @param channelId
     * @param pageNum
     * @param pageSize
     * @return
     */
    List getSonChannelInfo(Integer channelId, Integer pageNum, Integer pageSize);
}
