package com.neusoft.smis.service.tdzyfSifaPaimaiService.impl;

import com.neusoft.smis.common.entity.mysql.TdzyfSifaPaimai;
import com.neusoft.smis.common.entity.mysql.weixin.WeiXinYuYue;
import com.neusoft.smis.dao.mysql.tdzyf.TdzyfSifaPaimaiMapper;
import com.neusoft.smis.service.tdzyfSifaPaimaiService.TdzyfSifaPaimaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/21.
 */
@Service("tdzyfSifaPaimaiService")
public class TdzyfSifaPaimaiServiceImpl implements TdzyfSifaPaimaiService {
    @Autowired
    TdzyfSifaPaimaiMapper tdzyfSifaPaimaiMapper;

    @Override
    public List<TdzyfSifaPaimai> selectTdzyfSifaPaimaiByHouseName(TdzyfSifaPaimai tdzyfSifaPaimai) {
        return tdzyfSifaPaimaiMapper.selectTdzyfSifaPaimaiByHouseName(tdzyfSifaPaimai);
    }

    @Override
    public List<TdzyfSifaPaimai> selectTdzyfSifaPaimaiByhouse_proclaimtion_time(String time_1, String time_2,String[] house_type_list) {
        Map map=new HashMap();
        map.put("startTime",time_1);
        map.put("endTime",time_2);
        map.put("house_type_list",house_type_list);
        return tdzyfSifaPaimaiMapper.selectTdzyfSifaPaimaiByhouse_proclaimtion_time(map);
    }

    @Override
    public List<TdzyfSifaPaimai> selectTdzyfSifaPaimaiByLngLat(Map map) {
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("auction_end_time",simpleDateFormat.format(date));
        return tdzyfSifaPaimaiMapper.selectTdzyfSifaPaimaiByLngLat(map);
    }

    @Override
    public List<TdzyfSifaPaimai> selectTdzyfSifaPaimaiInWeiXin(String time_1, String time_2, String[] house_type_list, String search_input,String auction_end_time_1,String auction_end_time_2,String limit_start,String limit_size,String[] house_region_list,String[] auction_price_list,String[] area_list) {
        Map map=new HashMap();
        map.put("startTime",time_1);
        map.put("endTime",time_2);
        map.put("house_type_list",house_type_list);
        map.put("search_input",search_input);
        map.put("auction_end_time_1",auction_end_time_1);
        map.put("auction_end_time_2",auction_end_time_2);
        map.put("limit_start",Integer.parseInt(limit_start));
        map.put("limit_size",Integer.parseInt(limit_size));
        map.put("house_region_list",house_region_list);
        map.put("auction_price_list",auction_price_list);//起拍价区间列表
        map.put("area_list",area_list);//面积区间列表

        return tdzyfSifaPaimaiMapper.selectTdzyfSifaPaimaiInWeiXin(map);
    }

    @Override
    public List<TdzyfSifaPaimai> selectMyYuyueTdzyfSifaPaimaiDataList(WeiXinYuYue weiXinYuYue) {

        return tdzyfSifaPaimaiMapper.selectMyYuyueTdzyfSifaPaimaiDataList(weiXinYuYue);
    }

    @Override
    public List<TdzyfSifaPaimai> selectTdzyfSifaPaimaiBusiness(Map map) {
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("auction_end_time",simpleDateFormat.format(date));
        return tdzyfSifaPaimaiMapper.selectTdzyfSifaPaimaiBusiness(map);
    }
}
