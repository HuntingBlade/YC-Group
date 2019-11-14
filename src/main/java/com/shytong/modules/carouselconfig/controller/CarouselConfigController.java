package com.shytong.modules.carouselconfig.controller;

import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.common.web.BaseController;
import com.shytong.core.util.SyValidationUtils;
import com.shytong.modules.carouselconfig.model.CarouselConfigDo;
import com.shytong.modules.carouselconfig.service.ICarouselConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * @Author CL
 * @Description 轮播配置控制类
 * @Date 2019/11/13
 */
@RestController
@RequestMapping(value = "/api/carousel/config", produces = "application/json;charset=utf-8", consumes = "application/json")
public class CarouselConfigController extends BaseController {

    @Autowired
    private ICarouselConfigService carouselConfigService;

    /**
     * 添加轮播配置项
     *
     * @param servletRequest
     * @param carouselConfigDo
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCarouselConfig(HttpServletRequest servletRequest, @RequestBody CarouselConfigDo carouselConfigDo) throws ApiBizException {
        SyValidationUtils.valid()
                .isInt(carouselConfigDo.getCarouselId(), 11, false, "所属轮播编号格式错误")
                .len(carouselConfigDo.getStyle(), 512, false, "轮播样式格式错误")
                .len(carouselConfigDo.getStartIndex(), 11, false, "轮播开始下标格式错误")
                .len(carouselConfigDo.getIntervalTime(), 255, false, "；轮播间隔时间格式错误")
                .len(carouselConfigDo.getCycleType(), 255, false, "轮播循环类型格式错误")
                .len(carouselConfigDo.getMaxShowCount(), 255, false, "轮播最大显示个数")
                .len(carouselConfigDo.getIsUp(), 11, false, "轮播配置是否启用");
        Integer result = carouselConfigService.addCarouselConfig(carouselConfigDo);
        return this.normalResp(result);
    }

    /**
     * 删除轮播配置项
     *
     * @param servletRequest
     * @param map
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/deleted", method = RequestMethod.GET, consumes = "*")
    public String deletedCarouselConfig(HttpServletRequest servletRequest, @RequestParam Map map) throws ApiBizException {
        SyMap params = new SyMap(map);
        SyValidationUtils.valid()
                .len(params.getString("id"), 32, true, "轮播配置编号格式错误");
        Integer result = carouselConfigService.deletedCarouselConfig(params);
        return this.normalResp(result);
    }

    /**
     * 更新轮播配置项
     *
     * @param servletRequest
     * @param carouselConfigDo
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateCarouselConfig(HttpServletRequest servletRequest, @RequestBody CarouselConfigDo carouselConfigDo) throws ApiBizException {
        SyValidationUtils.valid()
                .isInt(carouselConfigDo.getId(), 32, true, "轮播配置编号格式错误")
                .isInt(carouselConfigDo.getCarouselId(), 11, false, "所属轮播编号格式错误")
                .len(carouselConfigDo.getStyle(), 512, false, "轮播样式格式错误")
                .len(carouselConfigDo.getStartIndex(), 11, false, "轮播开始下标格式错误")
                .len(carouselConfigDo.getIntervalTime(), 255, false, "；轮播间隔时间格式错误")
                .len(carouselConfigDo.getCycleType(), 255, false, "轮播循环类型格式错误")
                .len(carouselConfigDo.getMaxShowCount(), 255, false, "轮播最大显示个数")
                .len(carouselConfigDo.getIsUp(), 11, false, "轮播配置是否启用");
        Integer result = carouselConfigService.updateCarouselConfig(carouselConfigDo);
        return this.normalResp(result);
    }

    /**
     * 获取轮播配置列表项
     *
     * @param servletRequest
     * @param map
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String selectCarouselConfig(HttpServletRequest servletRequest, @RequestBody Map map) throws ApiBizException {
        SyMap params = new SyMap(map);
        SyValidationUtils.valid()
                .isInt(params.getInteger("pageNum"), 11, true, "页码格式错误")
                .isInt(params.getString("pageSize"), 11, true, "每页显示大小格式错误");
        PageInfo result = carouselConfigService.selectCarouselConfig(params);
        return this.normalRespPage(result);
    }

}
