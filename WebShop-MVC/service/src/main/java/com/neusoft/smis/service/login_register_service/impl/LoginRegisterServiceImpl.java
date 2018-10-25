package com.neusoft.smis.service.login_register_service.impl;

import com.neusoft.smis.common.entity.mysql.Role;
import com.neusoft.smis.common.entity.mysql.User;
import com.neusoft.smis.common.unit.MD5Encoder;
import com.neusoft.smis.dao.mysql.user.UserMapper;

import com.neusoft.smis.service.login_register_service.LoginRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/7/10.
 */
@Service("loginRegisterService")
public class LoginRegisterServiceImpl implements LoginRegisterService {
    @Autowired
    UserMapper userMapper;
    @Override
    public String login(User user,HttpServletRequest request,HttpServletResponse response) {
        String res_str="";
        if(user!=null&&user.getAccount()!=null&&"".equals(user.getAccount())!=true&&user.getPassword()!=null&&"".equals(user.getPassword())!=true){
            List<User> userList=userMapper.selectUserBuAccountPassword(user);
            if(userList.size()==1){
                res_str="登录成功";
                //存入cookie中
                Cookie cookie = new Cookie("user_id", userList.get(0).getUser_id()+"");
                cookie.setPath("/");
                response.addCookie(cookie);

                //存session中
                HttpSession session=request.getSession();
                session.setAttribute(userList.get(0).getUser_id()+"",userList.get(0));
            }
            else if(userList.size()==0){
                res_str="账号或者密码错误";
            }
            else{
                res_str="检测到有多个该账号";
            }
        }
        else{
            res_str="账号或者密码为空";
        }
        return res_str;
    }
    @Override
    public String register(User user) {
        //先查看用户账号是否有已经注册过了
        if(user.getAccount()==null||"".equals(user.getAccount())){
            return "您输入的账号是空的";
        }
        if(user.getPassword()==null||"".equals(user.getPassword())){
            return "您输入的密码是空的";
        }
        if(user.getUser_name()==null||"".equals(user.getUser_name())){
            return "您输入的用户名字是空的";
        }
        List<User> userList=userMapper.selectUserByAccount(user);
        if(userList.size()>0){
            return "您输入的账号已经注册过了,请重新输入";
        }
        //user表 新增用户
        userMapper.insertUser(user);
        //新增用户和角色的关系
        List<Role> roleList=userMapper.selectRoleByRoleName("普通员工");
        if(roleList.size()>0){
            Role role=roleList.get(0);
            //新增用户和角色的关系在中间表中
            userMapper.insertRelationUserRoleByUserIdRoleId(user.getUser_id()+"",role.getRole_id()+"");
        }
        return "注册成功";
    }

    @Override
    public Map<String,Objects> androidLogin(User user) {
        Map resMap=new HashMap();
        String info="";
        //先检查账号和密码不能为空
        if("".equals(user.getAccount())){
            info="登录的账号为空";
            resMap.put("info",info);
            return resMap;
        }
        else if("".equals(user.getPassword())){
            info="登录的密码为空";
            resMap.put("info",info);
            return resMap;
        }
        //去数据库查找,通过账号查找,然后再匹配密码
        List<User> userList=userMapper.selectUserByAccount(user);
        if(userList.size()>0){
            User user2=userList.get(0);
            try {
                if(MD5Encoder.validPassword(user.getPassword(), user2.getPassword())){
                    info="登录成功";
                    resMap.put("info",info);
                    resMap.put("user",user2);
                }
                else{
                    info="密码错误";
                    resMap.put("info",info);
                }
            } catch (NoSuchAlgorithmException e) {
                info="密码对比出现异常";
                resMap.put("info",info);
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                info="密码对比出现异常";
                resMap.put("info",info);
                e.printStackTrace();
            }
        }
        else{
            info="账号错误";
            resMap.put("info",info);
        }
        return resMap;
    }
    public boolean checkUser(User user){
        if("".equals(user.getAccount())){
            return false;
        }
        else if("".equals(user.getPassword())){
            return false;
        }
        else if("".equals(user.getUser_name())){
            return false;
        }
        return true;
    }
    @Override
    public Map<String,Objects> androidRegister(User user) {

        Map resMap=new HashMap();
        String info="";
        //先检查规不规范
        if(checkUser(user)==false){
            info="用户信息中存在不规范不符合要求的信息";
            resMap.put("info",info);
            return resMap;
        }
        //先查找数据库是否已经有该账号
        List<User> userList=userMapper.selectUserByAccount(user);
        if(userList.size()>0){
            info="该账号已经被人注册了";
            resMap.put("info",info);
            return resMap;
        }
        //密码加密
        String encryptedPwd = null;
        try {
            encryptedPwd = MD5Encoder.getEncryptedPwd(user.getPassword());
            user.setPassword(encryptedPwd);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //设置创建日期
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        user.setCreate_date(df.format(new Date()));// new Date()为获取当前系统时间
        //注册账号
        int id=userMapper.insertUser(user);
        if(id>0){
            info="注册成功";
            resMap.put("info",info);
            resMap.put("user",user);
        }
        else{
            info="数据库新增用户信息失败";
            resMap.put("info",info);
        }
        return resMap;
    }

    @Override
    public Map androidSelectUserByUserId(User user) {
        Map map=new HashMap();
        if(user!=null&&"".equals(user.getUser_id())==false&&user.getUser_id()!=0){
            List<User> userList=userMapper.selectUser(user);
            if(userList.size()==1){
                map.put("info","查找到用户信息");
                map.put("user",userList.get(0));
            }
            else if(userList.size()>1){
                map.put("info","查找到多个用户");
            }
            else{
                map.put("info","没有查找到用户");
            }
        }
        else{
            map.put("info","用户查询条件id值为空");
        }
        return map;
    }
}
