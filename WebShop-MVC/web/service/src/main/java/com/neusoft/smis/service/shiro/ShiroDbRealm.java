package com.neusoft.smis.service.shiro;

import com.neusoft.smis.common.entity.mysql.Privs;
import com.neusoft.smis.common.entity.mysql.Role;
import com.neusoft.smis.common.entity.mysql.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.neusoft.smis.service.user_manage_service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/11.
 */
public class ShiroDbRealm extends AuthorizingRealm {
    @Autowired
    private UserServiceImpl userService;
    public static final String SESSION_USER_KEY = "gray";

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用,负责在应用程序中决定用户的访问控制的方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        System.out.println("授权");
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute(ShiroDbRealm.SESSION_USER_KEY);
        System.out.println("授权:user_id:"+user.getUser_id());
        List<String> roles = new ArrayList<String>();
        List<String> permissions = new ArrayList<String>();
        //从数据库中获取当前用户的角色和权限
        List<Role> roleList=userService.selectUserRolesByUser_id(user);
        for(Role r:roleList){
            roles.add(r.getRole_name());
            List<Privs> privsList=userService.selectPrivsByRoleId(r);
            for(Privs p:privsList){
                Boolean flag_chongfu=false;
                for(String s:permissions){
                    if(s.equals(p.getPrivs_name())){
                        flag_chongfu=true;
                        break;
                    }
                }
                if(flag_chongfu==false) {
                    permissions.add(p.getPrivs_name());
                }
            }
        }
        for(String s:roles){
            System.out.println("角色:"+s);
        }
        for(String s:permissions){
            System.out.println("权限:"+s);
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
    }

    /**
     * 认证回调函数，登录信息和用户验证信息验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        // 把token转换成User对象
        User userLogin = tokenToUser((UsernamePasswordToken) authcToken);
        // 验证用户是否可以登录
        User ui = userService.doUserLogin(userLogin);
        if(ui == null)
            return null; // 异常处理，找不到数据
        // 设置session
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute(ShiroDbRealm.SESSION_USER_KEY, ui);
        //当前 Realm 的 name
        String realmName = this.getName();
        //登陆的主要信息: 可以是一个实体类的对象, 但该实体类的对象一定是根据 token 的 username 查询得到的.
//      Object principal = ui.getUsername();
        Object principal = authcToken.getPrincipal();
        return new SimpleAuthenticationInfo(principal, userLogin.getPassword(), realmName);
    }

    private User tokenToUser(UsernamePasswordToken authcToken) {
        User user = new User();
        user.setAccount(authcToken.getUsername());
        user.setPassword(String.valueOf(authcToken.getPassword()));
        return user;
    }

    //一定要写getset方法
    public UserServiceImpl getUserService() {
        return userService;
    }

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
}
