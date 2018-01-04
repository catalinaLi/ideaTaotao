package top.catalinali.cart.service;

import top.catalinali.common.pojo.TaotaoResult;
import top.catalinali.pojo.TbItem;

import java.util.List;

/**
 * <pre>
 * Description:
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/1/4
 * </pre>
 */
public interface CartService {
    TaotaoResult addCart(long userId, long itemId, int num);
    TaotaoResult mergeCart(long userId, List<TbItem> itemList);
    List<TbItem> getCartList(long userId);
    TaotaoResult updateCartNum(long userId, long itemId, int num);
    TaotaoResult deleteCartItem(long userId, long itemId);
}
