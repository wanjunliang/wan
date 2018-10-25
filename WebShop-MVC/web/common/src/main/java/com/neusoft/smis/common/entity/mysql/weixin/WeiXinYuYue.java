package com.neusoft.smis.common.entity.mysql.weixin;

import lombok.Data;

/**
 * Created by Administrator on 2018/6/12.
 * 本类是微信端预约的实体类
 * mysql对应的表是weixin_yuyue表
 */
@Data
public class WeiXinYuYue {
    private int id;//表weixin_yuyue的主键
    private String openid;//微端的开放ID
    private int tdzyf_sifa_paimai_id;//表tdzyf_sifa_paimai的主键
    private String house_name;//表tdzyf_sifa_paimai的房产名字
    private String name;//预约人的名字
    private String phone;//联系电话
    private String time;//新增本条数据的时间
}
