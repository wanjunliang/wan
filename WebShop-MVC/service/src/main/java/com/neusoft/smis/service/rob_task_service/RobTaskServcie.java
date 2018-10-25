package com.neusoft.smis.service.rob_task_service;

import com.neusoft.smis.common.entity.mysql.task.RobTask;

import java.util.List;

/**
 * Created by Administrator on 2018/3/20.
 */
public interface RobTaskServcie {
    //新增抢到的任务
    String insertRobTask(RobTask robTask);
    //查询判断是否被人抢
    String selectRobTaskIfRobbed(RobTask robTask);

    List<RobTask> selectRobTaskListByUserId(RobTask robTask);
}
