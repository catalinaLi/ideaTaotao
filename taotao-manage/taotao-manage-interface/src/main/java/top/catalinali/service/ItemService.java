package top.catalinali.service;

import top.catalinali.common.pojo.EUDataGridResult;
import top.catalinali.common.pojo.TaotaoResult;
import top.catalinali.pojo.TbItem;

/**
 * Created by TDH on 2017/8/18.
 */
public interface ItemService {
    EUDataGridResult getItemList(Integer page, Integer rows);

    TaotaoResult createItem(TbItem item, String desc, String itemParam) throws Exception;

    TbItem getItemById(long itemId);
}
