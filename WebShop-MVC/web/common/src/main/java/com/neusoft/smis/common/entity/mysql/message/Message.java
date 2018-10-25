package com.neusoft.smis.common.entity.mysql.message;

import lombok.Data;

/**
 * Created by Administrator on 2018/3/1.
 * 信息实体类对应数据库的表message
 * message表在android系统里面的具体的某个标的物的工作里面展示,展示文本图片语音视频
 */
@Data
public class Message {
    private int message_id;
    private int sender_id;//发送人的ID
    private int tdzyf_sifa_paimai_data_id;//司法拍卖的数据的ID
    private String type;//信息的类型:文本,图片,语音,视频
    private String message_text;//文本信息
    private String picture_name;//图片文件名字
    private String voice_name;//语音文件名字
    private String video_name;//视频文件名字
    private String send_time;//发送时间
    private int state;//状态标记是否删除:0,正常状态,1,删除状态

    private String user_name;//姓名
    private String img_src_head;//用户的头像名字
}
