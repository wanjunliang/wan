package com.neusoft.smis.dao.mysql.message;

import com.neusoft.smis.common.entity.mysql.message.Message;

import java.util.List;

/**
 * Created by Administrator on 2018/3/1.
 *
 */
public interface MessageMapper {
    //动态新增
    int insertMessage(Message message);
    //查询信息,通过司法拍卖的标的物的ID和消息时间,按照send_time升序排列
    List<Message> selectMessageListBytdzyf_sifa_paimai_data_idAndsend_time(Message message);
}
