package com.neusoft.smis.common.entity.mongodb;



import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * Created by WanJunliang on 2017/5/16.
 */
//爬虫的数据
@Document(collection = "detail_info")
@Data
public class Detail_info {
    @Id
    private String id;                   //id
    private String auction_price;   //  起拍价
    private String house_area;          //面积
    private String house_unit_price;   //单价
    private String house_name;         //房屋名称描述
    private String house_proclaimtion_time;  //挂网时间
//    private String house_price;         //总价
//    private String house_number;     //门牌号
    private String house_type;       //房屋类型
    private String house_start_time;   //开拍时间
    private String house_region;     //房屋地区

    private String period;   //  竞价周期
//    private String item_md5;   //  MD5校验码
    private String current_price;   //  当前价
    private String pay_type;   //  类型
    private String preemption;   //  优先购买权人
    private String delayed_period;   //  延时周期
    private String evaluate;   //  评估价
    private String add_price;   //  加价幅度
    private String item_url;   // 第一次数据来源的url地址
    private String current_status;   //  当前状态
    private String deposit;   //  保证金

//    private String auction_notice_page;//          html的源码
//    private String notice_page;       //          html的源码
//    private String house_presentation_page; //     html的源码

    private String bargained_price;    //成交价格
    private String bargained_time;    //成交时间
    private String save_time;    //保存到mongodb的时间  第一次保存的时间

    private String court_name;  //法院名称
    private String postcode;   //邮编
    private String flag_rmfysszc_com; //标志是否人名法院诉讼司法资产网有这条书,有,无
    private String latest_update_time;//最后的更新时间
    private String url_sf_taobao_com;//司法淘宝网的url地址,如果在司法淘宝网有这条数据的话,就会有
    private String flag_sf_taobao_com;//标志是否司法淘宝网有这条数据,有,无
    private String data_source;//数据来源,这是第一次该条数据的来源
    private String url_rmfysszc_com;//任命法院司法诉讼资产网的url地址,如果有的话就有
}
