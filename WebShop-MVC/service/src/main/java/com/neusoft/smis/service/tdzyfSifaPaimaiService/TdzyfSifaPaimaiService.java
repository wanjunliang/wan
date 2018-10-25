package com.neusoft.smis.service.tdzyfSifaPaimaiService;

import com.neusoft.smis.common.entity.mysql.TdzyfSifaPaimai;
import com.neusoft.smis.common.entity.mysql.TdzyfSifaPaimaiImg;
import com.neusoft.smis.common.entity.mysql.weixin.WeiXinYuYue;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/21.
 */
public interface TdzyfSifaPaimaiService {
    //查询tdzyf_sifa_paimai通过house_name模糊查询
    List<TdzyfSifaPaimai> selectTdzyfSifaPaimaiByHouseName(TdzyfSifaPaimai tdzyfSifaPaimai);
    //查询数据通过挂网的时间区间进行查询
    List<TdzyfSifaPaimai> selectTdzyfSifaPaimaiByhouse_proclaimtion_time(String time_1,String time_2,String[] house_type_list);
    //查询tdzyf_sifa_paimai的数据通过经纬度的限制上下左右的边界进行查询,并且查询还没有结束的
    List<TdzyfSifaPaimai> selectTdzyfSifaPaimaiByLngLat(Map map);
    //提供给微信端的查询
    List<TdzyfSifaPaimai> selectTdzyfSifaPaimaiInWeiXin(String time_1,String time_2,String[] house_type_list,String search_input,String auction_end_time_1,String auction_end_time_2,String limit_start,String limit_size,String[] house_region_list,String[] auction_price_list,String[] area_list);
    //提供给微信端的查询我的预约的司法拍卖的数据
    List<TdzyfSifaPaimai> selectMyYuyueTdzyfSifaPaimaiDataList(WeiXinYuYue weiXinYuYue);
    //提供给地图的商业地图
    List<TdzyfSifaPaimai> selectTdzyfSifaPaimaiBusiness(Map map);
    //查询表tdzyf_sifa_paimai_img中的图片们,通过传入url
    List<TdzyfSifaPaimaiImg> selectTdzyfSifaPaimaiImagesByUrl(String url);
}
