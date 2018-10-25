package com.neusoft.smis.common.filter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neusoft.smis.common.unit.CookieUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * Created by Administrator on 2017/7/10.
 */
public class LoginHandlerIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入登录拦截器中preHandle()");
        CookieUtils cookieUtils=new CookieUtils();
        Cookie cookie=cookieUtils.getCookieByName(request,"user_id");
        String user_id=cookie.getValue();

        HttpSession session = request.getSession();
        String user = (String) session.getAttribute(user_id);
        if(user!=null){
            System.out.println("已经登录过了");
            //登陆成功的用户
            System.out.println("登录人名字"+user);
            return true;
        }else{
            System.out.println("没有登录,跳转到登录页面");
            //没有登陆，转向登陆界面
            response.sendRedirect(request.getContextPath()+"/html/oa_new/login.html");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
