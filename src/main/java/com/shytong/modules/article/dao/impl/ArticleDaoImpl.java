package com.shytong.modules.article.dao.impl;

import com.github.pagehelper.PageInfo;
import com.shytong.common.model.SyMap;
import com.shytong.core.database.BaseDao;
import com.shytong.modules.article.dao.IArticleDao;
import com.shytong.modules.article.model.ArticleDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: CL
 * @Date: 2019/11/12 19:41
 */
@Repository
public class ArticleDaoImpl implements IArticleDao {

    @Autowired
    private BaseDao baseDao;

    @Override
    public Integer insertArticle(ArticleDo articleDo) {
        return baseDao.insert("ArticleMapper.insert", articleDo);
    }

    @Override
    public Integer deletedArticle(List list) {
        return baseDao.delete("ArticleMapper.deleted", list);
    }

    @Override
    public Integer updateArticle(ArticleDo articleDo) {
        return baseDao.update("ArticleMapper.update", articleDo);
    }

    @Override
    public PageInfo<ArticleDo> selectArticleList(Integer pageNum, Integer pageSize, SyMap params) {
        return baseDao.commlistOfPage("ArticleMapper.select", pageNum, pageSize, params);
    }
}
