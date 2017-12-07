package top.catalinali.search.service;


import top.catalinali.common.pojo.SearchResult;

/**
 * <pre>
 * Description: 搜索服务层
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2017/12/6
 * </pre>
 */
public interface SearchService {
    SearchResult search(String keyword, int page, int rows)  throws Exception;
}
