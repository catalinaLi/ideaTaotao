package top.catalinali.sso.service;

import top.catalinali.common.pojo.TaotaoResult;
import top.catalinali.pojo.TbUser;

public interface RegisterService {

    TaotaoResult checkData(String param, int type);
    TaotaoResult register(TbUser user);
}
