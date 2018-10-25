package com.neusoft.smis.webapp.weixinController;

import com.alibaba.fastjson.JSON;
import com.neusoft.smis.common.entity.mysql.weixin.WeiXinUser;
import com.neusoft.smis.webapp.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;

import java.util.List;

/**
 * Created by Administrator on 2018/7/18.
 * 微信用户controller
 */
@Controller
@RequestMapping("/WeiXinUserController")
public class WeiXinUserController extends BaseController{

    /***
     * 查询微信用户是否可以使用微信小程序,只有角色是会员的,分配了权限是:允许会员使用微信小程序
     *
     * @param weiXinUser
     * @return
     */
    @RequestMapping(value = "/selectWeixinUserIfkeyishiyongweixin",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String selectWeixinUserIfkeyishiyongweixin(WeiXinUser weiXinUser){
//        System.out.println("AAA:"+weiXinUser.getOpenid());
        String re=weixinService.selectWeixinUserIfkeyishiyongweixin(weiXinUser);
        ModelAndView mav=new ModelAndView();
        mav.addObject("flag",re);
        String res="";
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }

    /***
     * 新增微信用户
     * @param weiXinUser
     * @return
     */
    @RequestMapping(value = "/insertWeixinUser",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String insertWeixinUser(WeiXinUser weiXinUser){
        System.out.println("即将新增微信用户,openid:"+weiXinUser.getOpenid()+" 用户名字:"+weiXinUser.getName());
        String flag=weixinService.insertWeixinUser(weiXinUser);
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
     * 查询所有微信用户,按照时间逆序排序
     * @return
     */
    @RequestMapping(value = "/selectWeixinUserList",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String selectWeixinUserList(String keyword){
        List<WeiXinUser> weiXinUserList=weixinService.selectWeixinUserList(keyword);
        ModelAndView mav=new ModelAndView();
        mav.addObject("weiXinUserList",weiXinUserList);
        String res="";
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }

    /***
     * 把微信用户设置成会员角色
     * 先要看是否已经是会员,如果是就不管了,如果不是就新增成会员
     * @return
     */
    @RequestMapping(value = "/insertRoleVIP",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String insertRoleVIP(String weixin_user_id){
        String tip=weixinService.insertRoleVIP(weixin_user_id);
        ModelAndView mav=new ModelAndView();
        mav.addObject("tip",tip);
        String res="";
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }

    /***
     * 取消微信用户的会员
     * @param weixin_user_id
     * @return
     */
    @RequestMapping(value = "/deleteWeiXinUserRoleVIP",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deleteWeiXinUserRoleVIP(String weixin_user_id){
        String tip=weixinService.deleteWeiXinUserRoleVIP(weixin_user_id);
        ModelAndView mav=new ModelAndView();
        mav.addObject("tip",tip);
        String res="";
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }
}
