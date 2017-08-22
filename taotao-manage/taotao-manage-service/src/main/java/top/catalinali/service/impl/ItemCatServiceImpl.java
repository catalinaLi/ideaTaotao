package top.catalinali.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.catalinali.common.pojo.EUTreeNode;
import top.catalinali.mapper.TbItemCatMapper;
import top.catalinali.pojo.TbItemCat;
import top.catalinali.pojo.TbItemCatExample;
import top.catalinali.service.ItemCatService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TDH on 2017/8/21.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<EUTreeNode> getItemCatList(long parentId) {
        //根据parentId查询分类列表
        TbItemCatExample example = new TbItemCatExample();
        //设置查询条件
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);

        List<TbItemCat> tbItemCats = itemCatMapper.selectByExample(example);
        //分类列表转换成TreeNode的列表
        ArrayList<EUTreeNode> resultList = new ArrayList<>();
        for (TbItemCat tbItemCat : tbItemCats) {
            //创建一个TreeNode对象
            EUTreeNode node = new EUTreeNode(tbItemCat.getId(), tbItemCat.getName(),
                    tbItemCat.getIsParent()?"closed":"open");
            resultList.add(node);
        }

        return resultList;
    }
}
