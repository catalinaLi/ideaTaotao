package top.catalinali.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @RequestMapping("/page/register")
    public String showRegister() {
        return "register";
    }
}
