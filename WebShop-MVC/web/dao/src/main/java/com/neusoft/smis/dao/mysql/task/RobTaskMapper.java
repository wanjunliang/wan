package com.neusoft.smis.dao.mysql.task;

import com.neusoft.smis.common.entity.mysql.task.RobTask;

import java.util.List;

/**
 * Created by Administrator on 2018/3/20.
 */
public interface RobTaskMapper {
    //动态的新增 抢任务
    int insertRobTask(RobTask robTask);
    //动态查询,可以根据rob_task_id,user_id,tdzyf_sifa_paimai_id,state
    List<RobTask> selectRobTaskList(RobTask robTask);
}
