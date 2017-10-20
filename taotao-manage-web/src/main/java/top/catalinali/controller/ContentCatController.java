package top.catalinali.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.catalinali.common.pojo.EUTreeNode;
import top.catalinali.content.service.ContentCategoryService;

import java.util.List;

/**
 * <pre>
 * Description: 内容分类管理Controller
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2017/10/19
 * </pre>
 */
@Controller
@RequestMapping("content/category")
public class ContentCatController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @ResponseBody
    @RequestMapping("list")
    public List<EUTreeNode> getContentCatList(@RequestParam(name="id", defaultValue="0")Long parentId) {
        List<EUTreeNode> list = contentCategoryService.getContentCatList(parentId);
        return list;
    }

}
