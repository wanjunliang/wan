package com.neusoft.smis.webapp.tdzyfSifaPaimaiController;

import com.alibaba.fastjson.JSON;
import com.neusoft.smis.common.entity.mysql.TdzyfSifaPaimai;
import com.neusoft.smis.webapp.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;

import java.util.List;

/**
 * 本控制层,应用于安卓系统的
 * Created by Administrator on 2018/1/17.
 */
@Controller
@RequestMapping("/androidTdzyfSifaPaimaiTableController")
public class AndroidTdzyfSifaPaimaiTableController extends BaseController{

    /***
     * 传入两个时间比如2018-01-16 00:00:00到2018-01-17 00:00:00,还有房屋的类型
     * 查询在这两个时间之间挂网的数据并且支持房屋类型的选择house_type_list
     * 比如http://localhost:8081/tdzyf/androidTdzyfSifaPaimaiTableController/selectTdzyfSifaPaimaiByhouse_proclaimtion_time?time_1=2018-01-17%2000:00:00&time_2=2018-01-18%2000:00:00&house_type_list[]=商业&house_type_list[]=办公
     * 阿里云服务地址:http://www.tdzyf.xin/tdzyf/androidTdzyfSifaPaimaiTableController/selectTdzyfSifaPaimaiByhouse_proclaimtion_time?time_1=2018-01-17%2000:00:00&time_2=2018-01-18%2000:00:00&house_type_list=商业
     * @param time_1,time_2,house_type_list
     * @return json数据
     */
    @RequestMapping(value = "/selectTdzyfSifaPaimaiByhouse_proclaimtion_time",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String selectTdzyfSifaPaimaiByhouse_proclaimtion_time(String time_1,String time_2,String[] house_type_list){
        List<TdzyfSifaPaimai> tdzyfSifaPaimaiList=tdzyfSifaPaimaiService.selectTdzyfSifaPaimaiByhouse_proclaimtion_time(time_1,time_2,house_type_list);
        ModelAndView mav=new ModelAndView();
        mav.addObject("tdzyfSifaPaimaiList",tdzyfSifaPaimaiList);
        mav.addObject("list_size",tdzyfSifaPaimaiList.size());
        String res="";
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }
}
