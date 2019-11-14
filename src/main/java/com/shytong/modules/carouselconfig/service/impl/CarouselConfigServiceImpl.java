package com.shytong.modules.carouselconfig.service.impl;

import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.core.util.SyIdUtils;
import com.shytong.modules.carouselconfig.dao.ICarouselConfigDao;
import com.shytong.modules.carouselconfig.model.CarouselConfigDo;
import com.shytong.modules.carouselconfig.service.ICarouselConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Author CL
 * @Date 2019/11/13
 */
@Service
public class CarouselConfigServiceImpl implements ICarouselConfigService {

    @Autowired
    private ICarouselConfigDao carouselConfigDao;

    @Override
    public Integer addCarouselConfig(CarouselConfigDo carouselConfigDo) throws ApiBizException {
        if (carouselConfigDo == null) {
            throw new ApiBizException(-1, "参数错误");
        }
        String id = String.valueOf(SyIdUtils.nextId());
        carouselConfigDo.setId(id);
        Integer result = carouselConfigDao.addCarouselConfig(carouselConfigDo);
        if (result < 0) {
            throw new RuntimeException();
        }
        return 1;
    }

    @Override
    public Integer deletedCarouselConfig(SyMap params) throws ApiBizException {
        String id = params.getString("id");
        if (id.isEmpty()) {
            throw new ApiBizException(-1, "参数错误");
        }
        CarouselConfigDo carouselConfigDo = new CarouselConfigDo();
        carouselConfigDo.setId(id);
        carouselConfigDo.setIsDeleted(1);
        Integer result = carouselConfigDao.updateCarouselConfig(carouselConfigDo);
        if (result < 0) {
            throw new RuntimeException();
        }
        return 1;
    }

    @Override
    public Integer updateCarouselConfig(CarouselConfigDo carouselConfigDo) throws ApiBizException {
        if (carouselConfigDo == null) {
            throw new ApiBizException(-1, "参数错误");
        }
        Integer result = carouselConfigDao.updateCarouselConfig(carouselConfigDo);
        if (result < 0) {
            throw new RuntimeException();
        }
        return 1;
    }

    @Override
    public PageInfo selectCarouselConfig(SyMap params) throws ApiBizException {
        int pageNum = params.getInteger("pageNum");
        int pageSize = params.getInteger("pageSize");
        if (pageNum <= 0 || pageSize <= 0) {
            throw new ApiBizException(-1, "参数错误");
        }
        PageInfo result = carouselConfigDao.selectCarouselConfig(pageNum, pageSize, params);
        return result;
    }
}
