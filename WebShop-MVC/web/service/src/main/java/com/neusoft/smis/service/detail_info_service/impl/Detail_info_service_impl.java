package com.neusoft.smis.service.detail_info_service.impl;

import com.neusoft.smis.common.entity.mongodb.Detail_info;
import com.neusoft.smis.dao.mongodb.Detail_info_dao;

import com.neusoft.smis.dao.mongodb.impl.Detail_info_dao_impl;
import com.neusoft.smis.service.detail_info_service.Detail_info_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.*;
import java.util.*;

/**
 * Created by WanJunliang on 2017/5/16.
 */
@Service("detail_info_service")
public class Detail_info_service_impl implements Detail_info_service {
    @Autowired
    Detail_info_dao detail_info_dao;

    @Override
    public List<Detail_info> findAll() {
        List<Detail_info> detail_infoList=null;
        try {
            detail_infoList= detail_info_dao.findAll();
        }catch (Exception e){
            System.out.println("Detail_info_service_impl_报错");
            e.printStackTrace();
        }
        return detail_infoList;
    }
    @Override
    public void insert(Detail_info detail_info) {
        detail_info_dao.insert(detail_info);

    }

    @Override
    public List<Detail_info> find(Detail_info detail_info, String skip, String limit,String house_price_min,String house_price_max
            ,String house_start_time_min,String house_start_time_max) {
        double double_house_price_min=0;//房产最小值
        double double_house_price_max=Double.MAX_VALUE;//房产最大值
        boolean house_price_flag=true;//是否进行房产的判断标志
        if(house_price_min==null&&house_price_max==null||house_price_min==""&&house_price_max==""){
            house_price_flag=false;
        }
        try{
            double_house_price_min=Double.parseDouble(house_price_min);
        }catch (Exception e){
        }
        try{
            double_house_price_max=Double.parseDouble(house_price_max);
        }catch (Exception e){
        }

        SimpleDateFormat format =  new SimpleDateFormat( "yyyy-MM-dd" );
        Date data_house_start_time_min=null;
        try{
            data_house_start_time_min=format.parse("1990-01-01");
        }catch (ParseException e){
        }
        Date data_house_start_time_max=null;
        try{
            data_house_start_time_max=format.parse("2117-01-01");
        }catch (ParseException e){
        }
        boolean data_house_start_time_flag=true;//是否进行日期的判断
        if(house_start_time_min==null&&house_start_time_max==null||house_start_time_min==""&&house_start_time_max==""){
            data_house_start_time_flag=false;
        }
        if(data_house_start_time_flag==true){
            try {
                data_house_start_time_min=format.parse(house_start_time_min);
            } catch (ParseException e) {
                data_house_start_time_flag=false;
            }
            try {
                data_house_start_time_max=format.parse(house_start_time_max);
            } catch (ParseException e) {
                data_house_start_time_flag=false;
            }
        }
        return detail_info_dao.find(detail_info,skip,limit
                ,double_house_price_min,double_house_price_max,house_price_flag
                ,house_start_time_min,house_start_time_max,data_house_start_time_flag
        );
    }

    @Override
    public List<Detail_info> find2(Detail_info detail_info, int skip, int limit
            , String bargained_time_start, String bargained_time_end
            , String bargained_price_start, String bargained_price_end
            , String house_area_start, String house_area_end
            , String house_unit_price_start, String house_unit_price_end
            ,String[] checkbox_house_region
            ,String save_time_start,String save_time_end
            ,String[] checkbox_current_status
    ) {
        //把时间做个处理  设置最大值和最小值日期
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date bargained_time_min = null;
        Boolean flag_bargined_time = false; //是否采用成交时间进行判断  默认是false
        try {
            bargained_time_min = format.parse("2000-01-01 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date bargained_time_max = null;
        try {
            bargained_time_max = format.parse("2100-01-01 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (bargained_time_start != null && "".equals(bargained_time_start) == false) {
            try {
                bargained_time_min = format.parse(bargained_time_start);
                flag_bargined_time = true;
            } catch (ParseException e) {
                System.out.println("find2()日期转化bargained_time_start");

            }
        }
        if (bargained_time_end != null && "".equals(bargained_time_end) == false) {
            try {
                bargained_time_max = format.parse(bargained_time_end);
                flag_bargined_time = true;
            } catch (ParseException e) {
                System.out.println("find2()日期转化bargained_time_start");

            }
        }
        String bargained_time_min_String = format.format(bargained_time_min);
        String bargained_time_max_String = format.format(bargained_time_max);
        //把成交价格做个处理
        double bargained_price_min = Double.MIN_VALUE;   //最小值
        double bargained_price_max = Double.MAX_VALUE;   //最大值
        boolean flag_bargained_price = false;
        if (bargained_price_start != null && "".equals(bargained_price_start) == false) {
            try {
                bargained_price_min = Double.parseDouble(bargained_price_start);
                flag_bargained_price = true;
            } catch (Exception e) {
                System.out.println("find2()中bargained_price_start转化失败");
            }
        }
        if (bargained_price_end != null && "".equals(bargained_price_end) == false) {
            try {
                bargained_price_max = Double.parseDouble(bargained_price_end);
                flag_bargained_price = true;
            } catch (Exception e) {
                System.out.println("find2()中bargained_price_end转化失败");

            }
        }
        //把面积做个处理
        double house_area_min = Double.MIN_VALUE;   //最小值
        double house_area_max = Double.MAX_VALUE;   //最大值
        boolean flag_house_area = false;//是否进行面积的判断
        if (house_area_start != null && "".equals(house_area_start) == false) {
            try {
                house_area_min = Double.parseDouble(house_area_start);
                flag_house_area = true;
            } catch (Exception e) {
                System.out.println("find2()中bargained_price_start转化失败");

            }
        }
        if (house_area_end != null && "".equals(house_area_end) == false) {
            try {
                house_area_max = Double.parseDouble(house_area_end);
                flag_house_area = true;
            } catch (Exception e) {
                System.out.println("find2()中house_area_end转化失败");

            }
        }
        //把单价做个处理
        double house_unit_price_min = Double.MIN_VALUE;   //最小值
        double house_unit_price_max = Double.MAX_VALUE;   //最大值
        boolean flag_house_unit_price = false;
        if (house_unit_price_start != null && "".equals(house_unit_price_start) == false) {
            try {
                house_unit_price_min = Double.parseDouble(house_unit_price_start);
                flag_house_unit_price = true;
            } catch (Exception e) {
                System.out.println("find2()中house_unit_price_start转化失败");

            }
        }
        if (house_unit_price_end!=null&&"".equals(house_unit_price_end)==false){
            try {
                house_unit_price_max = Double.parseDouble(house_unit_price_end);
                flag_house_unit_price=true;
            } catch (Exception e) {
                System.out.println("find2()中house_unit_price_end转化失败");

            }
        }
        String save_time_min="2000-01-01 00:00:00";//入库的最小时间
        String save_time_max="2100-01-01 00:00:00";//入的最大时间
        boolean flag_save_time=false;
        if(save_time_start!=null&&"".equals(save_time_start)==false){
            save_time_min=save_time_start;
            flag_save_time=true;
        }
        if(save_time_end!=null&&"".equals(save_time_end)==false){
            save_time_max=save_time_end;
            flag_save_time=true;
        }
        return detail_info_dao.find2(detail_info, skip,  limit
                , bargained_time_min_String,  bargained_time_max_String
                , bargained_price_min, bargained_price_max
                , house_area_min, house_area_max,flag_house_area
                , house_unit_price_min, house_unit_price_max,flag_house_unit_price
                ,checkbox_house_region
                ,flag_bargined_time,flag_bargained_price
                ,save_time_min,save_time_max,flag_save_time
                ,checkbox_current_status
        );
    }

    @Override
    public long count(Detail_info detail_info) {
        return detail_info_dao.count(detail_info);
    }
    @Override
    public long getCountByCondition() {
        return detail_info_dao.getCountByCondition();
    }

    //统计成交价 传入开始日期,和结束日期
    @Override
    public Map statisticsTransactionPrice(
            String date_min, String date_max
            ,String save_time_start,String save_time_end
    ) {
        Calendar startDay = Calendar.getInstance();
        Calendar endDay = Calendar.getInstance();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        try {
            startDay.setTime(format.parse(date_min));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            endDay.setTime(format.parse(date_max));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //终止日期不能大于开始日期
        if(startDay.compareTo(endDay)>0) {
            return null;
        }


        //设置入库时间
        String save_time_min="2000-01-01 00:00:00";
        String save_time_max="2100-01-01 00:00:00";
        if(save_time_start!=null&&"".equals(save_time_start)==false){
            save_time_min=save_time_start;
        }
        if(save_time_end!=null&&"".equals(save_time_end)==false){
            save_time_max=save_time_end;
        }
        //查询在成交日期在  开始 与  结束之间的数据
        List<Detail_info> detail_infoList=detail_info_dao.selectDetail_infoByTransactionDate(format.format(startDay.getTime()),format.format(endDay.getTime()),save_time_min,save_time_max);
        System.out.println("BBB:"+detail_infoList.size());
        Map data_map=new HashMap();
        //遍历 给对应的日期存入成交价格
        for(Detail_info d:detail_infoList){
            //先要取出当前天的数值,如果不存在那么赋值,如果存在就加上去
            if(data_map.isEmpty()){
                String temp_map_date=null;
                try {
                    Date temp_date=format.parse(d.getBargained_time());
                    temp_map_date=format.format(temp_date);
                }catch (Exception e){

                }
                data_map.put(temp_map_date, d.getBargained_price());
                continue;
            }
            String temp_map_date=null;
            try {
                Date temp_date=format.parse(d.getBargained_time());
                temp_map_date=format.format(temp_date);
            }catch (Exception e){

            }
            String a=data_map.get(temp_map_date)+"";
            if(a==null||"".equals(a)||a.equals("null")){
                temp_map_date=null;
                try {
                    Date temp_date=format.parse(d.getBargained_time());
                    temp_map_date=format.format(temp_date);
                }catch (Exception e){

                }
                data_map.put(temp_map_date,d.getBargained_price());
            }
            else{
                try {
                    double temp_b = Double.parseDouble(a);
                    double temp_cp=temp_b+Double.parseDouble(d.getBargained_price());
                    temp_map_date=null;
                    try {
                        Date temp_date=format.parse(d.getBargained_time());
                        temp_map_date=format.format(temp_date);
                    }catch (Exception e){

                    }
                    data_map.put(temp_map_date,temp_cp);
                }catch (Exception e){

                    e.printStackTrace();
                }
            }
        }
        List<String> category=new ArrayList<String>();//横坐标的日期
        List<Double> data=new ArrayList<Double>();    //数据集合
        double totalPrice=0;                //总共成交价
        for(Calendar currntDay=startDay;currntDay.compareTo(endDay)<=0;currntDay.add(Calendar.DATE,1)){
            //存入 日期
            System.out.println("SSS:日期"+format.format(currntDay.getTime()));
            String temp_currentDay=format.format(currntDay.getTime());
            category.add(temp_currentDay);
            //如果data_map数据在今日为空,那么赋值为0
            if(data_map.get(temp_currentDay)==null||"".equals(data_map.get(temp_currentDay))||data_map.get(temp_currentDay).equals("null")){
                data.add(0.0);
                continue;
            }
            data.add(Double.parseDouble(data_map.get(temp_currentDay)+""));
            System.out.println("SSS:今日的当前价和"+data_map.get(temp_currentDay)+"");
            try {
                totalPrice += Double.parseDouble(data_map.get(temp_currentDay)+"");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("DDD:totalPrice="+totalPrice);
        List<List> series=new ArrayList<List>();
        series.add(data);
        Map res_map=new HashMap();
        res_map.put("category",category);
        res_map.put("series",series);
        res_map.put("totalPrice",totalPrice);
        return res_map;
    }

    @Override
    public Map statisticsPieBargainedPrice(String pie_date_min, String pie_date_max, String pie_save_time_start, String pie_save_time_end, String[] pie_checkbox_house_region) {
        List<Detail_info> detail_infoList=detail_info_dao.selectDetail_infoByBargained_timeSave_timeHouse_region( pie_date_min, pie_date_max,  pie_save_time_start,  pie_save_time_end,pie_checkbox_house_region);
        Map map=new HashMap();
        for(String i:pie_checkbox_house_region){
            map.put(i,"0");
        }
        double pie_totalPrice=0;//总价
        for(Detail_info detail:detail_infoList){
            try {
                double price = Double.parseDouble(map.get(detail.getHouse_region()) + "");
                double t_price = Double.parseDouble(detail.getBargained_price());
                price = price + t_price;
                pie_totalPrice=pie_totalPrice+t_price;

                map.put(detail.getHouse_region(), price);
            }catch (Exception e){
                System.out.println("double转换报错");
            }
        }
        //重新组合格式传给前台的
        List<Map> listMap=new ArrayList<Map>();
        for(String i:pie_checkbox_house_region){
            Map t_map=new HashMap();
            t_map.put("value",map.get(i));
            t_map.put("name",i);
            listMap.add(t_map);
        }
        Map res_map=new HashMap();
        res_map.put("legend_data",pie_checkbox_house_region);
        res_map.put("series_data",listMap);
        res_map.put("pie_totalPrice",pie_totalPrice);
        return res_map;
    }

    @Override
    public Detail_info selectDetail_infoById(Detail_info detail_info) {
        if(detail_info==null||"".equals(detail_info.getId())){
            return null;
        }
        return detail_info_dao.selectDetail_infoById(detail_info);
    }

    public String doubleToString(double d){
        String dou_str="";
        try {
            DecimalFormat   df   =  new   DecimalFormat( "#,##0.00");
            dou_str = df.format(d);
        }catch (Exception e){
            System.out.println("double转换成String方法失败");
        }
        return dou_str;
    }
}
