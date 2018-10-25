package com.neusoft.smis.service.rob_task_service.impl;


import com.neusoft.smis.common.entity.mysql.TdzyfSifaPaimai;
import com.neusoft.smis.common.entity.mysql.task.RobTask;
import com.neusoft.smis.dao.mysql.task.RobTaskMapper;
import com.neusoft.smis.dao.mysql.tdzyf.TdzyfSifaPaimaiMapper;
import com.neusoft.smis.service.rob_task_service.RobTaskServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/3/20.
 */
@Service("robTaskService")
public class RobTaskServiceImpl implements RobTaskServcie{
    @Autowired
    RobTaskMapper robTaskMapper;
    @Autowired
    TdzyfSifaPaimaiMapper tdzyfSifaPaimaiMapper;

    @Override
    public String insertRobTask(RobTask robTask) {
        if(robTask.getUser_id()==0||robTask.getTdzyf_sifa_paimai_id()==0){
            return "fail";//失败
        }
        //先查看是否有人已经抢了这个任务,通过
        //必须参数:抢任务的人,user_id
        // 必须参数:司法拍卖的数据的ID,tdzyf_sifa_paimai_id
        RobTask robTask1=new RobTask();
        robTask1.setTdzyf_sifa_paimai_id(robTask.getTdzyf_sifa_paimai_id());
        List<RobTask> robTaskList=robTaskMapper.selectRobTaskList(robTask1);
        if(robTaskList!=null&&robTaskList.size()>0){
            //如果有就遍历,如果有人正在执行或者已完成就不能抢
            for(RobTask r:robTaskList){
                if("执行中".equals(r.getState())||"已完成".equals(r.getState())){
                    return "have been robbed";//已结被抢了
                }
            }
        }
        //没有人抢占,那么就可以新增,先设置时间和状态
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        robTask.setRob_task_time(simpleDateFormat.format(new Date()));
        robTask.setState("执行中");
        int re=robTaskMapper.insertRobTask(robTask);
        if(re==1) {
            return "success";
        }
        else{
            return "fail";
        }
    }

    @Override
    public String selectRobTaskIfRobbed(RobTask robTask) {
        if(robTask.getUser_id()==0||robTask.getTdzyf_sifa_paimai_id()==0){
            return "异常";//异常
        }
        RobTask robTask1=new RobTask();
        robTask1.setTdzyf_sifa_paimai_id(robTask.getTdzyf_sifa_paimai_id());
        List<RobTask> robTaskList=robTaskMapper.selectRobTaskList(robTask1);
        if(robTaskList==null||robTaskList.size()==0){//没有的情况,那么肯定没被抢
            return "还没有人抢";
        }
        else{//遍历
            for(RobTask r:robTaskList){
                if("执行中".equals(r.getState())||"已完成".equals(r.getState())){//判断出被人抢了
                    if(r.getUser_id()== robTask.getUser_id()){
                        return "被自己抢了";//被我抢了
                    }
                    else{
                        return "被别人抢了";//被别人抢了
                    }
                }
            }
        }
        return "异常";
    }

    @Override
    public List<RobTask> selectRobTaskListByUserId(RobTask robTask) {
        if(robTask==null||robTask.getUser_id()==0){
            return null;
        }
        //执行中的列表
        List<RobTask> robTaskList_zhixingzhong=new ArrayList<>();
        //其他的列表
        List<RobTask> robTaskList_qita=new ArrayList<>();
        List<RobTask> robTaskList=robTaskMapper.selectRobTaskList(robTask);
        //排序,执行中的在前面
        //按照抢单时间逆序排列
        for(RobTask r:robTaskList){
            if("执行中".equals(r.getState())){
                robTaskList_zhixingzhong.add(r);
            }
            else{
                robTaskList_qita.add(r);
            }
        }
        for(int i=0;i<robTaskList_zhixingzhong.size();i++){
            for(int j=1;j<robTaskList_zhixingzhong.size()-i;j++){
                //时间逆序
                if(robTaskList_zhixingzhong.get(j-1).getRob_task_time().compareTo(robTaskList_zhixingzhong.get(j).getRob_task_time())<0){
                    //交换
                    RobTask temp=new RobTask();
                    temp.setRob_task_id(robTaskList_zhixingzhong.get(j-1).getRob_task_id());
                    temp.setUser_id(robTaskList_zhixingzhong.get(j-1).getUser_id());
                    temp.setTdzyf_sifa_paimai_id(robTaskList_zhixingzhong.get(j-1).getTdzyf_sifa_paimai_id());
                    temp.setRob_task_time(robTaskList_zhixingzhong.get(j-1).getRob_task_time());
                    temp.setFinish_time(robTaskList_zhixingzhong.get(j-1).getFinish_time());
                    temp.setState(robTaskList_zhixingzhong.get(j-1).getState());

                    robTaskList_zhixingzhong.get(j-1).setRob_task_id(robTaskList_zhixingzhong.get(j).getRob_task_id());
                    robTaskList_zhixingzhong.get(j-1).setUser_id(robTaskList_zhixingzhong.get(j).getUser_id());
                    robTaskList_zhixingzhong.get(j-1).setTdzyf_sifa_paimai_id(robTaskList_zhixingzhong.get(j).getTdzyf_sifa_paimai_id());
                    robTaskList_zhixingzhong.get(j-1).setRob_task_time(robTaskList_zhixingzhong.get(j).getRob_task_time());
                    robTaskList_zhixingzhong.get(j-1).setFinish_time(robTaskList_zhixingzhong.get(j).getFinish_time());
                    robTaskList_zhixingzhong.get(j-1).setState(robTaskList_zhixingzhong.get(j).getState());

                    robTaskList_zhixingzhong.get(j).setRob_task_id(temp.getRob_task_id());
                    robTaskList_zhixingzhong.get(j).setUser_id(temp.getUser_id());
                    robTaskList_zhixingzhong.get(j).setTdzyf_sifa_paimai_id(temp.getTdzyf_sifa_paimai_id());
                    robTaskList_zhixingzhong.get(j).setRob_task_time(temp.getRob_task_time());
                    robTaskList_zhixingzhong.get(j).setFinish_time(temp.getFinish_time());
                    robTaskList_zhixingzhong.get(j).setState(temp.getState());
                }
            }
        }

        for(int i=0;i<robTaskList_qita.size();i++){
            for(int j=1;j<robTaskList_qita.size()-i;j++){
                //时间逆序
                if(robTaskList_qita.get(j-1).getRob_task_time().compareTo(robTaskList_qita.get(j).getRob_task_time())<0){
                    //交换
                    RobTask temp=new RobTask();
                    temp.setRob_task_id(robTaskList_qita.get(j-1).getRob_task_id());
                    temp.setUser_id(robTaskList_qita.get(j-1).getUser_id());
                    temp.setTdzyf_sifa_paimai_id(robTaskList_qita.get(j-1).getTdzyf_sifa_paimai_id());
                    temp.setRob_task_time(robTaskList_qita.get(j-1).getRob_task_time());
                    temp.setFinish_time(robTaskList_qita.get(j-1).getFinish_time());
                    temp.setState(robTaskList_qita.get(j-1).getState());

                    robTaskList_qita.get(j-1).setRob_task_id(robTaskList_qita.get(j).getRob_task_id());
                    robTaskList_qita.get(j-1).setUser_id(robTaskList_qita.get(j).getUser_id());
                    robTaskList_qita.get(j-1).setTdzyf_sifa_paimai_id(robTaskList_qita.get(j).getTdzyf_sifa_paimai_id());
                    robTaskList_qita.get(j-1).setRob_task_time(robTaskList_qita.get(j).getRob_task_time());
                    robTaskList_qita.get(j-1).setFinish_time(robTaskList_qita.get(j).getFinish_time());
                    robTaskList_qita.get(j-1).setState(robTaskList_qita.get(j).getState());

                    robTaskList_qita.get(j).setRob_task_id(temp.getRob_task_id());
                    robTaskList_qita.get(j).setUser_id(temp.getUser_id());
                    robTaskList_qita.get(j).setTdzyf_sifa_paimai_id(temp.getTdzyf_sifa_paimai_id());
                    robTaskList_qita.get(j).setRob_task_time(temp.getRob_task_time());
                    robTaskList_qita.get(j).setFinish_time(temp.getFinish_time());
                    robTaskList_qita.get(j).setState(temp.getState());
                }
            }
        }
        List<RobTask> res=new ArrayList<>();
        res.addAll(robTaskList_zhixingzhong);
        res.addAll(robTaskList_qita);

        //把自定义的字段补齐
        for(RobTask r:res){
            TdzyfSifaPaimai tdzyfSifaPaimai=new TdzyfSifaPaimai();
            tdzyfSifaPaimai.setId(r.getTdzyf_sifa_paimai_id());
            List<TdzyfSifaPaimai> tdzyfSifaPaimai_list=tdzyfSifaPaimaiMapper.selectTdzyfSifaPaimaiByHouseName(tdzyfSifaPaimai);
            if(tdzyfSifaPaimai_list!=null&&tdzyfSifaPaimai_list.size()==1){
                TdzyfSifaPaimai TdzyfSifaPaimai_1=tdzyfSifaPaimai_list.get(0);
                r.setHouse_name(TdzyfSifaPaimai_1.getHouse_name());
            }
        }
        return res;
    }
}
