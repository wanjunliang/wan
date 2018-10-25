package com.neusoft.smis.webapp.task_controller;

import com.alibaba.fastjson.JSON;
import com.neusoft.smis.common.entity.mysql.task.RobTask;
import com.neusoft.smis.webapp.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;

import java.util.List;

/**
 * Created by Administrator on 2018/3/20.
 * 抢任务的控制层
 */
@Controller
@RequestMapping("/robTaskController")
public class RobTaskController extends BaseController {

    /***
     * 新增任务
     * 必须参数:抢任务的人,user_id
     * 必须参数:司法拍卖的数据的ID,tdzyf_sifa_paimai_id
     * @param robTask
     * @return
     * 返回是否成功的标志:成功,失败,已结被抢了
     * flag:success,fail,have been robbed
     */
    @RequestMapping(value = "/insertRobTask",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String insertRobTask(RobTask robTask){
        String flag=robTaskServcie.insertRobTask(robTask);
        ModelAndView mav=new ModelAndView();
        mav.addObject("flag",flag);
        String res="";
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }
    /***
     * 判断是否被人抢
     *必须参数:
     * user_id:当前登录用户的id
     *tdzyf_sifa_paimai_id:数据的id
     * 返回:
     * flag:还没有人抢,被自己抢了,被别人抢了,异常
     */
    @RequestMapping(value = "/selectRobTaskIfRobbed",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String selectRobTaskIfRobbed(RobTask robTask){
        String flag=robTaskServcie.selectRobTaskIfRobbed(robTask);
        ModelAndView mav=new ModelAndView();
        mav.addObject("flag",flag);
        String res="";
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }

    /***
     * 查询所有抢到的任务通过用户的ID,抢到的任务按照抢到的时间逆序排序
     * 必须传入用户的ID
     * @param robTask
     * @return
     * flag:success,fail
     * robTaskList:抢到的任务的列表
     */
    @RequestMapping(value = "/selectRobTaskListByUserId",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String selectRobTaskListByUserId(RobTask robTask){
        List<RobTask> robTaskList=robTaskServcie.selectRobTaskListByUserId(robTask);
        String flag="success";
        if(robTaskList==null){
            flag="fail";
        }
        ModelAndView mav=new ModelAndView();
        mav.addObject("flag",flag);
        mav.addObject("robTaskList",robTaskList);
        String res="";
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }
}
