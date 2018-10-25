package com.neusoft.smis.webapp.tdzyfSifaPaimaiController;

import com.alibaba.fastjson.JSON;
import com.neusoft.smis.common.entity.mysql.TdzyfSifaPaimai;
import com.neusoft.smis.webapp.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/21.
 * 查询tdzyf_sifa_paimai 表的控制层
 */
@Controller
@RequestMapping("/tdzyfSifaPaimaiController")
public class TdzyfSifaPaimaiController extends BaseController {
    @RequestMapping(value = "/selectTdzyfSifaPaimaiByHouseName",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String selectTdzyfSifaPaimaiByHouseName(TdzyfSifaPaimai tdzyfSifaPaimai){
        List<TdzyfSifaPaimai>  tdzyfSifaPaimaiList=tdzyfSifaPaimaiService.selectTdzyfSifaPaimaiByHouseName(tdzyfSifaPaimai);

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
    //地图的查询数据接口
    @RequestMapping(value = "/selectTdzyfSifaPaimaiByLngLat",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String selectTdzyfSifaPaimaiByLngLat(String lng_bianjie_you,String lng_bianjie_zuo,String lat_bianjie_shang,String lat_bianjie_xia){
        Map map=new HashMap();
        map.put("lng_bianjie_you",lng_bianjie_you);
        map.put("lng_bianjie_zuo",lng_bianjie_zuo);
        map.put("lat_bianjie_shang",lat_bianjie_shang);
        map.put("lat_bianjie_xia",lat_bianjie_xia);

        List<TdzyfSifaPaimai>  tdzyfSifaPaimaiList=tdzyfSifaPaimaiService.selectTdzyfSifaPaimaiByLngLat(map);

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

    /***
     * 商业地图的查询接口
     * @return
     */
    @RequestMapping(value = "/selectTdzyfSifaPaimaiBusiness",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String selectTdzyfSifaPaimaiBy(String house_type){
        Map map=new HashMap();
        map.put("house_type",house_type);
        List<TdzyfSifaPaimai>  tdzyfSifaPaimaiList=tdzyfSifaPaimaiService.selectTdzyfSifaPaimaiBusiness(map);

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
