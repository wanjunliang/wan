package com.neusoft.smis.service.login_register_service;

import com.neusoft.smis.common.entity.mysql.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2017/7/10.
 */
public interface LoginRegisterService {
    /**
     *
     * @param user 已经知道账号和密码
     * @return 返回提示字符串
     */
    String login(User user, HttpServletRequest request, HttpServletResponse response);
    //注册
    String register(User user);

    //android端登录的service
    Map<String,Objects> androidLogin(User user);
    //android端注册账号
    Map<String,Objects> androidRegister(User user);
    //android端查询用户通过用户的id
    Map androidSelectUserByUserId(User user);
}
