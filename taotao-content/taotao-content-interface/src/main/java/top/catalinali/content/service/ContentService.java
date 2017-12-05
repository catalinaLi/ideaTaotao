package top.catalinali.content.service;

import top.catalinali.common.pojo.TaotaoResult;
import top.catalinali.pojo.TbContent;

import java.util.List;

/**
 * <pre>
 * Description:
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2017/10/25
 * </pre>
 */
public interface ContentService {
    /**
     * 添加内容
     * @param content
     * @return
     */
    TaotaoResult addContent(TbContent content);

    /**
     * 根据id查询内容
     * @param cid
     * @return
     */
    List<TbContent> getContentListByCid(long cid);
}
