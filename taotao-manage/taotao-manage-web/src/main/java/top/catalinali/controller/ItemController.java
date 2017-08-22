package top.catalinali.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.catalinali.common.pojo.EUDataGridResult;
import top.catalinali.service.ItemService;

/**
 * Created by TDH on 2017/8/18.
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getItemList(@RequestParam(defaultValue="1")Integer page,
                                        @RequestParam(defaultValue="30")Integer rows){
        EUDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }

}
