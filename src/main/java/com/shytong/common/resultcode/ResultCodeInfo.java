package com.shytong.common.resultcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: CL
 * @Date: 2019/11/29 21:29
 */
public class ResultCodeInfo {
    private static final Map<String, String> resultMap;

    static {
        resultMap = new HashMap<>(16);
        resultMap.put("1", "SUCCESS");
        resultMap.put("20001", "用户不存在");
        resultMap.put("20002", "用户未登陆");
        resultMap.put("20003", "用户名或密码错误");
        resultMap.put("20004", "用户账户已被禁用");
        resultMap.put("20005", "用户已存在");
        resultMap.put("21001", "验证码错误");
        resultMap.put("40001", "参数错误");
    }

    public static String getCodeInfoByErrCode(String errCode) {
        if (errCode.isEmpty()) {
            return "";
        }
        return resultMap.get(errCode);
    }
}
