package com.shytong.modules.front.service;

import org.springframework.ui.ModelMap;

import java.util.Map;

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
     */
    void setIndexContent(ModelMap model);

    /**
     * 设置文章内容
     * @param model
     * @param channelId 栏目编号
     * @param pageNum 页码
     * @param pageSize  每页大小
     * @param group 分组
     */
    void setContent(ModelMap model, Integer channelId, Integer pageNum, Integer pageSize, String group);





    /**
     * 设置资质荣誉内容
     *
     * @param model
     * @param channelId
     * @param pageNum
     */
    void setQualificationContent(ModelMap model, Integer channelId, Integer pageNum);

    /**
     * 设置工程业绩内容
     *
     * @param model
     * @param channelId
     * @param pageNum
     */
    void setProjectCaseContent(ModelMap model, Integer channelId, Integer pageNum);

    /**
     * 设置工程业绩内容
     *
     * @param model
     * @param channelId
     * @param pageNum
     */
    void setNewsCenterCaseContent(ModelMap model, Integer channelId, Integer pageNum);

    /**
     * 获取子栏目信息
     *
     * @param channelId
     * @param pageNum
     * @param pageSize
     * @return
     */
    Map getSonChannelInfo(Integer channelId, Integer pageNum, Integer pageSize);


    /**
     * 诚聘英才
     *
     * @param model
     * @param channelId
     * @param pageNum
     */
    void talentsWanted(ModelMap model, Integer channelId, Integer pageNum);

    /**
     * 联系我们
     *
     * @param model
     * @param channelId
     * @param pageNum
     */
    void contactUs(ModelMap model, Integer channelId, Integer pageNum);


}
