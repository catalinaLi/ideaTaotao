package top.catalinali.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.catalinali.common.pojo.EUDataGridResult;
import top.catalinali.common.pojo.TaotaoResult;
import top.catalinali.common.util.IDUtils;
import top.catalinali.mapper.TbItemDescMapper;
import top.catalinali.mapper.TbItemMapper;
import top.catalinali.mapper.TbItemParamItemMapper;
import top.catalinali.pojo.TbItem;
import top.catalinali.pojo.TbItemDesc;
import top.catalinali.pojo.TbItemExample;
import top.catalinali.pojo.TbItemParamItem;
import top.catalinali.service.ItemService;

import java.util.Date;
import java.util.List;

/**
 * Created by lllx on 2017/8/18.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Override
    public EUDataGridResult getItemList(Integer page, Integer rows) {
        //查询商品列表
        TbItemExample example = new TbItemExample();
        //分页处理
        PageHelper.startPage(page, rows);
        List<TbItem> list = itemMapper.selectByExample(example);
        //返回一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public TaotaoResult createItem(TbItem item, String desc, String itemParam) throws Exception {
        //item补全，生成商品ID
        Long itemId = IDUtils.genItemId();
        item.setId(itemId);
        //商品状态1正常2下架3删除
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        itemMapper.insert(item);

        TaotaoResult result = insertItemDesc(itemId, desc);

        if(result.getStatus() != 200){
            throw new Exception();
        }
        //添加规格参数
        result = insertItemParamItem(itemId, itemParam);
        if(result.getStatus() != 200){
            throw new Exception();
        }

        return TaotaoResult.ok();
    }

    //添加商品描述
    private TaotaoResult insertItemDesc(Long itemId,String desc){
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());

        itemDescMapper.insert(itemDesc);
        return TaotaoResult.ok();
    }

    //添加参数规格
    private TaotaoResult insertItemParamItem(Long ItemId,String itemParam){
        //创建一个pojo
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(ItemId);
        itemParamItem.setParamData(itemParam);
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(new Date());
        itemParamItemMapper.insert(itemParamItem);

        return TaotaoResult.ok();
    }
}
