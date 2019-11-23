package com.shytong.modules.front.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.shytong.modules.article.dao.IArticleDao;
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
            System.out.println(sonId);
            List sonChannelList = this.getSonChannelList(sonId);
            if (sonChannelList != null && sonChannelList.size() > 0) {
                channelMap.put("children", sonChannelList);
            }
        });
        return channelListByParentChannelId;
    }


    /**
     * 设置顶部图片和标题
     *
     * @param model
     * @param channelId
     */
    public void setTopImgAndTitle(ModelMap model, Integer channelId) {
        // 栏目顶部图片
        model.addAttribute("channelTopImg", sysConfigDao.getSysConfig(channelId.toString(), "yc"));
        // 栏目标题
        model.addAttribute("channelTitle", channelDao.getChannelById(channelId));
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
        // 首页推荐文章配图
        model.addAttribute("recommendArticleImg", sysConfigDao.getSysConfig("newsRecommendImg", "yc"));
        // 首页推荐文章
        model.addAttribute("recommendArticle", articleDao.getRecommendArticle(17));
        // 首页新闻中心更多按钮
        model.addAttribute("newsMoreBtn", sysConfigDao.getSysConfig("newsMoreBtn", "yc"));
        // 首页新闻文章
        model.addAttribute("newsArticleList", this.getSonChannelList(15));
    }

    @Override
    public void setAboutContent(ModelMap model, Integer channelId) {
        this.setTopImgAndTitle(model, channelId);
        model.addAttribute("content", articleDao.getArticleByChannelId(channelId));
    }

    @Override
    public void setQualificationContent(ModelMap model, Integer channelId) {
        this.setTopImgAndTitle(model, channelId);

        List<Map> sonChannelList = this.getSonChannelList(channelId);
        int maxId = Integer.MAX_VALUE;
        if (sonChannelList != null && sonChannelList.size() > 0) {
            for (int i = 0; i < sonChannelList.size(); i++) {
                int cid = (int) sonChannelList.get(i).get("sort");
                // 越小的越大
                if (cid < maxId) {
                    maxId = cid;
                }
            }
        }
        // 获取栏目下的子栏目
        model.addAttribute("navList", sonChannelList);
        // 子栏目内容
        model.addAttribute("content", articleDao.getArticleByChannelId(maxId));
    }

}
