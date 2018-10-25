package com.neusoft.smis.dao.mysql.dataAnalysis;

import com.neusoft.smis.common.entity.mysql.TdzyfSifaPaimai;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/9/7.
 */
public interface DataAnalysisMapper {
    //查询一个时间段内的上拍数量,传入参数time1,time2(1)
    int selectCountByMonth(Map map);
    //查询一个时间段内的上拍数量,传入参数time1,time2(1)
    int selectCountChengjiaoByMonth(Map map);



    //查询一个时间段内的上拍数量,传入参数time1,time2,house_type
    int selectCountByMonthByHouseType(Map map);
    //查询一个时间段内的上拍数量,传入参数time1,time2,house_type
    int selectCountChengjiaoByMonthByHouseType(Map map);

    //查询一个时间段内的上拍数量,传入参数time1,time2,auction_stage(8)
    int selectCountByMonthByAuctionStage(Map map);
    //查询一个时间段内的上拍数量,传入参数time1,time2,auction_stage(8)
    int selectCountChengjiaoByMonthByAuctionStage(Map map);

    //查询一个时间段内的上拍数量,传入参数time1,time2,house_region(5)
    int selectCountByMonthByHouseRegion(Map map);
    //查询一个时间段内的上拍数量,传入参数time1,time2,house_region(5)
    int selectCountChengjiaoByMonthByHouseRegion(Map map);

    //查询tdzyfsifapaimai中的数据通过公告时间,传入参数:time1,time2
    List<TdzyfSifaPaimai> selectTdzyfSiFaPaiMaiByTime1Time2(Map map);
    //查询tdzyfsifapaimai中的数据通过结束时间,传入参数:time1,time2
    List<TdzyfSifaPaimai> selectChengjiaoTdzyfSiFaPaiMaiByTime1Time2(Map map);


}
