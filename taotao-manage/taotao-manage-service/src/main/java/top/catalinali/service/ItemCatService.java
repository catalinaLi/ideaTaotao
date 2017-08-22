package top.catalinali.service;

import top.catalinali.common.pojo.EUTreeNode;

import java.util.List;

/**
 * Created by TDH on 2017/8/21.
 */
public interface ItemCatService {
    List<EUTreeNode> getItemCatList(long parentId);
}
