package top.catalinali.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.catalinali.common.pojo.TaotaoResult;
import top.catalinali.content.service.ContentService;
import top.catalinali.pojo.TbContent;

/**
 * <pre>
 * Description: 内容管理Controller
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2017/10/25
 * </pre>
 */
@Controller
public class ContentController   {

    @Autowired
    private ContentService contentService;

    @RequestMapping(value="/content/save", method= RequestMethod.POST)
    @ResponseBody
    public TaotaoResult addContent(TbContent content) {
        //调用服务把内容数据保存到数据库
        TaotaoResult taotaoResult = contentService.addContent(content);
        return taotaoResult;
    }

}
