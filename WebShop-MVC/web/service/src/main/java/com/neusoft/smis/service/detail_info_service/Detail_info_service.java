package com.neusoft.smis.service.detail_info_service;

import com.neusoft.smis.common.entity.mongodb.Detail_info;

import java.util.List;
import java.util.Map;

/**
 * Created by WanJunliang on 2017/5/16.
 */

public interface Detail_info_service {
    //查询全部
    List<Detail_info> findAll();
    void insert(Detail_info detail_info);
    //条件查询
    List<Detail_info> find(Detail_info detail_info,String skip, String limit,String house_price_min,String house_price_max,String house_start_time_min,String house_start_time_max);
    //多条件查询 2号
    List<Detail_info> find2(Detail_info detail_info,int skip, int limit
                            ,String bargained_time_start,String bargained_time_end
                            ,String bargained_price_start,String bargained_price_end
                            ,String house_area_start,String house_area_end
                            ,String house_unit_price_start,String house_unit_price_end
                            ,String[] checkbox_house_region
                            ,String save_time_start,String save_time_end
                            ,String[] checkbox_current_status
    );
    //统计数量
    long count(Detail_info detail_info);
    //得到当前查询的数量
    long getCountByCondition();
    //统计成交价
    Map statisticsTransactionPrice(
            String date_min,String date_max
            ,String save_time_start,String save_time_end
    );
    Map statisticsPieBargainedPrice(
            String pie_date_min,String pie_date_max
            ,String pie_save_time_start,String pie_save_time_end
            ,String[] pie_checkbox_house_region
    );
    //    查询数据通过ID
    Detail_info selectDetail_infoById(Detail_info detail_info);

    //查询昨天的数据挂网的数据
//    List<Detail_info> selectYesterdayHouseProclaimtionTimeDetailInfo();
}
