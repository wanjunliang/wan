package com.neusoft.smis.common.entity.mysql.task;

import lombok.Data;

/**
 * Created by Administrator on 2018/3/20.
 * 抢任务的实体类
 *
 * 管理员和总经理拥有查看tdzyf_sifa_paimai表的权限
 * 或者抢单的那个人和被分享数据的人拥有查看权限
 */
@Data
public class RobTask {
    private int rob_task_id;//抢任务表的ID
    private int user_id;//抢到任务的人,也就是执行任务的人的ID
    private int tdzyf_sifa_paimai_id;//司法拍卖的数据的ID
    private String rob_task_time;//抢到任务的时间
    private String finish_time;//结束时间,就是完成任务的时间
    private String state;//标记任务的状态:执行中,已完成,放弃了,已删除


    //自定义的字段
    private String house_name;//标的物的名字
}
