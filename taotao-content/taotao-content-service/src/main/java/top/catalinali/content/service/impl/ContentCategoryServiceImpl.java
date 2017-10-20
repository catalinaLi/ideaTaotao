package top.catalinali.content.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.catalinali.common.pojo.EUTreeNode;
import top.catalinali.mapper.TbContentCategoryMapper;
import top.catalinali.pojo.TbContentCategory;
import top.catalinali.pojo.TbContentCategoryExample;
import top.catalinali.content.service.ContentCategoryService;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Description:
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2017/10/19
 * </pre>
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EUTreeNode> getContentCatList(long parentId) {
        // 根据parentid查询子节点列表
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbContentCategory> catList = contentCategoryMapper.selectByExample(example);
        //转换成EasyUITreeNode的列表
        List<EUTreeNode> nodeList = new ArrayList<>();
        for (TbContentCategory tbContentCategory : catList) {
            EUTreeNode node = new EUTreeNode();
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            node.setState(tbContentCategory.getIsParent()?"closed":"open");
            //添加到列表
            nodeList.add(node);
        }
        return nodeList;
    }
}
