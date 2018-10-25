package com.neusoft.smis.common.entity.mysql;

import lombok.Data;

/**
 * Created by Administrator on 2018/10/17.
 * 表tdzyf_sifa_paimai_img
 * 图片表
 */
@Data
public class TdzyfSifaPaimaiImg {
    private int tdzyf_sifa_paimai_img_id;//主键
    private String name;//图片名字
    private String url;//tdzyf_sifa_paimai的网址,url
}
