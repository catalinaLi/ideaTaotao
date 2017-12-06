package top.catalinali.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.catalinali.common.pojo.TaotaoResult;
import top.catalinali.search.service.SearchItemService;

/**
 * <pre>
 * Description: 导入商品数据到索引库
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2017/12/6
 * </pre>
 */
@Controller
public class SearchItemController {
    @Autowired
    private SearchItemService searchItemService;

    @RequestMapping("/index/item/import")
    @ResponseBody
    public TaotaoResult importItemList() {
        TaotaoResult taotaoResult = searchItemService.importAllItems();
        return taotaoResult;

    }
}
