package com.neusoft.smis.common.entity.mysql;

import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2017/11/21.
 */
@Data
public class TdzyfSifaPaimai {
    private int id;
    private String house_name;     //房屋名称描述,也是地址
    private double current_price;  //当前价
    private double auction_price;  //起拍价
    private double bargained_price;//成交价
    private double evaluate;//评估价
    private String url;//网址
    private String current_status;//当前状态
    private String house_region;//地区
    private String house_type;//房屋类型:住宅,商业
    private double house_unit_price;//单价
    private double house_area;//面积
    private Date house_proclaimtion_time;//公告时间挂网时间
    private Date house_start_time;//开拍时间
    private Date bargained_time;//成交时间
    private Date save_time;//保存时间
    private String court_name;//法院名称
    private String data_source;//数据来源
    private Date latest_update_time;//最后更新时间
    private double viewerCount;//围观人数
    private double applyCount;//报名人数
    private double warnCount;//提醒人数
    private String auction_stage;//竞拍阶段,一拍,二拍三拍,变卖,重新拍卖
    private Date auction_end_time;//结束时间
    private String community_name;//小区名字(从自己的数据库,从百度从高德得到的小区名字)
    private String lian_jia_data_id;//链家网的data_id
    private String lianjia_community_name;//链家中查找的小区名字
    private String lianjia_community_address;//链家查找的小区的地址
    private String lianjia_community_building_age;//链家查找小区的建成年代
    private String lianjia_community_developer;//链家小区的开发商
    private String lianjia_community_total_number_of_building;//链家查找的小区的总栋数
    private double lianjia_average_price;//链家网该小区交易均价
    private String lianjia_reference_time;//链家网该小区参考时间
    private String lianjia_chengjiao_name;//链家网最新的一条成交的名字
    private double lianjia_chengjiao_unitPrice;//链家最新成交一条记录的单价
    private Date lianjia_chengjiao_dealDate;//链家最新成交一条记录的成交日期
    private double lianjia_chengjiao_chengjiao_price;//链家最新成交一条记录的成交价
    private double lianjia_chengjiao_number;//链家网最近三个月成交量
    private String lianjia_guawang_name;//链家该小区最新的一条挂网信息的名字
    private double lianjia_guawang_unitPrice;//链家最新挂网的一条记录的单价
    private double lianjia_guawang_area;//链家最新挂网一条记录的面积
    private double lianjia_guawang_price;//链家最新挂网一条记录的挂网价
    private String fang_tian_xia_projcodeid;//房天下的projcodeid
    private String fang_tian_xia_community_name;//房天下中查找的小区名字
    private String fang_tian_xia_community_building_age;//房天下中查找的小区的建筑年代
    private String fang_tian_xia_community_developer;//房天下中查找的小区的开发商
    private String fang_tian_xia_total_number_of_building;//房天下中查找的小区的总栋数
    private double fangtianxia_average_price;//房天下该小区的交易均价
    private String fangtianxia_reference_time;//房天下该小区均价参考时间
    private String fangtianxia_active;//房天下中该小区的活跃情况
    private String fangtianxia_chengjiao_house_type;//房天下该小区的成交一条记录的户型
    private double fangtianxia_chengjiao_unit_price;//房天下最新成交的单价
    private Date fangtianxia_chengjiao_time;//房天下最新成交一条记录的时间
    private double fangtianxia_chengjiao_area;//房天下最新成交的面积
    private double fangtianxia_chengjiao_price;//房天下最新成交价格
    private double fangtianxia_chengjiao_number;//房天下最近三个月成交数量
    private double fangtianxia_guawang_unit_price;//房天下最新挂网的单价
    private double fangtianxia_guawang_area;//房天下最新挂网的面积
    private double fangtianxia_guawang_price;//房天下最新挂网总价
    private String kindergarten;//最近的幼儿园
    private String primary_school;//最近的小学
    private String middle_school;//最近的中学
    private String hospital;//最近的医院
    private double chengben_hesuan;//成本核算(住宅系数1.116,商业系数1.177)
    private double chengben_unit_price;//成本单价
    private double xiaoshou_hesuan;//销售核算
    private double xiaoshou_unit_price;//销售单价
    private double percentage_increase;//涨幅百分比=(成交价-起拍价)/起拍价
    private String bd_lat;//百度坐标:纬度
    private String bd_lng;//百度坐标:经度
    private String metro_name;//最近的地铁站名字
    private String metro_line;//地铁站经过的线路
    private String metro_status;//地铁站当前状态
    private String metro_remark;//地铁站的备注说明描述

//    2018年10月17日新增
    private String bidder;//竞买人
    private String transaction_if_complete;//最新交易是否完成,是否履行支付尾款,可能竞买人会悔拍,如果没有交易完成只写确定交易未履行
    private String transaction_the_latest_remark;//如果确定交易未履行,这里备注情况说明
    private String contact_unit_person;//联系单位或者法官人,一般是助拍公司
    private String contact_line_1;//联系第一个电话
    private String contact_line_2;//联系第二个电话
    private String lock_house_area;//锁面积,如果是1,表明,已经人为修改过了,不能爬虫程序修改
    private String gaode_lng;//高德的经度
    private String gaode_lat;//高德纬度
    private String taobao_id;//标的物在淘宝数据库的ID
    private String taobao_title;//淘宝数据库中存放的地址,标的物的标题,会有些符号&ldquo等等
    private String pic_name;//主图,标的物的主图片



}
