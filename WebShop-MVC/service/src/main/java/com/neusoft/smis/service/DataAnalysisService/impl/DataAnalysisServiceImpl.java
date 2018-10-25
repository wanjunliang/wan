package com.neusoft.smis.service.DataAnalysisService.impl;

import com.alibaba.fastjson.JSON;
import com.neusoft.smis.common.entity.mysql.TdzyfSifaPaimai;
import com.neusoft.smis.common.entity.mysql.dataAnalysis.FiveDayData;
import com.neusoft.smis.dao.mysql.dataAnalysis.DataAnalysisMapper;
import com.neusoft.smis.service.DataAnalysisService.DataAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2018/9/7.
 */
@Service("dataAnalysisService")
public class DataAnalysisServiceImpl implements DataAnalysisService{
    @Autowired
    DataAnalysisMapper dataAnalysisMapper;

    @Override
    public String selectDataToBiao1() {
        Map resMap=new HashMap();
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) ;
        List<String> yuefeng_array=new ArrayList<String>();
        List<Integer> shangpai_array=new ArrayList<Integer>();
        List<Integer> chengjiao_array=new ArrayList<Integer>();
        for(int i=1;i<=month;i++){
            String time1="";
            String time2="";
            if(i<=9){
                time1=year+"-0"+i+"-01 00:00:00";
            }
            else{
                time1=year+"-"+i+"-01 00:00:00";
            }
            int m2=i+1;
            int y2=year;
            if(m2>12){
                y2=year+1;
                m2=1;
            }
            if(m2<=9){
                time2=y2+"-0"+m2+"-01 00:00:00";
            }
            else{
                time2=y2+"-"+m2+"-01 00:00:00";
            }
            System.out.println("AAA:"+time1+"  "+time2);

            Map map_t=new HashMap();
            map_t.put("time1",time1);
            map_t.put("time2",time2);
            //上拍数量
            int count=dataAnalysisMapper.selectCountByMonth(map_t);
            int chengjiao=dataAnalysisMapper.selectCountChengjiaoByMonth(map_t);

            yuefeng_array.add(i+"月");
            shangpai_array.add(count);
            chengjiao_array.add(chengjiao);
//            resMap.put(i+"月份上拍总数",count);
//            resMap.put(i+"月份成交总数",chengjiao);
        }
        resMap.put("yuefeng_array",yuefeng_array);
        resMap.put("shangpai_array",shangpai_array);
        resMap.put("chengjiao_array",chengjiao_array);
        String res=null;
        try {
            res = JSON.toJSONString(resMap);

        }catch (Exception e){
            res="封装json失败!";
            System.out.println("封装json失败!");
        }
        return res;
    }
    @Override
    public String selectDataToBiao2() {
        Map resMap=new HashMap();
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) ;
        List<String> yuefeng_array=new ArrayList<String>();
        List<Integer> chengjiaolv_array=new ArrayList<Integer>();
        for(int i=1;i<=month;i++){
            String time1="";
            String time2="";
            if(i<=9){
                time1=year+"-0"+i+"-01 00:00:00";
            }
            else{
                time1=year+"-"+i+"-01 00:00:00";
            }
            int m2=i+1;
            int y2=year;
            if(m2>12){
                y2=year+1;
                m2=1;
            }
            if(m2<=9){
                time2=y2+"-0"+m2+"-01 00:00:00";
            }
            else{
                time2=y2+"-"+m2+"-01 00:00:00";
            }
//            System.out.println("AAA:"+time1+"  "+time2);

            Map map_t=new HashMap();
            map_t.put("time1",time1);
            map_t.put("time2",time2);
            map_t.put("house_type","总成交套数");
            //上拍数量
            //上拍数量
            int count=dataAnalysisMapper.selectCountByMonth(map_t);
            int chengjiao=dataAnalysisMapper.selectCountChengjiaoByMonth(map_t);

            yuefeng_array.add(i+"月");
            chengjiaolv_array.add((int)((double)chengjiao/count*100));
        }
        resMap.put("yuefeng_array",yuefeng_array);
        resMap.put("chengjiaolv_array",chengjiaolv_array);
        String res=null;
        try {
            res = JSON.toJSONString(resMap);

        }catch (Exception e){
            res="封装json失败!";
            System.out.println("封装json失败!");
        }
        return res;
    }

    @Override
    public String selectDataToBiao3() {
        Map resMap=new HashMap();
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) ;
        List<String> yuefeng_array=new ArrayList<String>();
        List<Integer> shangpai_array=new ArrayList<Integer>();
        List<Integer> chengjiao_array=new ArrayList<Integer>();
        for(int i=1;i<=month;i++){
            String time1="";
            String time2="";
            if(i<=9){
                time1=year+"-0"+i+"-01 00:00:00";
            }
            else{
                time1=year+"-"+i+"-01 00:00:00";
            }
            int m2=i+1;
            int y2=year;
            if(m2>12){
                y2=year+1;
                m2=1;
            }
            if(m2<=9){
                time2=y2+"-0"+m2+"-01 00:00:00";
            }
            else{
                time2=y2+"-"+m2+"-01 00:00:00";
            }
//            System.out.println("AAA:"+time1+"  "+time2);

            Map map_t=new HashMap();
            map_t.put("time1",time1);
            map_t.put("time2",time2);
            map_t.put("house_type","住宅");
            //上拍数量
            int count=dataAnalysisMapper.selectCountByMonthByHouseType(map_t);
            int chengjiao=dataAnalysisMapper.selectCountChengjiaoByMonthByHouseType(map_t);

            yuefeng_array.add(i+"月");
            shangpai_array.add(count);
            chengjiao_array.add(chengjiao);
//            resMap.put(i+"月份上拍总数",count);
//            resMap.put(i+"月份成交总数",chengjiao);
        }
        resMap.put("yuefeng_array",yuefeng_array);
        resMap.put("shangpai_array",shangpai_array);
        resMap.put("chengjiao_array",chengjiao_array);
        String res=null;
        try {
            res = JSON.toJSONString(resMap);

        }catch (Exception e){
            res="封装json失败!";
            System.out.println("封装json失败!");
        }
        return res;
    }

    @Override
    public String selectDataToBiao4() {
        Map resMap=new HashMap();
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) ;
        List<String> yuefeng_array=new ArrayList<String>();
        List<Integer> chengjiaolv_array=new ArrayList<Integer>();
        for(int i=1;i<=month;i++){
            String time1="";
            String time2="";
            if(i<=9){
                time1=year+"-0"+i+"-01 00:00:00";
            }
            else{
                time1=year+"-"+i+"-01 00:00:00";
            }
            int m2=i+1;
            int y2=year;
            if(m2>12){
                y2=year+1;
                m2=1;
            }
            if(m2<=9){
                time2=y2+"-0"+m2+"-01 00:00:00";
            }
            else{
                time2=y2+"-"+m2+"-01 00:00:00";
            }
//            System.out.println("AAA:"+time1+"  "+time2);

            Map map_t=new HashMap();
            map_t.put("time1",time1);
            map_t.put("time2",time2);
            map_t.put("house_type","住宅");
            //上拍数量
            int count=dataAnalysisMapper.selectCountByMonthByHouseType(map_t);
            int chengjiao=dataAnalysisMapper.selectCountChengjiaoByMonthByHouseType(map_t);

            yuefeng_array.add(i+"月");
            chengjiaolv_array.add((int)((double)chengjiao/count*100));
        }
        resMap.put("yuefeng_array",yuefeng_array);
        resMap.put("chengjiaolv_array",chengjiaolv_array);
        String res=null;
        try {
            res = JSON.toJSONString(resMap);

        }catch (Exception e){
            res="封装json失败!";
            System.out.println("封装json失败!");
        }
        return res;
    }

    @Override
    public String selectDataToBiao7() {
        Map resMap=new HashMap();
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) ;
        Map map_t=new HashMap();
        //日期
        List<FiveDayData> date_list=new ArrayList<FiveDayData>();

        for(int i=0;i<15;i++){
            FiveDayData fiveDayData=new FiveDayData();
            Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例
            ca.add(Calendar.DATE, -i);// 日期减1
            Date resultDate = ca.getTime(); // 结果
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String time1=sdf.format(resultDate)+" 00:00:00";
            String time2=sdf.format(resultDate)+" 24:00:00";
            map_t.put("time1",time1);
            map_t.put("time2",time2);

//            System.out.println("AAA:"+time1+" BBB:"+time2);
//            上拍的数据
            List<TdzyfSifaPaimai> tdzyfSifaPaimaiList=dataAnalysisMapper.selectTdzyfSiFaPaiMaiByTime1Time2(map_t);

            //上拍套数
            int shangpaitaoshu=tdzyfSifaPaimaiList.size();
            //上拍总金额
            int shangpaizongjine=0;
            //遍历
            for(TdzyfSifaPaimai t:tdzyfSifaPaimaiList){
                shangpaizongjine+=t.getAuction_price();
            }
            //成交数据表
            List<TdzyfSifaPaimai> tdzyfSifaPaimaiList_chengjiao=dataAnalysisMapper.selectChengjiaoTdzyfSiFaPaiMaiByTime1Time2(map_t);
            //成交套数
            int chengjiaotaoshu=tdzyfSifaPaimaiList_chengjiao.size();
            //成交金额
            int chengjiaojine=0;
            //遍历
            for(TdzyfSifaPaimai t:tdzyfSifaPaimaiList_chengjiao){
                chengjiaojine+=t.getBargained_price();
            }


            fiveDayData.setDate(sdf.format(resultDate));
            fiveDayData.setShangpaitaoshu(shangpaitaoshu);
            fiveDayData.setShangpaijine(shangpaizongjine);
            fiveDayData.setChengjiaotaoshu(chengjiaotaoshu);
            fiveDayData.setChengjiaojine(chengjiaojine);
            date_list.add(fiveDayData);

        }
        resMap.put("date_list",date_list);
        String res=null;
        try {
            res = JSON.toJSONString(resMap);
        }catch (Exception e){
            res="封装json失败!";
            System.out.println("封装json失败!");
        }
        return res;
    }

    @Override
    public String selectDataToBiao5() {
        Map resMap=new HashMap();
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        String time1=year+"-01"+"-01"+" 00:00:00";
        String time2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cale.getTime());
        String[] regions={"锦江","青羊","金牛","武侯","成华","双流","高新","天府新","龙泉驿","温江","郫"};
        List<String> region_array=new ArrayList<String>();
        List<Integer> shangpai_array=new ArrayList<Integer>();
        List<Integer> chengjiao_array=new ArrayList<Integer>();
        Map map_t=new HashMap();
        map_t.put("time1",time1);
        map_t.put("time2",time2);
        for(int i=0;i<regions.length;i++){

            map_t.put("house_region",regions[i]);
            //上拍数量
            int count=dataAnalysisMapper.selectCountByMonthByHouseRegion(map_t);
            int chengjiao=dataAnalysisMapper.selectCountChengjiaoByMonthByHouseRegion(map_t);

            region_array.add(regions[i]);
            shangpai_array.add(count);
            chengjiao_array.add(chengjiao);

        }
        resMap.put("region_array",region_array);
        resMap.put("shangpai_array",shangpai_array);
        resMap.put("chengjiao_array",chengjiao_array);
        String res=null;
        try {
            res = JSON.toJSONString(resMap);
        }catch (Exception e){
            res="封装json失败!";
            System.out.println("封装json失败!");
        }
        return res;
    }

    @Override
    public String selectDataToBiao8() {
        Map resMap=new HashMap();
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) ;
        List<String> yuefeng_array=new ArrayList<String>();
        List<Integer> shangpai_array=new ArrayList<Integer>();
        List<Integer> chengjiao_array=new ArrayList<Integer>();
        for(int i=1;i<=month;i++){
            String time1="";
            String time2="";
            if(i<=9){
                time1=year+"-0"+i+"-01 00:00:00";
            }
            else{
                time1=year+"-"+i+"-01 00:00:00";
            }
            int m2=i+1;
            int y2=year;
            if(m2>12){
                y2=year+1;
                m2=1;
            }
            if(m2<=9){
                time2=y2+"-0"+m2+"-01 00:00:00";
            }
            else{
                time2=y2+"-"+m2+"-01 00:00:00";
            }
//            System.out.println("AAA:"+time1+"  "+time2);

            Map map_t=new HashMap();
            map_t.put("time1",time1);
            map_t.put("time2",time2);
            map_t.put("auction_stage","一拍");
            //上拍数量
            int count=dataAnalysisMapper.selectCountByMonthByAuctionStage(map_t);
            int chengjiao=dataAnalysisMapper.selectCountChengjiaoByMonthByAuctionStage(map_t);

            yuefeng_array.add(i+"月");
            shangpai_array.add(count);
            chengjiao_array.add(chengjiao);

        }
        resMap.put("yuefeng_array",yuefeng_array);
        resMap.put("shangpai_array",shangpai_array);
        resMap.put("chengjiao_array",chengjiao_array);
        String res=null;
        try {
            res = JSON.toJSONString(resMap);
        }catch (Exception e){
            res="封装json失败!";
            System.out.println("封装json失败!");
        }
        return res;
    }



    @Override
    public String selectDataToBiao9() {
        Map resMap=new HashMap();
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) ;
        List<String> yuefeng_array=new ArrayList<String>();
        List<Integer> shangpai_array=new ArrayList<Integer>();
        List<Integer> chengjiao_array=new ArrayList<Integer>();
        for(int i=1;i<=month;i++){
            String time1="";
            String time2="";
            if(i<=9){
                time1=year+"-0"+i+"-01 00:00:00";
            }
            else{
                time1=year+"-"+i+"-01 00:00:00";
            }
            int m2=i+1;
            int y2=year;
            if(m2>12){
                y2=year+1;
                m2=1;
            }
            if(m2<=9){
                time2=y2+"-0"+m2+"-01 00:00:00";
            }
            else{
                time2=y2+"-"+m2+"-01 00:00:00";
            }
//            System.out.println("AAA:"+time1+"  "+time2);

            Map map_t=new HashMap();
            map_t.put("time1",time1);
            map_t.put("time2",time2);
            map_t.put("auction_stage","二拍");
            //上拍数量
            int count=dataAnalysisMapper.selectCountByMonthByAuctionStage(map_t);
            int chengjiao=dataAnalysisMapper.selectCountChengjiaoByMonthByAuctionStage(map_t);

            yuefeng_array.add(i+"月");
            shangpai_array.add(count);
            chengjiao_array.add(chengjiao);
//            resMap.put(i+"月份上拍总数",count);
//            resMap.put(i+"月份成交总数",chengjiao);
        }
        resMap.put("yuefeng_array",yuefeng_array);
        resMap.put("shangpai_array",shangpai_array);
        resMap.put("chengjiao_array",chengjiao_array);
        String res=null;
        try {
            res = JSON.toJSONString(resMap);

        }catch (Exception e){
            res="封装json失败!";
            System.out.println("封装json失败!");
        }
        return res;
    }



    @Override
    public String selectDataToBiao10() {
        Map resMap=new HashMap();
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) ;
        List<String> yuefeng_array=new ArrayList<String>();
        List<Integer> shangpai_array=new ArrayList<Integer>();
        List<Integer> chengjiao_array=new ArrayList<Integer>();
        for(int i=1;i<=month;i++){
            String time1="";
            String time2="";
            if(i<=9){
                time1=year+"-0"+i+"-01 00:00:00";
            }
            else{
                time1=year+"-"+i+"-01 00:00:00";
            }
            int m2=i+1;
            int y2=year;
            if(m2>12){
                y2=year+1;
                m2=1;
            }
            if(m2<=9){
                time2=y2+"-0"+m2+"-01 00:00:00";
            }
            else{
                time2=y2+"-"+m2+"-01 00:00:00";
            }
//            System.out.println("AAA:"+time1+"  "+time2);

            Map map_t=new HashMap();
            map_t.put("time1",time1);
            map_t.put("time2",time2);
            map_t.put("auction_stage","变卖");
            //上拍数量
            int count=dataAnalysisMapper.selectCountByMonthByAuctionStage(map_t);
            int chengjiao=dataAnalysisMapper.selectCountChengjiaoByMonthByAuctionStage(map_t);
            yuefeng_array.add(i+"月");
            shangpai_array.add(count);
            chengjiao_array.add(chengjiao);
//            resMap.put(i+"月份上拍总数",count);
//            resMap.put(i+"月份成交总数",chengjiao);
        }
        resMap.put("yuefeng_array",yuefeng_array);
        resMap.put("shangpai_array",shangpai_array);
        resMap.put("chengjiao_array",chengjiao_array);
        String res=null;
        try {
            res = JSON.toJSONString(resMap);

        }catch (Exception e){
            res="封装json失败!";
            System.out.println("封装json失败!");
        }
        return res;
    }


    @Override
    public String selectDataToBiao11() {
        Map resMap=new HashMap();
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) ;
        List<String> yuefeng_array=new ArrayList<String>();
        List<Integer> chengjiaolv_array=new ArrayList<Integer>();
        for(int i=1;i<=month;i++){
            String time1="";
            String time2="";
            if(i<=9){
                time1=year+"-0"+i+"-01 00:00:00";
            }
            else{
                time1=year+"-"+i+"-01 00:00:00";
            }
            int m2=i+1;
            int y2=year;
            if(m2>12){
                y2=year+1;
                m2=1;
            }
            if(m2<=9){
                time2=y2+"-0"+m2+"-01 00:00:00";
            }
            else{
                time2=y2+"-"+m2+"-01 00:00:00";
            }
//            System.out.println("AAA:"+time1+"  "+time2);

            Map map_t=new HashMap();
            map_t.put("time1",time1);
            map_t.put("time2",time2);
            map_t.put("auction_stage","二拍");
            //上拍数量
            int count=dataAnalysisMapper.selectCountByMonthByAuctionStage(map_t);
            int chengjiao=dataAnalysisMapper.selectCountChengjiaoByMonthByAuctionStage(map_t);

            yuefeng_array.add(i+"月");
            chengjiaolv_array.add((int)((double)chengjiao/count*100));
        }
        resMap.put("yuefeng_array",yuefeng_array);
        resMap.put("chengjiaolv_array",chengjiaolv_array);
        String res=null;
        try {
            res = JSON.toJSONString(resMap);

        }catch (Exception e){
            res="封装json失败!";
            System.out.println("封装json失败!");
        }
        return res;
    }


    @Override
    public String selectDataToBiao12() {
        Map resMap=new HashMap();
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) ;
        List<String> yuefeng_array=new ArrayList<String>();
        List<Integer> chengjiaolv_array=new ArrayList<Integer>();
        for(int i=1;i<=month;i++){
            String time1="";
            String time2="";
            if(i<=9){
                time1=year+"-0"+i+"-01 00:00:00";
            }
            else{
                time1=year+"-"+i+"-01 00:00:00";
            }
            int m2=i+1;
            int y2=year;
            if(m2>12){
                y2=year+1;
                m2=1;
            }
            if(m2<=9){
                time2=y2+"-0"+m2+"-01 00:00:00";
            }
            else{
                time2=y2+"-"+m2+"-01 00:00:00";
            }
//            System.out.println("AAA:"+time1+"  "+time2);

            Map map_t=new HashMap();
            map_t.put("time1",time1);
            map_t.put("time2",time2);
            map_t.put("auction_stage","变卖");
            //上拍数量
            int count=dataAnalysisMapper.selectCountByMonthByAuctionStage(map_t);
            int chengjiao=dataAnalysisMapper.selectCountChengjiaoByMonthByAuctionStage(map_t);

            yuefeng_array.add(i+"月");
            chengjiaolv_array.add((int)((double)chengjiao/count*100));
        }
        resMap.put("yuefeng_array",yuefeng_array);
        resMap.put("chengjiaolv_array",chengjiaolv_array);
        String res=null;
        try {
            res = JSON.toJSONString(resMap);

        }catch (Exception e){
            res="封装json失败!";
            System.out.println("封装json失败!");
        }
        return res;
    }


    @Override
    public String selectDataToBiao13() {
        Map resMap=new HashMap();
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) ;
        List<String> yuefeng_array=new ArrayList<String>();
        List<Integer> shangpai_array=new ArrayList<Integer>();
        List<Integer> chengjiao_array=new ArrayList<Integer>();
        for(int i=1;i<=month;i++){
            String time1="";
            String time2="";
            if(i<=9){
                time1=year+"-0"+i+"-01 00:00:00";
            }
            else{
                time1=year+"-"+i+"-01 00:00:00";
            }
            int m2=i+1;
            int y2=year;
            if(m2>12){
                y2=year+1;
                m2=1;
            }
            if(m2<=9){
                time2=y2+"-0"+m2+"-01 00:00:00";
            }
            else{
                time2=y2+"-"+m2+"-01 00:00:00";
            }
//            System.out.println("AAA:"+time1+"  "+time2);

            Map map_t=new HashMap();
            map_t.put("time1",time1);
            map_t.put("time2",time2);
            map_t.put("house_region","锦江");
            //上拍数量
            int count=dataAnalysisMapper.selectCountByMonthByHouseRegion(map_t);
            int chengjiao=dataAnalysisMapper.selectCountChengjiaoByMonthByHouseRegion(map_t);

            yuefeng_array.add(i+"月");
            shangpai_array.add(count);
            chengjiao_array.add(chengjiao);
//            resMap.put(i+"月份上拍总数",count);
//            resMap.put(i+"月份成交总数",chengjiao);
        }
        resMap.put("yuefeng_array",yuefeng_array);
        resMap.put("shangpai_array",shangpai_array);
        resMap.put("chengjiao_array",chengjiao_array);
        String res=null;
        try {
            res = JSON.toJSONString(resMap);

        }catch (Exception e){
            res="封装json失败!";
            System.out.println("封装json失败!");
        }
        return res;
    }


    @Override
    public String selectDataToBiao14() {
        Map resMap=new HashMap();
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) ;
        List<String> yuefeng_array=new ArrayList<String>();
        List<Integer> shangpai_array=new ArrayList<Integer>();
        List<Integer> chengjiao_array=new ArrayList<Integer>();
        for(int i=1;i<=month;i++){
            String time1="";
            String time2="";
            if(i<=9){
                time1=year+"-0"+i+"-01 00:00:00";
            }
            else{
                time1=year+"-"+i+"-01 00:00:00";
            }
            int m2=i+1;
            int y2=year;
            if(m2>12){
                y2=year+1;
                m2=1;
            }
            if(m2<=9){
                time2=y2+"-0"+m2+"-01 00:00:00";
            }
            else{
                time2=y2+"-"+m2+"-01 00:00:00";
            }
//            System.out.println("AAA:"+time1+"  "+time2);

            Map map_t=new HashMap();
            map_t.put("time1",time1);
            map_t.put("time2",time2);
            map_t.put("house_region","青羊");
            //上拍数量
            int count=dataAnalysisMapper.selectCountByMonthByHouseRegion(map_t);
            int chengjiao=dataAnalysisMapper.selectCountChengjiaoByMonthByHouseRegion(map_t);

            yuefeng_array.add(i+"月");
            shangpai_array.add(count);
            chengjiao_array.add(chengjiao);
//            resMap.put(i+"月份上拍总数",count);
//            resMap.put(i+"月份成交总数",chengjiao);
        }
        resMap.put("yuefeng_array",yuefeng_array);
        resMap.put("shangpai_array",shangpai_array);
        resMap.put("chengjiao_array",chengjiao_array);
        String res=null;
        try {
            res = JSON.toJSONString(resMap);

        }catch (Exception e){
            res="封装json失败!";
            System.out.println("封装json失败!");
        }
        return res;
    }


    @Override
    public String selectDataToBiao15() {
        Map resMap=new HashMap();
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) ;
        List<String> yuefeng_array=new ArrayList<String>();
        List<Integer> shangpai_array=new ArrayList<Integer>();
        List<Integer> chengjiao_array=new ArrayList<Integer>();
        for(int i=1;i<=month;i++){
            String time1="";
            String time2="";
            if(i<=9){
                time1=year+"-0"+i+"-01 00:00:00";
            }
            else{
                time1=year+"-"+i+"-01 00:00:00";
            }
            int m2=i+1;
            int y2=year;
            if(m2>12){
                y2=year+1;
                m2=1;
            }
            if(m2<=9){
                time2=y2+"-0"+m2+"-01 00:00:00";
            }
            else{
                time2=y2+"-"+m2+"-01 00:00:00";
            }
//            System.out.println("AAA:"+time1+"  "+time2);

            Map map_t=new HashMap();
            map_t.put("time1",time1);
            map_t.put("time2",time2);
            map_t.put("house_region","金牛");
            //上拍数量
            int count=dataAnalysisMapper.selectCountByMonthByHouseRegion(map_t);
            int chengjiao=dataAnalysisMapper.selectCountChengjiaoByMonthByHouseRegion(map_t);

            yuefeng_array.add(i+"月");
            shangpai_array.add(count);
            chengjiao_array.add(chengjiao);
//            resMap.put(i+"月份上拍总数",count);
//            resMap.put(i+"月份成交总数",chengjiao);
        }
        resMap.put("yuefeng_array",yuefeng_array);
        resMap.put("shangpai_array",shangpai_array);
        resMap.put("chengjiao_array",chengjiao_array);
        String res=null;
        try {
            res = JSON.toJSONString(resMap);

        }catch (Exception e){
            res="封装json失败!";
            System.out.println("封装json失败!");
        }
        return res;
    }



    @Override
    public String selectDataToBiao16() {
        Map resMap=new HashMap();
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) ;
        List<String> yuefeng_array=new ArrayList<String>();
        List<Integer> shangpai_array=new ArrayList<Integer>();
        List<Integer> chengjiao_array=new ArrayList<Integer>();
        for(int i=1;i<=month;i++){
            String time1="";
            String time2="";
            if(i<=9){
                time1=year+"-0"+i+"-01 00:00:00";
            }
            else{
                time1=year+"-"+i+"-01 00:00:00";
            }
            int m2=i+1;
            int y2=year;
            if(m2>12){
                y2=year+1;
                m2=1;
            }
            if(m2<=9){
                time2=y2+"-0"+m2+"-01 00:00:00";
            }
            else{
                time2=y2+"-"+m2+"-01 00:00:00";
            }
//            System.out.println("AAA:"+time1+"  "+time2);

            Map map_t=new HashMap();
            map_t.put("time1",time1);
            map_t.put("time2",time2);
            map_t.put("house_region","武侯");
            //上拍数量
            int count=dataAnalysisMapper.selectCountByMonthByHouseRegion(map_t);
            int chengjiao=dataAnalysisMapper.selectCountChengjiaoByMonthByHouseRegion(map_t);

            yuefeng_array.add(i+"月");
            shangpai_array.add(count);
            chengjiao_array.add(chengjiao);
//            resMap.put(i+"月份上拍总数",count);
//            resMap.put(i+"月份成交总数",chengjiao);
        }
        resMap.put("yuefeng_array",yuefeng_array);
        resMap.put("shangpai_array",shangpai_array);
        resMap.put("chengjiao_array",chengjiao_array);
        String res=null;
        try {
            res = JSON.toJSONString(resMap);

        }catch (Exception e){
            res="封装json失败!";
            System.out.println("封装json失败!");
        }
        return res;
    }



    @Override
    public String selectDataToBiao17() {
        Map resMap=new HashMap();
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) ;
        List<String> yuefeng_array=new ArrayList<String>();
        List<Integer> shangpai_array=new ArrayList<Integer>();
        List<Integer> chengjiao_array=new ArrayList<Integer>();
        for(int i=1;i<=month;i++){
            String time1="";
            String time2="";
            if(i<=9){
                time1=year+"-0"+i+"-01 00:00:00";
            }
            else{
                time1=year+"-"+i+"-01 00:00:00";
            }
            int m2=i+1;
            int y2=year;
            if(m2>12){
                y2=year+1;
                m2=1;
            }
            if(m2<=9){
                time2=y2+"-0"+m2+"-01 00:00:00";
            }
            else{
                time2=y2+"-"+m2+"-01 00:00:00";
            }
//            System.out.println("AAA:"+time1+"  "+time2);

            Map map_t=new HashMap();
            map_t.put("time1",time1);
            map_t.put("time2",time2);
            map_t.put("house_region","成华");
            //上拍数量
            int count=dataAnalysisMapper.selectCountByMonthByHouseRegion(map_t);
            int chengjiao=dataAnalysisMapper.selectCountChengjiaoByMonthByHouseRegion(map_t);

            yuefeng_array.add(i+"月");
            shangpai_array.add(count);
            chengjiao_array.add(chengjiao);
//            resMap.put(i+"月份上拍总数",count);
//            resMap.put(i+"月份成交总数",chengjiao);
        }
        resMap.put("yuefeng_array",yuefeng_array);
        resMap.put("shangpai_array",shangpai_array);
        resMap.put("chengjiao_array",chengjiao_array);
        String res=null;
        try {
            res = JSON.toJSONString(resMap);

        }catch (Exception e){
            res="封装json失败!";
            System.out.println("封装json失败!");
        }
        return res;
    }



    @Override
    public String selectDataToBiao18() {
        Map resMap=new HashMap();
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) ;
        List<String> yuefeng_array=new ArrayList<String>();
        List<Integer> shangpai_array=new ArrayList<Integer>();
        List<Integer> chengjiao_array=new ArrayList<Integer>();
        for(int i=1;i<=month;i++){
            String time1="";
            String time2="";
            if(i<=9){
                time1=year+"-0"+i+"-01 00:00:00";
            }
            else{
                time1=year+"-"+i+"-01 00:00:00";
            }
            int m2=i+1;
            int y2=year;
            if(m2>12){
                y2=year+1;
                m2=1;
            }
            if(m2<=9){
                time2=y2+"-0"+m2+"-01 00:00:00";
            }
            else{
                time2=y2+"-"+m2+"-01 00:00:00";
            }
//            System.out.println("AAA:"+time1+"  "+time2);

            Map map_t=new HashMap();
            map_t.put("time1",time1);
            map_t.put("time2",time2);
            map_t.put("house_region","双流");
            //上拍数量
            int count=dataAnalysisMapper.selectCountByMonthByHouseRegion(map_t);
            int chengjiao=dataAnalysisMapper.selectCountChengjiaoByMonthByHouseRegion(map_t);

            yuefeng_array.add(i+"月");
            shangpai_array.add(count);
            chengjiao_array.add(chengjiao);
//            resMap.put(i+"月份上拍总数",count);
//            resMap.put(i+"月份成交总数",chengjiao);
        }
        resMap.put("yuefeng_array",yuefeng_array);
        resMap.put("shangpai_array",shangpai_array);
        resMap.put("chengjiao_array",chengjiao_array);
        String res=null;
        try {
            res = JSON.toJSONString(resMap);

        }catch (Exception e){
            res="封装json失败!";
            System.out.println("封装json失败!");
        }
        return res;
    }



    @Override
    public String selectDataToBiao19() {
        Map resMap=new HashMap();
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) ;
        List<String> yuefeng_array=new ArrayList<String>();
        List<Integer> shangpai_array=new ArrayList<Integer>();
        List<Integer> chengjiao_array=new ArrayList<Integer>();
        for(int i=1;i<=month;i++){
            String time1="";
            String time2="";
            if(i<=9){
                time1=year+"-0"+i+"-01 00:00:00";
            }
            else{
                time1=year+"-"+i+"-01 00:00:00";
            }
            int m2=i+1;
            int y2=year;
            if(m2>12){
                y2=year+1;
                m2=1;
            }
            if(m2<=9){
                time2=y2+"-0"+m2+"-01 00:00:00";
            }
            else{
                time2=y2+"-"+m2+"-01 00:00:00";
            }
//            System.out.println("AAA:"+time1+"  "+time2);

            Map map_t=new HashMap();
            map_t.put("time1",time1);
            map_t.put("time2",time2);
            map_t.put("house_region","高新");
            //上拍数量
            int count=dataAnalysisMapper.selectCountByMonthByHouseRegion(map_t);
            int chengjiao=dataAnalysisMapper.selectCountChengjiaoByMonthByHouseRegion(map_t);

            yuefeng_array.add(i+"月");
            shangpai_array.add(count);
            chengjiao_array.add(chengjiao);
//            resMap.put(i+"月份上拍总数",count);
//            resMap.put(i+"月份成交总数",chengjiao);
        }
        resMap.put("yuefeng_array",yuefeng_array);
        resMap.put("shangpai_array",shangpai_array);
        resMap.put("chengjiao_array",chengjiao_array);
        String res=null;
        try {
            res = JSON.toJSONString(resMap);

        }catch (Exception e){
            res="封装json失败!";
            System.out.println("封装json失败!");
        }
        return res;
    }


    @Override
    public String selectDataToBiao20() {
        Map resMap=new HashMap();
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) ;
        List<String> yuefeng_array=new ArrayList<String>();
        List<Integer> shangpai_array=new ArrayList<Integer>();
        List<Integer> chengjiao_array=new ArrayList<Integer>();
        for(int i=1;i<=month;i++){
            String time1="";
            String time2="";
            if(i<=9){
                time1=year+"-0"+i+"-01 00:00:00";
            }
            else{
                time1=year+"-"+i+"-01 00:00:00";
            }
            int m2=i+1;
            int y2=year;
            if(m2>12){
                y2=year+1;
                m2=1;
            }
            if(m2<=9){
                time2=y2+"-0"+m2+"-01 00:00:00";
            }
            else{
                time2=y2+"-"+m2+"-01 00:00:00";
            }
//            System.out.println("AAA:"+time1+"  "+time2);

            Map map_t=new HashMap();
            map_t.put("time1",time1);
            map_t.put("time2",time2);
            map_t.put("house_region","天府新");
            //上拍数量
            int count=dataAnalysisMapper.selectCountByMonthByHouseRegion(map_t);
            int chengjiao=dataAnalysisMapper.selectCountChengjiaoByMonthByHouseRegion(map_t);

            yuefeng_array.add(i+"月");
            shangpai_array.add(count);
            chengjiao_array.add(chengjiao);
//            resMap.put(i+"月份上拍总数",count);
//            resMap.put(i+"月份成交总数",chengjiao);
        }
        resMap.put("yuefeng_array",yuefeng_array);
        resMap.put("shangpai_array",shangpai_array);
        resMap.put("chengjiao_array",chengjiao_array);
        String res=null;
        try {
            res = JSON.toJSONString(resMap);

        }catch (Exception e){
            res="封装json失败!";
            System.out.println("封装json失败!");
        }
        return res;
    }



    @Override
    public String selectDataToBiao21() {
        Map resMap=new HashMap();
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) ;
        List<String> yuefeng_array=new ArrayList<String>();
        List<Integer> shangpai_array=new ArrayList<Integer>();
        List<Integer> chengjiao_array=new ArrayList<Integer>();
        for(int i=1;i<=month;i++){
            String time1="";
            String time2="";
            if(i<=9){
                time1=year+"-0"+i+"-01 00:00:00";
            }
            else{
                time1=year+"-"+i+"-01 00:00:00";
            }
            int m2=i+1;
            int y2=year;
            if(m2>12){
                y2=year+1;
                m2=1;
            }
            if(m2<=9){
                time2=y2+"-0"+m2+"-01 00:00:00";
            }
            else{
                time2=y2+"-"+m2+"-01 00:00:00";
            }
//            System.out.println("AAA:"+time1+"  "+time2);

            Map map_t=new HashMap();
            map_t.put("time1",time1);
            map_t.put("time2",time2);
            map_t.put("house_region","龙泉驿");
            //上拍数量
            int count=dataAnalysisMapper.selectCountByMonthByHouseRegion(map_t);
            int chengjiao=dataAnalysisMapper.selectCountChengjiaoByMonthByHouseRegion(map_t);

            yuefeng_array.add(i+"月");
            shangpai_array.add(count);
            chengjiao_array.add(chengjiao);
//            resMap.put(i+"月份上拍总数",count);
//            resMap.put(i+"月份成交总数",chengjiao);
        }
        resMap.put("yuefeng_array",yuefeng_array);
        resMap.put("shangpai_array",shangpai_array);
        resMap.put("chengjiao_array",chengjiao_array);
        String res=null;
        try {
            res = JSON.toJSONString(resMap);

        }catch (Exception e){
            res="封装json失败!";
            System.out.println("封装json失败!");
        }
        return res;
    }




    @Override
    public String selectDataToBiao22() {
        Map resMap=new HashMap();
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) ;
        List<String> yuefeng_array=new ArrayList<String>();
        List<Integer> shangpai_array=new ArrayList<Integer>();
        List<Integer> chengjiao_array=new ArrayList<Integer>();
        for(int i=1;i<=month;i++){
            String time1="";
            String time2="";
            if(i<=9){
                time1=year+"-0"+i+"-01 00:00:00";
            }
            else{
                time1=year+"-"+i+"-01 00:00:00";
            }
            int m2=i+1;
            int y2=year;
            if(m2>12){
                y2=year+1;
                m2=1;
            }
            if(m2<=9){
                time2=y2+"-0"+m2+"-01 00:00:00";
            }
            else{
                time2=y2+"-"+m2+"-01 00:00:00";
            }
//            System.out.println("AAA:"+time1+"  "+time2);

            Map map_t=new HashMap();
            map_t.put("time1",time1);
            map_t.put("time2",time2);
            map_t.put("house_region","温江");
            //上拍数量
            int count=dataAnalysisMapper.selectCountByMonthByHouseRegion(map_t);
            int chengjiao=dataAnalysisMapper.selectCountChengjiaoByMonthByHouseRegion(map_t);

            yuefeng_array.add(i+"月");
            shangpai_array.add(count);
            chengjiao_array.add(chengjiao);
//            resMap.put(i+"月份上拍总数",count);
//            resMap.put(i+"月份成交总数",chengjiao);
        }
        resMap.put("yuefeng_array",yuefeng_array);
        resMap.put("shangpai_array",shangpai_array);
        resMap.put("chengjiao_array",chengjiao_array);
        String res=null;
        try {
            res = JSON.toJSONString(resMap);

        }catch (Exception e){
            res="封装json失败!";
            System.out.println("封装json失败!");
        }
        return res;
    }




    @Override
    public String selectDataToBiao23() {
        Map resMap=new HashMap();
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) ;
        List<String> yuefeng_array=new ArrayList<String>();
        List<Integer> shangpai_array=new ArrayList<Integer>();
        List<Integer> chengjiao_array=new ArrayList<Integer>();
        for(int i=1;i<=month;i++){
            String time1="";
            String time2="";
            if(i<=9){
                time1=year+"-0"+i+"-01 00:00:00";
            }
            else{
                time1=year+"-"+i+"-01 00:00:00";
            }
            int m2=i+1;
            int y2=year;
            if(m2>12){
                y2=year+1;
                m2=1;
            }
            if(m2<=9){
                time2=y2+"-0"+m2+"-01 00:00:00";
            }
            else{
                time2=y2+"-"+m2+"-01 00:00:00";
            }
//            System.out.println("AAA:"+time1+"  "+time2);

            Map map_t=new HashMap();
            map_t.put("time1",time1);
            map_t.put("time2",time2);
            map_t.put("house_region","郫");
            //上拍数量
            int count=dataAnalysisMapper.selectCountByMonthByHouseRegion(map_t);
            int chengjiao=dataAnalysisMapper.selectCountChengjiaoByMonthByHouseRegion(map_t);

            yuefeng_array.add(i+"月");
            shangpai_array.add(count);
            chengjiao_array.add(chengjiao);
//            resMap.put(i+"月份上拍总数",count);
//            resMap.put(i+"月份成交总数",chengjiao);
        }
        resMap.put("yuefeng_array",yuefeng_array);
        resMap.put("shangpai_array",shangpai_array);
        resMap.put("chengjiao_array",chengjiao_array);
        String res=null;
        try {
            res = JSON.toJSONString(resMap);

        }catch (Exception e){
            res="封装json失败!";
            System.out.println("封装json失败!");
        }
        return res;
    }
}

