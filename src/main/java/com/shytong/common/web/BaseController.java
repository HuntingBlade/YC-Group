package com.shytong.common.web;

import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.resultcode.ResultCodeInfo;
import com.shytong.common.service.validator.IValidatorService;
import com.shytong.core.util.SyJsonUtil;

import javax.annotation.Resource;

/**
 * @author sytong
 * @Package com.shytong.common
 * @Description:
 * @date 2018-01-0417:50
 */
public class BaseController {
    @Resource(name = "validatorService")
    public IValidatorService validatorService;

    public String normalResp() {
        return SyResponse.normalResp();
    }

    public String normalResp(Object entity) {
        return SyResponse.normalResp(entity);
    }

    public String normalResp(int errCode, String errInfo) {
        return SyResponse.normalResp(errCode, errInfo);
    }

    public String normalResp(String errCode) {
        return SyResponse.normalResp(errCode);
    }

    public String normalRespPage(Object pageEntity, int pageNum, int pageSize, long total) {
        return SyResponse.normalRespPage(pageEntity, pageNum, pageSize, total);
    }

    public String normalRespPage(PageInfo pageInfo) {

        return SyResponse.normalRespPage(pageInfo.getList(), pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal());

    }

//    /**
//     * jpa查询List分页显示
//     * @param page
//     * @return
//     */
//    public String normalRespPage(Page page){
//        return  SyResponse.normalRespPage(page.getContent(),page.getNumber()+1,page.getSize(),page.getTotalElements());
//    }

    public String normalRespPage(Object pageEntity) {

        return SyResponse.normalRespPage(pageEntity);

    }

    public void checkNull(String value) throws ApiBizException {

        if (value != null) {
            throw new ApiBizException(-1, value);
        }


    }


}
