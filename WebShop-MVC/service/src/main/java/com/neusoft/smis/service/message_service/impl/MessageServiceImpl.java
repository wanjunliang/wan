package com.neusoft.smis.service.message_service.impl;

import com.neusoft.smis.common.entity.mysql.User;
import com.neusoft.smis.common.entity.mysql.message.Message;
import com.neusoft.smis.dao.mysql.message.MessageMapper;
import com.neusoft.smis.dao.mysql.user.UserMapper;
import com.neusoft.smis.service.message_service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/3/6.
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService{
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public String insertMessage(Message message) {
        if(message==null){
            return "fail";
        }
        //判断是否有为有发送人ID和标的物的ID
        if(message.getSender_id()<=0){
            return "fail";
        }
        if(message.getTdzyf_sifa_paimai_data_id()<=0){
            return "fail";
        }
        //判断类型,新增的这条消息的什么类型的
        if(message.getMessage_text()!=null&&"".equals(message.getMessage_text())==false){
            message.setType("文本");
        }
        else if(message.getPicture_name()!=null&&"".equals(message.getPicture_name())==false){
            message.setType("图片");
        }
        else if(message.getVoice_name()!=null&&"".equals(message.getVoice_name())==false){
            message.setType("语音");
        }
        else if(message.getVideo_name()!=null&&"".equals(message.getVideo_name())==false){
            message.setType("视频");
        }
        else{
            return "fail";
        }
        //动态新增
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date day=new Date();
        message.setSend_time(df.format(day));
        message.setState(0);
        int re=messageMapper.insertMessage(message);
        String flag="success";
        if(re==0){
            flag="fail";
        }
        return flag;
    }

    @Override
    public List<Message> selectMessageListBytdzyf_sifa_paimai_data_idAndsend_time(Message message) {
        List<Message> messageList=messageMapper.selectMessageListBytdzyf_sifa_paimai_data_idAndsend_time(message);
        //遍历根据sender_id查找user表的姓名和头像图片名字
        for(int i=0;i<messageList.size();i++){
            Message m=messageList.get(i);
            int user_id=m.getSender_id();
            User user=new User();
            user.setUser_id(user_id);
            List<User> userList=userMapper.selectUser(user);
            if(userList.size()==1){
                User re_user=userList.get(0);
                m.setUser_name(re_user.getUser_name());
                m.setImg_src_head(re_user.getImg_src_head());
            }
        }

        return messageList;
    }
}
