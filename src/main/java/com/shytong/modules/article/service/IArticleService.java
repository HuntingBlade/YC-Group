package com.shytong.modules.article.service;

import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.modules.article.model.ArticleDo;

import java.util.List;

/**
 * @Author: CL
 * @Date: 2019/11/12 19:39
 */
public interface IArticleService {

    /**
     * 添加文章
     *
     * @param articleDo
     * @return
     * @throws ApiBizException
     */
    String insertArticle(ArticleDo articleDo) throws ApiBizException;

    /**
     * 删除文章(批量删除)
     *
     * @param ids
     * @return
     * @throws ApiBizException
     */
    String deletedArticle(String[] ids) throws ApiBizException;

    /**
     * 修改文章
     *
     * @param articleDo
     * @return
     * @throws ApiBizException
     */
    String updateArticle(ArticleDo articleDo) throws ApiBizException;

    /**
     * 获取文章列表
     *
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     * @throws ApiBizException
     */
    PageInfo selectArticleList(SyMap params, Integer pageNum, Integer pageSize) throws ApiBizException;
}
