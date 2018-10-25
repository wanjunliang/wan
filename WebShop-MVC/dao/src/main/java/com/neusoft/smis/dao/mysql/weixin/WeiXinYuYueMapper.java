package com.neusoft.smis.dao.mysql.weixin;

import com.neusoft.smis.common.entity.mysql.weixin.WeiXinYuYue;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/12.
 */
public interface WeiXinYuYueMapper {
    //新增一条来自微信端的预约数据
    int insertYuYue(WeiXinYuYue weiXinYuYue);
    //查询微信预约表的的数据通过各种组合条件
    List<WeiXinYuYue> selectWeiXinYuYueList(Map map);
}
