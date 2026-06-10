package com.xxx.crm.interceptor;

import com.xxx.crm.exceptions.NoLoginException;
import com.xxx.crm.service.UserService;
import com.xxx.crm.utils.CookieUtil;
import com.xxx.crm.utils.UserIDBase64;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoLoginInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        // 1. 获取 Cookie 中的用户 ID
        String userIdStr = CookieUtil.getCookieValue(request, "userIdStr");
        
        // 2. 判断是否有 Cookie
        if (null == userIdStr || userIdStr.isEmpty()) {
            System.out.println("拦截器提示：未检测到 userIdStr Cookie，正在重定向到登录页...");
            throw new NoLoginException();
        }
        
        // 3. 解析 ID 并校验用户是否存在
        Integer userId = UserIDBase64.decoderUserID(userIdStr);
        if (null == userId || null == userService.selectByPrimaryKey(userId)) {
            System.out.println("拦截器提示：Cookie 无效或用户不存在，正在重定向到登录页...");
            throw new NoLoginException();
        }
        
        return true;
    }
}
