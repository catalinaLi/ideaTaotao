package top.catalinali.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.catalinali.common.pojo.EUTreeNode;
import top.catalinali.service.ItemCatService;

import java.util.List;

/**
 * Created by TDH on 2017/8/21.
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @ResponseBody
    @RequestMapping("/list")
    public List<EUTreeNode> getItemCatList(@RequestParam(value="id",defaultValue="0")Long parentId){
        List<EUTreeNode> list = itemCatService.getItemCatList(parentId);
        return list;
    }
}
