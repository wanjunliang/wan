package com.neusoft.smis.common.entity.mysql.dataAnalysis;

import lombok.Data;

/**
 * Created by Administrator on 2018/9/21.
 * 分数分析平台,第7张图表里的
 * 前5日上拍和成交数据的类
 */
@Data
public class FiveDayData {
    //日期
    private String date;
    //今日上拍套数
    private int shangpaitaoshu;
    //今日成交套数
    private int chengjiaotaoshu;
    //今日上拍金额
    private int shangpaijine;
    //今日成交金额
    private int chengjiaojine;
}
