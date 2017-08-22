package top.catalinali.service;

import top.catalinali.common.pojo.EUDataGridResult;

/**
 * Created by TDH on 2017/8/18.
 */
public interface ItemService {
    EUDataGridResult getItemList(Integer page, Integer rows);
}
