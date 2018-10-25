package com.neusoft.smis.webapp.detail_info_controller;

import com.alibaba.fastjson.JSON;
import com.neusoft.smis.common.entity.mongodb.Detail_info;
import com.neusoft.smis.common.paging.Page;
import com.neusoft.smis.service.detail_info_service.Detail_info_service;
import com.neusoft.smis.webapp.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by WanJunliang on 2017/5/16.
 */
//处理爬虫的  controller层
@Controller
@RequestMapping("/detail")
public class Detail_info_controller extends BaseController{
    /**
     *  查询所有的爬虫数据 ,没有传入参数
     * @return
     */
    //查询所有的信息
    @RequestMapping(value = "/findAllDetailInfo",produces = "text/html;charset=UTF-8")
//    @ResponseBody          //注意这里
    public ModelAndView findAllDetailInfo(){
        ModelAndView mav=new ModelAndView();
        List<Detail_info> detail_info_List=detail_info_service.findAll();
        double total11=0;
        double totall1_1=0;
        double total2=0;
        double total2_2=0;
        double total3=0;
        double total3_3=0;
        double total4=0;
        double total4_4=0;
        double total5=0;
        double total5_5=0;
        double sum1=0;
        double sum11=0;
        double sum2=0;
        double sum22=0;
        double sum3=0;
        double sum33=0;
        double sum4=0;
        double sum44=0;
        double sum5=0;
        double sum55=0;
        for(Detail_info detail_info:detail_info_List){
            if(detail_info.getHouse_region()!=null&&"天府新".equals(detail_info.getHouse_region())){
            if("有".equals(detail_info.getFlag_sf_taobao_com())){
                if (detail_info.getHouse_start_time()!=null&&detail_info.getHouse_start_time().compareTo("2017-06-01") >=0 && detail_info.getHouse_start_time().compareTo("2017-07-01") < 0) {
                    if(detail_info.getHouse_type()==null||detail_info.getHouse_type()==""){
                        if (detail_info.getBargained_price() != null && detail_info.getBargained_price() != "") {
                            total5++;
                            if(Double.parseDouble(detail_info.getBargained_price())>1000000000){
                                sum5 += Double.parseDouble(detail_info.getBargained_price())/10000;
                            }
                            else{
                                sum5 += Double.parseDouble(detail_info.getBargained_price());
                            }
                        }
                        else {
                            total5_5++;
                            if(Double.parseDouble(detail_info.getAuction_price())>1000000000){
                                sum55 += Double.parseDouble(detail_info.getAuction_price())/10000;
                            }
                            else{
                                sum55 += Double.parseDouble(detail_info.getAuction_price());
                            }
                        }
                        continue;
                    }
                    if (detail_info.getHouse_type().equals("住宅")) {
                        if (detail_info.getBargained_price() != null && detail_info.getBargained_price() != "") {
                            total11++;
                            if(Double.parseDouble(detail_info.getBargained_price())>1000000000){
                                sum1 += Double.parseDouble(detail_info.getBargained_price())/10000;
                            }
                            else{
                                sum1 += Double.parseDouble(detail_info.getBargained_price());
                            }
                        }
                        else {
                            totall1_1++;
                            if(Double.parseDouble(detail_info.getAuction_price())>1000000000){
                                sum11 += Double.parseDouble(detail_info.getAuction_price())/10000;
                            }
                            else{
                                sum11 += Double.parseDouble(detail_info.getAuction_price());
                            }
                        }
                    }
                    if (detail_info.getHouse_type().equals("商业")) {
                        if (detail_info.getBargained_price() != null && detail_info.getBargained_price() != "") {
                            total2++;
                            if(Double.parseDouble(detail_info.getBargained_price())>1000000000){
                                sum2 += Double.parseDouble(detail_info.getBargained_price())/10000;
                            }
                            else{
                                sum2 += Double.parseDouble(detail_info.getBargained_price());
                            }
                        }
                        else {
                            total2_2++;
                            if(Double.parseDouble(detail_info.getAuction_price())>1000000000){
                                sum22 += Double.parseDouble(detail_info.getAuction_price())/10000;
                            }
                            else{
                                sum22 += Double.parseDouble(detail_info.getAuction_price());
                            }
                        }
                    }
                    if (detail_info.getHouse_type().equals("办公")) {
                        if (detail_info.getBargained_price() != null && detail_info.getBargained_price() != "") {
                            total3++;
                            if(Double.parseDouble(detail_info.getBargained_price())>1000000000){
                                sum3 += Double.parseDouble(detail_info.getBargained_price())/10000;
                            }
                            else{
                                sum3 += Double.parseDouble(detail_info.getBargained_price());
                            }
                        }
                        else {
                            total3_3++;
                            if(Double.parseDouble(detail_info.getAuction_price())>1000000000){
                                sum33 += Double.parseDouble(detail_info.getAuction_price())/10000;
                            }
                            else{
                                sum33 += Double.parseDouble(detail_info.getAuction_price());
                            }
                        }
                    }
                    if (detail_info.getHouse_type().equals("其他")) {
                        System.out.println(detail_info.getHouse_name());
                        if (detail_info.getBargained_price() != null && detail_info.getBargained_price() != "") {
                            total4++;
                            if(Double.parseDouble(detail_info.getBargained_price())>1000000000){
                                sum4 += Double.parseDouble(detail_info.getBargained_price())/10000;
                            }
                            else{
                                sum4 += Double.parseDouble(detail_info.getBargained_price());
                            }
                        }
                        else {
                            total4_4++;
                            if(Double.parseDouble(detail_info.getAuction_price())>1000000000){
                                sum44 += Double.parseDouble(detail_info.getAuction_price())/10000;
                            }
                            else{
                                sum44 += Double.parseDouble(detail_info.getAuction_price());
                            }
                        }
                    }
                }
            }

            }
        }

        System.out.println("住宅成交数量:"+total11);
        System.out.println("住宅没有成交数量:"+totall1_1);
        System.out.println("住宅成交价:"+sum1);
        System.out.println("住宅未成交价:"+sum11);


        System.out.println("商业成交数量:"+total2);
        System.out.println("商业没有成交数量:"+total2_2);
        System.out.println("商业成交价:"+sum2);
        System.out.println("商业未成交价:"+sum22);

        System.out.println("办公成交数量:"+total3);
        System.out.println("办公没有成交数量:"+total3_3);
        System.out.println("办公成交价:"+sum3);
        System.out.println("办公未成交价:"+sum33);

        System.out.println("其他成交数量:"+total4);
        System.out.println("其他没有成交数量:"+total4_4);
        System.out.println("其他成交价:"+sum4);
        System.out.println("其他未成交价:"+sum44);

        System.out.println("无类型,成交数量"+total5);
        System.out.println("无类型,没有成交数量:"+total5_5);
        System.out.println("无类型,成交价:"+sum5);
        System.out.println("无类型,未成交价:"+sum55);

        mav.addObject("detail_info_List",detail_info_List);
        mav.setViewName("index");
        return mav;
//        String toJson="";
//        try {
//            toJson= JsonUtils.instance.toJson(mav.getModel());
//        } catch (Exception e) {
//           e.printStackTrace();
//        }
//        return toJson;       //必须转换为json格式数据
    }
    //查询所有的信息
    @RequestMapping(value = "/testfindAllDetailInfo",produces = "text/html;charset=UTF-8")
//    @ResponseBody          //注意这里
    public ModelAndView testfindAllDetailInfo(){
        ModelAndView mav=new ModelAndView();
        List<Detail_info> detail_info_List=detail_info_service.findAll();
        double count=0;
        for(Detail_info detail_info:detail_info_List){
            if("有".equals(detail_info.getFlag_sf_taobao_com())){
                count++;
            }
        }
        System.out.println("统计数量:"+count);
        mav.addObject("detail_info_List",detail_info_List);
        mav.setViewName("index");
        return mav;
    }
    /**
     * 多条件查询 爬虫数据 传入页数,爬虫的信息,房产价格最低,房产价格最大
     * @param pageNo                 页码  从1开始
     * @param detail_info            数据信息
     * @param house_price_min       房产最小价格
     * @param house_price_max         房产最大价格
     * @param house_start_time_min    房产开拍最小时间
     * @param house_start_time_max    房产开拍最大时间
     * @return
     */
    @RequestMapping(value = "/findDetailInfo",produces = "text/html;charset=UTF-8")
    public ModelAndView findDetailInfo(String pageNo,Detail_info detail_info
            ,String house_price_min,String house_price_max
            ,String house_start_time_min,String house_start_time_max
    ){
        if(pageNo==null||pageNo==""){
            pageNo="1";
        }
        Page page=new Page();
        int pageSize=page.getPageSize();
//        int pageSize=3;
        int intPageNo=Integer.parseInt(pageNo);
//        long totalRecord=detail_info_service.count(detail_info);//总数量
//        int totalPage=(int)Math.ceil(1.0*totalRecord/pageSize);//总共页数
        int skip=(intPageNo-1)*pageSize; //去掉前面的数据,跳跃到skip这个位置开始取数据

        ModelAndView mav=new ModelAndView();
        List<Detail_info> detail_info_List=detail_info_service.find(detail_info,skip+"",pageSize+"",house_price_min,house_price_max,house_start_time_min,house_start_time_max);
        long totalRecord=detail_info_service.getCountByCondition(); //总数据数量
        int totalPage=(int)Math.ceil(1.0*totalRecord/pageSize);//总共页数
        mav.addObject("detail_info_List",detail_info_List);  //数据表
        mav.addObject("detail_info",detail_info);     //再次存储查询的信息
        mav.addObject("totalRecord",totalRecord);//总共数据数量
        mav.addObject("totalPage",totalPage);//总页数
        mav.addObject("currentPage",pageNo);//当前页数
        mav.addObject("house_price_min",house_price_min);//房产查询最小价格
        mav.addObject("house_price_max",house_price_max);//房产查询最大价格
        mav.addObject("house_start_time_min",house_start_time_min);//查询房产开拍最小时间
        mav.addObject("house_start_time_max",house_start_time_max);//查询房产开拍最大时间
        mav.setViewName("detail_info_find");
//        String res=null;
//        try {
//            res = JSON.toJSONString(mav.getModel());
//        }catch (Exception e){
//            System.out.println("封装json失败!");
//        }
        return mav;
    }

    /**
     * 统计成交价格
     * @param date_min   开始日期
     * @param date_max   结束日期
     * @return          json格式字符串
     */
    @RequestMapping(value = "/statisticsTransactionPrice",produces = "text/html;charset=UTF-8")
    @ResponseBody
    @RequiresPermissions("数据:统计")
    public String statisticsTransactionPrice(
            String date_min,String date_max
            ,String save_time_start,String save_time_end
    ){
        System.out.println("传入的入库日期:"+save_time_start);
        System.out.println("传入的入库日期:"+save_time_end);
        Map res_map=detail_info_service.statisticsTransactionPrice(
                date_min,date_max
                ,save_time_start,save_time_end
        );
//        List<String> category=new ArrayList<String>();
//        category.add("2017-05-20");
//        category.add("2017-05-21");
//        category.add("2017-05-22");
//        category.add("2017-05-23");
//        category.add("2017-05-24");
//        List<String> data=new ArrayList<String>();
//        data.add("40");
//        data.add("60");
//        data.add("40");
//        data.add("60");
//        data.add("50.99");
//        List<List> series=new ArrayList<List>();
//        series.add(data);
//        String totalPrice="200";
//        Map map=new HashMap<>();
//        map.put("category",category);
//        map.put("series",series);
//        map.put("totalPrice",totalPrice);
        String res=null;
        try {
            res = JSON.toJSONString(res_map);
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        System.out.println(res);
        return res;
    }

    /**
     * 多条件查询爬虫数据2
     * @param pageNo     页码
     * @param detail_info          爬虫信息数据   可以传可不传
     * @param bargained_time_start       开始成交日期
     * @param bargained_time_end          结束成交日期
     * @param bargained_price_start        开始的成交价格
     * @param bargained_price_end          结束的成交价格
     * @param house_area_start            开始的房产面积
     * @param house_area_end               结束的房产面积
     * @param house_unit_price_start       开始的房产单价
     * @param house_unit_price_end        结束的房产单价
     * @param checkbox_house_region                  房产地区数组
     * @return                          modeAndView
     */
    @RequestMapping(value = "/findDetailInfo2",produces = "text/html;charset=UTF-8")
    @ResponseBody
    @RequiresPermissions("数据:查询")
    public String findDetailInfo2(String pageNo,Detail_info detail_info
            ,String bargained_time_start,String bargained_time_end
            ,String bargained_price_start,String bargained_price_end
            ,String house_area_start,String house_area_end
            ,String house_unit_price_start,String house_unit_price_end
            ,@RequestParam(value = "checkbox_house_region", required = false) String[] checkbox_house_region
            ,String save_time_start,String save_time_end
            ,@RequestParam(value = "checkbox_current_status", required = false) String[] checkbox_current_status
    ){
        System.out.println("pageNo="+pageNo);
        System.out.println("save_time_start="+save_time_start);
        for(String i:checkbox_house_region){
            System.out.println(i);
        }
        for(String i:checkbox_current_status){
            System.out.println(i);
        }
        if(pageNo==null||pageNo==""){
            pageNo="1";
        }
        Page page=new Page();
        int pageSize=page.getPageSize();
        int intPageNo=1;
        try {
            intPageNo=Integer.parseInt(pageNo);
        }catch (Exception e){
            System.out.println("findDetailInfo2()中intPageNo转化失败");
        }
        int skip=(intPageNo-1)*pageSize; //去掉前面的数据,跳跃到skip这个位置开始取数据

        ModelAndView mav=new ModelAndView();
        List<Detail_info> detail_info_List=detail_info_service.find2(detail_info,skip,pageSize
                ,bargained_time_start,bargained_time_end
                ,bargained_price_start,bargained_price_end
                ,house_area_start,house_area_end
                ,house_unit_price_start,house_unit_price_end
                ,checkbox_house_region
                ,save_time_start,save_time_end
                ,checkbox_current_status
                );
        //去除html源码
//        for(Detail_info i:detail_info_List){
//            i.setAuction_notice_page("");
//            i.setNotice_page("");
//            i.setHouse_presentation_page("");
//        }
        System.out.println("数据条数:"+detail_info_List.size());
        long totalRecord=detail_info_service.getCountByCondition(); //总数据数量
        int totalPage=(int)Math.ceil(1.0*totalRecord/pageSize);//总共页数
        mav.addObject("detail_info_List",detail_info_List);  //数据表
        mav.addObject("detail_info",detail_info);     //再次存储查询的信息
        mav.addObject("totalRecord",totalRecord);//总共数据数量
        mav.addObject("totalPage",totalPage);//总页数
        mav.addObject("currentPage",pageNo);//当前页数
        mav.addObject("bargained_time_start",bargained_time_start);
        mav.addObject("bargained_time_end",bargained_time_end);
        mav.addObject("bargained_price_start",bargained_price_start);
        mav.addObject("bargained_price_end",bargained_price_end);
        mav.addObject("house_area_start",house_area_start);
        mav.addObject("house_area_end",house_area_end);
        mav.addObject("house_unit_price_start",house_unit_price_start);
        mav.addObject("house_unit_price_end",house_unit_price_end);
        mav.addObject("save_time_start",save_time_start);
        mav.addObject("save_time_end",save_time_end);

        mav.addObject("checkbox_house_region",checkbox_house_region);//拼接字符串返回到前端  ckeckbox匹配 是否是选中状态用的
        mav.addObject("checkbox_current_status",checkbox_current_status);

        String res=null;
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }

    /**
     * 根据各个区统计一定的时间内的总共成交价  饼状图
     * @param pie_checkbox_house_region 区域复选框
     * @param pie_date_min  成交时间最小
     * @param pie_date_max  成交时间最大
     * @param pie_save_time_start 入库时间起始
     * @param pie_save_time_end 入库时间终止
     * @return
     */
    @RequestMapping(value = "/statisticsPieBargainedPrice",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String statisticsPieBargainedPrice(
            String pie_date_min,String pie_date_max
            ,String pie_save_time_start,String pie_save_time_end
            ,@RequestParam(value = "pie_checkbox_house_region", required = false) String[] pie_checkbox_house_region
    ){
        //判断是否参数有一个是空的    参数都必须存在
        if(     pie_date_min==null||"".equals(pie_date_min)
                ||pie_date_max==null||"".equals(pie_date_max)
                ||pie_save_time_start==null||"".equals(pie_save_time_start)
                ||pie_save_time_end==null||"".equals(pie_save_time_end)
                ||pie_checkbox_house_region==null||"".equals(pie_checkbox_house_region)
                ){
            return "null";
        }
        Map map=detail_info_service.statisticsPieBargainedPrice(
                pie_date_min,pie_date_max
                ,pie_save_time_start,pie_save_time_end
                ,pie_checkbox_house_region
        );

        String res=null;
        try {
            res = JSON.toJSONString(map);
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        System.out.println(res);
        return res;
    }

    /**
     * 查询数据信息通过数据的ID来查询
     * @param detail_info id
     * @return 数据信息
     */
    @RequestMapping(value = "/findDetailInfoById",produces = "text/html;charset=UTF-8")
    @ResponseBody
    @RequiresPermissions("数据:查询")
    public String findDetailInfoById(Detail_info detail_info){
        ModelAndView mav=new ModelAndView();
        Detail_info res_detail_info= detail_info_service.selectDetail_infoById(detail_info);
        mav.addObject("detail_info",res_detail_info);
//        查询数据通过ID
        String res=null;
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }
}
