package top.catalinali.sso.service;

import top.catalinali.common.pojo.TaotaoResult;

/**
 * <pre>
 * Description: 根据token查询用户信息
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/1/3
 * </pre>
 */
public interface TokenService {
    TaotaoResult getUserByToken(String token);
}
