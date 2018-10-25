package com.neusoft.smis.webapp.login_register_controller;

import com.alibaba.fastjson.JSON;
import com.neusoft.smis.common.entity.mysql.User;
import com.neusoft.smis.service.shiro.ShiroDbRealm;
import com.neusoft.smis.webapp.BaseController;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
@Controller
@RequestMapping("/login")
public class LoginRegisterController extends BaseController {
    /**
     *账号登录
     * @param user   已经知道账号和密码查询是否正确
     * @return 1:登录成功
     *          2:账号或者密码错误
     *          3:检测到有多个该账号
     *          4:账号或者密码为空
     */
//    @RequestMapping(value = "/login",produces = "text/html;charset=UTF-8")
//    @ResponseBody
//    public String login(User user,HttpServletRequest request, HttpServletResponse response){
//        ModelAndView mav=new ModelAndView();
//        String res_str=loginRegisterService.login(user,request,response);
//        mav.addObject("tip",res_str);
//        String res=null;
//        try {
//            res = JSON.toJSONString(mav.getModel());
//        }catch (Exception e){
//            System.out.println("封装json失败!");
//        }
//        return res;
//    }

    /**
     * 注册账号
     * @param user
     * @return
     */
    @RequestMapping(value = "/register",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String register(User user){
        ModelAndView mav=new ModelAndView();
        String res_str=loginRegisterService.register(user);
        mav.addObject("tip",res_str);
        String res=null;
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }
    @RequestMapping(value = "/login",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String login(User user){
        String info = loginUser(user);
        ModelAndView mav=new ModelAndView();
        String res_str=null;
        if (!"SUCC".equals(info)) {
            res_str= info;
        }else{
            res_str="登录成功!";//返回到页面说夹带的参数
        }
        mav.addObject("tip",res_str);
        String res=null;
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }
    @RequestMapping(value="/logout",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String logout(HttpServletRequest request,HttpServletResponse response){
        System.out.println("注销");
        String res_tip="注销失败";
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            try{
                subject.logout();
                res_tip="注销成功";
            }catch(Exception ex){
            }
        }
        ModelAndView mav=new ModelAndView();
        mav.addObject("tip",res_tip);
        String res=null;
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }
    private String loginUser(User user) {
        if (isRelogin(user)) return "SUCC"; // 如果已经登陆，无需重新登录

        return shiroLogin(user); // 调用shiro的登陆验证
    }
    private String shiroLogin(User user) {
        // 组装token，包括账号和密码
        UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPassword().toCharArray(), null);
        token.setRememberMe(true);
        // shiro登陆验证
        try {
            SecurityUtils.getSubject().login(token);
        } catch (UnknownAccountException ex) {
            return "用户不存在或者密码错误！";
        } catch (IncorrectCredentialsException ex) {
            return "用户不存在或者密码错误！";
        } catch (AuthenticationException ex) {
            return ex.getMessage(); // 自定义报错信息
        } catch (Exception ex) {
            ex.printStackTrace();
            return "内部错误，请重试！";
        }
        return "SUCC";
    }

    private boolean isRelogin(User user) {
        Subject us = SecurityUtils.getSubject();
        if (us.isAuthenticated()) {
            return true; // 参数未改变，无需重新登录，默认为已经登录成功
        }
        return false; // 需要重新登陆
    }
    //判断是否有用户登录如果有,得到当前登录用户的信息
    @RequestMapping(value = "/islogined",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String islogined(){
        Boolean islogined=isRelogin(null);
        ModelAndView mav=new ModelAndView();
        mav.addObject("islogined",islogined);
        if(islogined==true){
            User user = (User) SecurityUtils.getSubject().getSession().getAttribute(ShiroDbRealm.SESSION_USER_KEY);
            mav.addObject("user",user);
        }
        String res=null;
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }

}
