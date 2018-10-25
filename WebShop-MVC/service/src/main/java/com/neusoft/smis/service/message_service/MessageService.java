package com.neusoft.smis.service.message_service;

import com.neusoft.smis.common.entity.mysql.message.Message;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6.
 */
public interface MessageService {
    //动态新增数据库表message的数据
    String insertMessage(Message message);
    //查询信息,通过司法拍卖的标的物的ID和消息时间
    List<Message> selectMessageListBytdzyf_sifa_paimai_data_idAndsend_time(Message message);

}
