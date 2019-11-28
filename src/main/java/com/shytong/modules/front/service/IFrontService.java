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
     * 文章详情
     * @param model
     * @param channelId
     * @param articleId
     */
    void getArticleDetail(ModelMap model, Integer channelId, String articleId);
}
