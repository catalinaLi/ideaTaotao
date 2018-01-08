package top.catalinali.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.catalinali.common.pojo.TaotaoResult;
import top.catalinali.common.util.CookieUtils;
import top.catalinali.sso.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * Description: 用户登录处理
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2017/12/29
 * </pre>
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Value("${TOKEN_KEY}")
    private String TOKEN_KEY;

    @RequestMapping(value="/user/login", method= RequestMethod.POST)
    @ResponseBody
    public TaotaoResult login(String username, String password,HttpServletRequest request, HttpServletResponse response) {
        TaotaoResult taotaoResult = loginService.userLogin(username, password);
        //判断是否登录成功
        if(taotaoResult.getStatus() == 200) {
            String token = taotaoResult.getData().toString();
            //如果登录成功需要把token写入cookie
            CookieUtils.setCookie(request, response, TOKEN_KEY, token);
        }
        //返回结果
        return taotaoResult;
    }

    @RequestMapping(value="/user/logout/{token}")
    @ResponseBody
    public Object logout(HttpServletRequest request, HttpServletResponse response, @PathVariable String token, String callback) {
        //删除cookie
        CookieUtils.deleteCookie(request,response,TOKEN_KEY);
        //删除redis
        TaotaoResult taotaoResult = loginService.logOut(token);
        //响应结果之前，判断是否为jsonp请求
        if (StringUtils.isNotBlank(callback)) {
            //把结果封装成一个js语句响应
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(taotaoResult);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }
        return TaotaoResult.build(400,"当前不是登录状态",null);
    }

    @RequestMapping("/page/login")
    public String showLogin(String redirect, Model model) {
        model.addAttribute("redirect", redirect);
        return "login";
    }

}
