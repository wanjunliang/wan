package com.neusoft.smis.dao.mysql.tdzyf;

import com.neusoft.smis.common.entity.mysql.TdzyfSifaPaimai;
import com.neusoft.smis.common.entity.mysql.TdzyfSifaPaimaiImg;
import com.neusoft.smis.common.entity.mysql.weixin.WeiXinYuYue;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/21.
 */
//关于mysql数据库的表tdzyf_sifa_paimai表的操作
public interface TdzyfSifaPaimaiMapper {
    //查询tdzyf_sifa_paimai通过house_name模糊查询
    List<TdzyfSifaPaimai> selectTdzyfSifaPaimaiByHouseName(TdzyfSifaPaimai tdzyfSifaPaimai);
    //查询数据通过挂网时间的一个时间段进行查询
    List<TdzyfSifaPaimai> selectTdzyfSifaPaimaiByhouse_proclaimtion_time(Map map);
    //查询tdzyf_sifa_paimai的数据通过经纬度的限制上下左右的边界进行查询,并且查询还没有结束的
    List<TdzyfSifaPaimai> selectTdzyfSifaPaimaiByLngLat(Map map);
    //提供给微信端的查询
    List<TdzyfSifaPaimai> selectTdzyfSifaPaimaiInWeiXin(Map map);
    //提供给微信端的查询我的预约了的司法司法拍卖的数据
    List<TdzyfSifaPaimai> selectMyYuyueTdzyfSifaPaimaiDataList(WeiXinYuYue weiXinYuYue);
    //提供给商业地图的数据接口
    List<TdzyfSifaPaimai> selectTdzyfSifaPaimaiBusiness(Map map);
    //查询表tdzyf_sifa_paimai_img中的图片们,通过传入url
    List<TdzyfSifaPaimaiImg> selectTdzyfSifaPaimaiImagesByUrl(Map map);
}
