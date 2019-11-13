package com.shytong.config.app;

import com.shytong.modules.wx.service.IWxAuthDeal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author sytong
 * @Package com.shytong.config.app
 * @Description:
 * @date 2018-04-178:26
 */
@Configuration
public class WxAuthConfig {
    @Value("${authorizeErrUrl}")
    private String authorizeErrUrl;

    private IWxAuthDeal activityService;
//    @Bean
//    public WxManager wxManager(@Qualifier(value = "activityService") IWxAuthDeal activityService , @Qualifier(value = "userService") IWxAuthDeal userService) {
//        WxManager.ops.put("1",userService);
//        WxManager.ops.put("2",activityService);
//
//        return new WxManager();
//    }
}
