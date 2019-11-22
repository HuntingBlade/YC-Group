package com.shytong.modules.front.service.impl;

import com.alibaba.fastjson.JSON;
import com.shytong.common.model.SyMap;
import com.shytong.core.util.SyMapUtils;
import com.shytong.modules.article.dao.IArticleDao;
import com.shytong.modules.article.model.ArticleDo;
import com.shytong.modules.channel.dao.IChannelDao;
import com.shytong.modules.channel.dao.impl.ChannelDao;
import com.shytong.modules.channel.model.ChannelDo;
import com.shytong.modules.front.service.IFrontService;
import com.shytong.modules.sysconfig.dao.ISysConfigDao;
import com.shytong.modules.urlconfig.dao.IUrlConfigDao;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: CL
 * @time: 2019/11/11 14:34
 */
@Service
public class FrontServiceImpl implements IFrontService {

    @Autowired
    private ISysConfigDao sysConfigDao;
    @Autowired
    private IChannelDao channelDao;
    @Autowired
    private IUrlConfigDao urlConfigDao;
    @Autowired
    private IArticleDao articleDao;

    /**
     * 根据分组获取链接配置列表项
     *
     * @param urlGroup
     * @return
     */
    public List getUrlConfigListByGroup(String urlGroup) {
        return urlConfigDao.getUrlConfigListByGroup(urlGroup);
    }

    /**
     * 栏目是否存在
     *
     * @param channelId
     * @return
     */
    public Integer channelIsExist(Integer channelId) {
        if (channelId == null) {
            return 1;
        }
        return channelId;
    }

    /**
     * 获取首页文章
     *
     * @param channelId
     * @return
     */
    public Object getIndexArticle(Integer channelId) {
        List articleList = articleDao.getArticleByChannelId(channelId);
        Object obj = null;
        for (int i = 0; i < 1; i++) {
            obj = articleList.get(i);
        }
        return obj;
    }

    @Override
    public void setHtml(ModelMap model) {
        // logo图片
        model.addAttribute("logoImg", sysConfigDao.getSysConfig("logo", "yc"));
        // 底部logo
        model.addAttribute("footLogoImg", sysConfigDao.getSysConfig("footLogo", "yc"));
        // 栏目
        model.addAttribute("channelList", channelDao.getChannelListByPId(0));
        // 底部下拉框一
        model.addAttribute("footInputOne", this.getUrlConfigListByGroup("1"));
        // 底部下拉框二
        model.addAttribute("footInputTwo", this.getUrlConfigListByGroup("2"));
        // 底部下拉框三
        model.addAttribute("footInputThree", this.getUrlConfigListByGroup("3"));
        // 版权
        model.addAttribute("copyright", sysConfigDao.getSysConfig("copyright", "yc"));
        // ICP
        model.addAttribute("footICP", sysConfigDao.getSysConfig("footICP", "yc"));
        // 公司
        model.addAttribute("company", sysConfigDao.getSysConfig("company", "yc"));
    }

    @Override
    public void setIndexContent(ModelMap model, Integer channelId) {
        channelId = this.channelIsExist(channelId);
        // 轮播
        model.addAttribute("carouselList", sysConfigDao.getSysConfigList("1", "yc"));
        // 首页文章
        model.addAttribute("articleList", this.getIndexArticle(channelId));
        // 签字
        model.addAttribute("signature", sysConfigDao.getSysConfig("signature", "yc"));
        // 模块
        model.addAttribute("indexContentModel", this.getSonChannelList(channelId));
        // 首页推荐文章
        model.addAttribute("recommendArticle", articleDao.getRecommendArticle(17));
        // 首页推荐文章配图
        model.addAttribute("recommendArticleImg", sysConfigDao.getSysConfig("newsRecommendImg", "yc"));
        // 首页新闻文章
        model.addAttribute("newsArticleList", this.getSonChannelList(15));
    }


    /**
     * 递归查找子栏目编号
     *
     * @param pId
     * @return
     */
    public List getSonChannelList(Integer pId) {
        List<Map> channelListByParentChannelId = channelDao.getChannelListByParentChannelId(pId);
        channelListByParentChannelId.forEach(channelMap -> {
            Integer sonId = MapUtils.getInteger(channelMap, "id", null);
            List sonChannelList = this.getSonChannelList(sonId);
            if (sonChannelList != null && sonChannelList.size() > 0) {
                channelMap.put("children", sonChannelList);
            }
        });
        return channelListByParentChannelId;
    }

}
