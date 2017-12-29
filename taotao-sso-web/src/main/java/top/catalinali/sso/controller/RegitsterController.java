package top.catalinali.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.catalinali.common.pojo.TaotaoResult;
import top.catalinali.pojo.TbUser;
import top.catalinali.sso.service.RegisterService;

/**
 * <pre>
 * Description: 注册功能Controller
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2017/12/28
 * </pre>
 */
@Controller
public class RegitsterController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping("/page/register")
    public String showRegister() {
        return "register";
    }

    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public TaotaoResult checkData(@PathVariable String param, @PathVariable Integer type) {
        TaotaoResult taotaoResult = registerService.checkData(param, type);
        return taotaoResult;
    }

    @RequestMapping(value="/user/register", method= RequestMethod.POST)
    @ResponseBody
    public TaotaoResult register(TbUser user) {
        TaotaoResult taotaoResult = registerService.register(user);
        return taotaoResult;
    }
}
