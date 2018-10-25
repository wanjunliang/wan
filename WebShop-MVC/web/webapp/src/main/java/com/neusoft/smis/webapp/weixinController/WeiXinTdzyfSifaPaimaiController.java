package com.neusoft.smis.webapp.weixinController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.neusoft.smis.common.entity.mysql.TdzyfSifaPaimai;
import com.neusoft.smis.common.entity.mysql.weixin.WeiXinYuYue;
import com.neusoft.smis.common.paging.Page;
import com.neusoft.smis.common.unit.WXAppletUserInfo;
import com.neusoft.smis.service.weixinService.WeixinService;
import com.neusoft.smis.webapp.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/11.
 * 提供给微信端的控制层接口
 */
@Controller
@RequestMapping("/WeiXinTdzyfSifaPaimaiController")
public class WeiXinTdzyfSifaPaimaiController extends BaseController {
    /***
     * 查询司法拍卖的数据
     * 这里需要分页设置一哈
     * @param time_1   开拍的起始时间
     * @param time_2   开拍的终止时间
     * @param house_type_list  房产的类型列表
     * @param search_input   搜索关键字
     * @param auction_end_time_1   竞拍结束时间的起始时间
     * @param auction_end_time_2   竞拍结束时间的结束时间
     * @param limit_start   传入查询的起始位置
     * @param limit_size   查找的数据的数量大小
     * @param house_region_list   数组 查找的地区们
     * @param auction_price_0_1   数组 起拍价最低和最高两个价格
     * @return
     */
    @RequestMapping(value = "/selectTdzyfSifaPaimaiInWeiXin",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String selectTdzyfSifaPaimaiInWeiXin(String time_1,String time_2,String[] house_type_list,String search_input,String auction_end_time_1,String auction_end_time_2,String limit_start,String limit_size,String[] house_region_list,String[] auction_price_list,String[] area_list){
        if(limit_start==null||limit_start==""){
            limit_start="1";
        }
        if(limit_size==null||limit_size==""){
            limit_size="10";
        }
        //要处理一下数组house_region_list
        if(house_region_list!=null){
            for(int i=0;i<house_region_list.length;i++){
                house_region_list[i]=house_region_list[i].replaceAll("\\\"","").replaceAll("\\[","").replaceAll("]","");
                if("".equals(house_region_list[i])==true){
                    house_region_list=null;
                    break;
                }
            }
        }
        //处理数组auction_price_0_1
        if(auction_price_list!=null){
            for(int i=0;i<auction_price_list.length;i++){
                auction_price_list[i]=auction_price_list[i].replaceAll("\\\"","").replaceAll("\\[","").replaceAll("]","");
            }
        }
        //处理数组area_list
        if(area_list!=null){
            for(int i=0;i<area_list.length;i++){
                area_list[i]=area_list[i].replaceAll("\\\"","").replaceAll("\\[","").replaceAll("]","");
            }
        }
        List<TdzyfSifaPaimai> tdzyfSifaPaimaiList=tdzyfSifaPaimaiService.selectTdzyfSifaPaimaiInWeiXin(time_1,time_2,house_type_list,search_input,auction_end_time_1,auction_end_time_2,limit_start,limit_size,house_region_list,auction_price_list,area_list);
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
     * 新增一条预约记录
     * @param weiXinYuYue
     * @return
     */
    @RequestMapping(value = "/insertYuYue",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String insertYuYue(WeiXinYuYue weiXinYuYue){
        String flag=weixinService.insertYuYue(weiXinYuYue);
        ModelAndView mav=new ModelAndView();
        if("新增成功".equals(flag)){
            mav.addObject("flag","预约成功");
        }
        else if("新增失败".equals(flag)){
            mav.addObject("flag","预约失败");
        }
        else if("已经预约过了".equals(flag)){
            mav.addObject("flag","已经预约过了");
        }
        String res="";
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }

    /***
     * 判断是否已经预约过了,传入openid,tdzyf_sifa_paimai_id只需要这两个参数
     * @param weiXinYuYue
     * @return
     */
    @RequestMapping(value = "/selectCheckIfYuyue",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String selectCheckIfYuyue(WeiXinYuYue weiXinYuYue){
        Map map=new HashMap();
        map.put("openid",weiXinYuYue.getOpenid());
        map.put("tdzyf_sifa_paimai_id",weiXinYuYue.getTdzyf_sifa_paimai_id());
        List<WeiXinYuYue> weiXinYuYueList=weixinService.selectWeiXinYuYueList(map);
        ModelAndView mav=new ModelAndView();
        if(weiXinYuYueList!=null&&weiXinYuYueList.size()>=1){
            mav.addObject("flag","已经预约了");
        }
        else{
            mav.addObject("flag","还没有预约");
        }
        String res="";
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }

    /**
     * 通过ID查询司法拍卖表中的一条数据
     * @param tdzyfSifaPaimai
     * @return
     */
    @RequestMapping(value = "/selectTdzyfSifaPaimaiOneDataById",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String selectTdzyfSifaPaimaiOneDataById(TdzyfSifaPaimai tdzyfSifaPaimai){
        List<TdzyfSifaPaimai>  tdzyfSifaPaimaiList=tdzyfSifaPaimaiService.selectTdzyfSifaPaimaiByHouseName(tdzyfSifaPaimai);
        ModelAndView mav=new ModelAndView();
        if(tdzyfSifaPaimaiList!=null&&tdzyfSifaPaimaiList.size()==1){
            mav.addObject("tdzyfSifaPaimai",tdzyfSifaPaimaiList.get(0));
            mav.addObject("list_size",tdzyfSifaPaimaiList.size());
        }
        else{
            mav.addObject("list_size",0);
        }

        String res="";
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }

    /***
     * 查询我的预约列表
     * @param tdzyfSifaPaimai
     * @return
     */
    @RequestMapping(value = "/selectMyYuyueList",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String selectMyYuyueList(TdzyfSifaPaimai tdzyfSifaPaimai){
        List<TdzyfSifaPaimai>  tdzyfSifaPaimaiList=tdzyfSifaPaimaiService.selectTdzyfSifaPaimaiByHouseName(tdzyfSifaPaimai);
        ModelAndView mav=new ModelAndView();
        if(tdzyfSifaPaimaiList!=null&&tdzyfSifaPaimaiList.size()==1){
            mav.addObject("tdzyfSifaPaimai",tdzyfSifaPaimaiList.get(0));
            mav.addObject("list_size",tdzyfSifaPaimaiList.size());
        }
        else{
            mav.addObject("list_size",0);
        }

        String res="";
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }

    /***
     * 查询我预约的司法拍卖的数据列表,传入数据就一个openid,连表查询司法拍卖的数据们
     * @param weiXinYuYue
     * @return
     */
    @RequestMapping(value = "/selectMyYuyueTdzyfSifaPaimaiDataList",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String selectMyYuyueTdzyfSifaPaimaiDataList(WeiXinYuYue weiXinYuYue){
        List<TdzyfSifaPaimai> tdzyfSifaPaimaiList=tdzyfSifaPaimaiService.selectMyYuyueTdzyfSifaPaimaiDataList(weiXinYuYue);
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
     * 微信端登录得到openid
     * @param code
     * @return
     */
    @RequestMapping(value = "/loginWeixinGetOpenId",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String loginWeixinGetOpenId(String code){
        WXAppletUserInfo w=new WXAppletUserInfo();
        JSONObject json=w.getSessionKeyOropenid(code);
        return json.toString();
    }

    /***
     * 链接到外部的html
     * @param url
     * @return
     */
//    @RequestMapping(value = "/lianjieOutHtml",produces = "text/html;charset=UTF-8")
//    public ModelAndView lianjieOutHtml(String url){
//        System.out.println("url:"+url);
//        ModelAndView mav=new ModelAndView();
//        mav.addObject("url",url);
//        return mav;
//    }
}
