package com.shytong.modules.front.service;

import com.shytong.common.model.SyMap;
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
     *
     * @param model
     * @param channelId 栏目编号
     * @param pageNum   页码
     * @param pageSize  每页大小
     * @param group     分组
     */
    void setContent(ModelMap model, Integer channelId, Integer pageNum, Integer pageSize, String group);

    /**
     * 文章详情
     *
     * @param model
     * @param channelId
     * @param articleId
     */
    void getArticleDetail(ModelMap model, Integer channelId, String articleId);

    /**
     * 获取配置信息
     *
     * @param model
     * @param pageNum
     * @param pageSize
     */
    void getSysConfig(ModelMap model, Integer pageNum, Integer pageSize);

    /**
     * 显示一级栏目
     *
     * @param model
     * @param pageNum
     * @param pageSize
     */
    void getFirstChannel(ModelMap model, Integer pageNum, Integer pageSize);

    /**
     * 根据id获取配置信息
     *
     * @param model
     * @param id
     */
    void getSysConfigById(ModelMap model, String id);

    /**
     * 根据id获取栏目
     *
     * @param model
     * @param id
     */
    void getChannelAndSysConfigById(ModelMap model, Integer id);

    /**
     * 二级栏目
     *
     * @param model
     * @param params
     */
    void getSecondClass(ModelMap model, SyMap params);

    /**
     * 二级栏目添加信息
     *
     * @param model
     */
    void getAddSecondClassInfo(ModelMap model);

    void editSecondClass(ModelMap model, Integer id);

    void setOtherChannel(ModelMap model, Integer pageNum, Integer pageSize);

    void setEditOtherChannel(ModelMap model, Integer parentId);

    void setAdminIndex(ModelMap model);

    void setModules(ModelMap model, Integer pageNum, Integer pageSize, String id);

    void setAddArticleDefaultData(ModelMap model, String firstChannelId);
}
