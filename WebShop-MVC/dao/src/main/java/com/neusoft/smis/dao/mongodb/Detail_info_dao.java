package com.neusoft.smis.dao.mongodb;

import com.neusoft.smis.common.entity.mongodb.Detail_info;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by WanJunliang on 2017/5/16.
 */
@Transactional
public interface Detail_info_dao {
    //查询全部
    List<Detail_info> findAll();
    //条件查询
    List<Detail_info> find(Detail_info detail_info,String skip, String limit,double house_price_min,double house_price_max,boolean house_price_flag
            ,String data_house_start_time_min,String data_house_start_time_max,boolean data_house_start_time_flag
    );
    List<Detail_info> find2(Detail_info detail_info,int skip, int limit
                            , String bargained_time_min_String, String bargained_time_max_String
                            ,double bargained_price_min,double bargained_price_max
                            ,double house_area_min,double house_area_max,boolean flag_house_area
                            ,double house_unit_price_min,double house_unit_price_max,boolean flag_house_unit_price
                            ,String[] checkbox_house_region
                            ,boolean flag_bargined_time,boolean flag_bargained_price
                            ,String save_time_min,String save_time_max,boolean flag_save_time
                            ,String[] checkbox_current_status
    );
    //统计总数量
    long count(Detail_info detail_info);
    void insert(Detail_info detail_info);
    //得到当前查询的数量
    long getCountByCondition();
    //查询成交日期范围内的数据
    List<Detail_info> selectDetail_infoByTransactionDate(
            String startDay,String endDay
            ,String save_time_min,String save_time_max
    );
    List<Detail_info> selectDetail_infoByBargained_timeSave_timeHouse_region(
            String startDay,String endDay
            ,String save_time_min,String save_time_max
            ,String[] checkbox_house_region
    );
//    查询数据通过ID
    Detail_info selectDetail_infoById(Detail_info detail_info);
}
