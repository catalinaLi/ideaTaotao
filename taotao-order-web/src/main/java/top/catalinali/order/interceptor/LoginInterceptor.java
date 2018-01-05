package top.catalinali.order.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.catalinali.cart.service.CartService;
import top.catalinali.common.pojo.TaotaoResult;
import top.catalinali.common.util.CookieUtils;
import top.catalinali.common.util.JsonUtils;
import top.catalinali.pojo.TbItem;
import top.catalinali.pojo.TbUser;
import top.catalinali.sso.service.TokenService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * Description: 用户登录拦截器
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/1/5
 * </pre>
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Value("${SSO_URL}")
    private String SSO_URL;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private CartService cartService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //从cookie中取token
        String token = CookieUtils.getCookieValue(request, "token");
        //判断token是否存在
        if (StringUtils.isBlank(token)) {
            //如果token不存在，未登录状态，跳转到sso系统的登录页面。用户登录成功后，跳转到当前请求的url
            response.sendRedirect(SSO_URL + "/page/login?redirect=" + request.getRequestURL());
            //拦截
            return false;
        }
        //如果token存在，需要调用sso系统的服务，根据token取用户信息
        TaotaoResult taotaoResult = tokenService.getUserByToken(token);
        //如果取不到，用户登录已经过期，需要登录。
        if (taotaoResult.getStatus() != 200) {
            //如果token不存在，未登录状态，跳转到sso系统的登录页面。用户登录成功后，跳转到当前请求的url
            response.sendRedirect(SSO_URL + "/page/login?redirect=" + request.getRequestURL());
            //拦截
            return false;
        }
        //如果取到用户信息，是登录状态，需要把用户信息写入request。
        TbUser user = (TbUser) taotaoResult.getData();
        request.setAttribute("user", user);
        //判断cookie中是否有购物车数据，如果有就合并到服务端。
        String jsonCartList = CookieUtils.getCookieValue(request, "cart", true);
        if (StringUtils.isNoneBlank(jsonCartList)) {
            //合并购物车
            cartService.mergeCart(user.getId(), JsonUtils.jsonToList(jsonCartList, TbItem.class));
        }
        //放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
