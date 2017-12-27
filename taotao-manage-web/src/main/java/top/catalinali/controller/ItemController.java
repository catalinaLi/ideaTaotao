package top.catalinali.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.catalinali.common.pojo.EUDataGridResult;
import top.catalinali.common.pojo.TaotaoResult;
import top.catalinali.pojo.TbItem;
import top.catalinali.service.ItemService;

/**
 * Created by lllx on 2017/8/18.
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getItemList(@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="30")Integer rows){
        EUDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }

    @RequestMapping(value="/save",method= RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createItem(TbItem item, String desc, String itemParams) throws Exception{
        TaotaoResult result = itemService.createItem(item,desc,itemParams);
        return result;
    }

    @RequestMapping("/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId) {
        TbItem tbItem = itemService.getItemById(itemId);
        return tbItem;
    }
}
