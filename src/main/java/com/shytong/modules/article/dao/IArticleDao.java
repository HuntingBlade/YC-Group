package com.shytong.modules.article.dao;

import com.github.pagehelper.PageInfo;
import com.shytong.common.model.SyMap;
import com.shytong.modules.article.model.ArticleDo;

import java.util.List;

/**
 * @Author: CL
 * @Date: 2019/11/12 19:41
 */
public interface IArticleDao {

    /**
     * 添加文章
     *
     * @param articleDo
     * @return
     */
    Integer insertArticle(ArticleDo articleDo);

    /**
     * 删除文章(批量删除)
     *
     * @param list
     * @return
     */
    Integer deletedArticle(List list);

    /**
     * 修改文章
     *
     * @param articleDo
     * @return
     */
    Integer updateArticle(ArticleDo articleDo);

    /**
     * 获取文章列表
     *
     * @param pageNum
     * @param pageSize
     * @param params
     * @return
     */
    PageInfo<ArticleDo> selectArticleList(Integer pageNum, Integer pageSize, SyMap params);

    /**
     * 根据栏目编号查找文章
     *
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo getArticleByChannelId(SyMap params, Integer pageNum, Integer pageSize);

    /**
     * 获取推荐文章
     *
     * @param channelId
     * @return
     */
    Object getRecommendArticle(Integer channelId);

    /**
     * 根据id获取文章信息
     *
     * @param articleId
     * @return
     */
    Object getArticleInfoById(String articleId);

    /**
     * 根据栏目id获取文章信息和栏目信息
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo getArticleAndChannelInfoByChannelId(SyMap params, Integer pageNum, Integer pageSize);
}
