package com.neusoft.smis.webapp.login_register_controller;

import com.alibaba.fastjson.JSON;
import com.neusoft.smis.common.entity.mysql.User;
import com.neusoft.smis.webapp.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;

import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2017/12/5.
 */
@Controller
@RequestMapping("/androidLogin")
public class AndroidLoginRegisterController extends BaseController {
    /**
     *
     * @param user
     * @return json数据字符串
     * "info":1."登录的账号为空";2."登录的密码为空";3."登录成功";4.:"密码错误";5."密码对比出现异常";6."账号错误";
     * "user":
     */
    @RequestMapping(value = "/login",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String login(User user){

        Map<String,Objects> map=loginRegisterService.androidLogin(user);
        String res=null;
        try {
            res = JSON.toJSONString(map);
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }

    /***
     *
     * @param user
     * @return json数据字符串
     * "info":1."用户信息中存在不规范不符合要求的信息";2."该账号已经被人注册了";3."注册成功";4."数据库新增用户信息失败";
     * "user":
     */
    @RequestMapping(value = "/register",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String register(User user){
        Map<String,Objects> map=loginRegisterService.androidRegister(user);
        String res=null;
        try {
            res = JSON.toJSONString(map);
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }

    /***
     *
     * @param user
     * @return json数据
     * "info":1."查找到用户信息";2."查找到多个用户";3."没有查找到用户";4."用户查询条件id值为空"
     * "user"
     */
    @RequestMapping(value = "/selectUserInfoByUserId",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String selectUserInfoByUserId(User user){
        Map map=loginRegisterService.androidSelectUserByUserId(user);
        String res=null;
        try {
            res = JSON.toJSONString(map);
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }
}
