package com.shytong.common.web;

import com.shytong.common.resultcode.ResultDo;
import com.shytong.core.util.SyJsonUtil;

/**
 * @author sytong
 * @Package com.shytong.common
 * @Description:
 * @date 2018-01-0417:59
 */
public class SyResponse {

    static String DEFAULT_RESPONSE;

    static {

        DEFAULT_RESPONSE = SyJsonUtil.Object2Json(new ResEntity());

    }

    public static String normalResp() {
        return DEFAULT_RESPONSE;
    }

    public static String normalResp(Object entity) {
        return SyJsonUtil.Object2Json(new ResEntity(entity));
    }

    public static String normalResp(int errCode, String errInfo) {
        return SyJsonUtil.Object2Json(new ResEntity(errCode, errInfo));
    }

    public static String normalResp(String errCode) {
        return SyJsonUtil.Object2Json(new ResultDo(errCode));
    }

    public static String normalRespPage(Object pageEntity, int pageNum, int pageSize, long total) {

        return SyJsonUtil.Object2Json(new ResPageEntity(pageEntity, total));

    }

    public static String normalRespPage(Object pageEntity) {

        return SyJsonUtil.Object2Json(new ResPageEntity(pageEntity));

    }
}
