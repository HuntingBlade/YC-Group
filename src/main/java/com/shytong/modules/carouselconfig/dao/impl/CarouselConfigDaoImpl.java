package com.shytong.modules.carouselconfig.dao.impl;

import com.github.pagehelper.PageInfo;
import com.shytong.common.model.SyMap;
import com.shytong.core.database.BaseDao;
import com.shytong.modules.carouselconfig.dao.ICarouselConfigDao;
import com.shytong.modules.carouselconfig.model.CarouselConfigDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Author CL
 * @Date 2019/11/13
 */
@Repository
public class CarouselConfigDaoImpl implements ICarouselConfigDao {

    @Autowired
    private BaseDao baseDao;

    @Override
    public Integer addCarouselConfig(CarouselConfigDo carouselConfigDo) {
        return baseDao.insert("CarouselConfigMapper.addCarouselConfig", carouselConfigDo);
    }

    @Override
    public Integer updateCarouselConfig(CarouselConfigDo carouselConfigDo) {
        return baseDao.update("CarouselConfigMapper.updateCarouselConfig", carouselConfigDo);
    }

    @Override
    public PageInfo selectCarouselConfig(int pageNum, int pageSize, SyMap params) {
        return baseDao.commlistOfPage("CarouselConfigMapper.selectCarouselConfig", pageNum, pageSize, params);
    }
}
