package top.catalinali.order.service;

import top.catalinali.common.pojo.TaotaoResult;
import top.catalinali.order.pojo.OrderInfo;

/**
 * <pre>
 * Description:
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/1/5
 * </pre>
 */
public interface OrderService {
    TaotaoResult createOrder(OrderInfo orderInfo);
}
