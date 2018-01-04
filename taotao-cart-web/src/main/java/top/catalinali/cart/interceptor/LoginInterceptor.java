package top.catalinali.cart.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.catalinali.common.pojo.TaotaoResult;
import top.catalinali.common.util.CookieUtils;
import top.catalinali.pojo.TbUser;
import top.catalinali.sso.service.TokenService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * Description: 用户登录处理拦截器
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/1/4
 * </pre>
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 前处理，执行handler之前执行此方法。
        //返回true，放行	false：拦截
        //1.从cookie中取token
        String token = CookieUtils.getCookieValue(request, "token");
        //2.如果没有token，未登录状态，直接放行
        if (StringUtils.isBlank(token)) {
            return true;
        }
        //3.取到token，需要调用sso系统的服务，根据token取用户信息
        TaotaoResult taotaoResult = tokenService.getUserByToken(token);
        //4.没有取到用户信息。登录过期，直接放行。
        if (taotaoResult.getStatus() != 200) {
            return true;
        }
        //5.取到用户信息。登录状态。
        TbUser user = (TbUser) taotaoResult.getData();
        //6.把用户信息放到request中。只需要在Controller中判断request中是否包含user信息。放行
        request.setAttribute("user", user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
