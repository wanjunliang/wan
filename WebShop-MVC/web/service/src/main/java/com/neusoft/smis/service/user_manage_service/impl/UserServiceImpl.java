package com.neusoft.smis.service.user_manage_service.impl;

import com.neusoft.smis.common.entity.mysql.Privs;
import com.neusoft.smis.common.entity.mysql.Role;
import com.neusoft.smis.common.entity.mysql.User;
import com.neusoft.smis.common.unit.MD5Encoder;
import com.neusoft.smis.dao.mysql.user.UserMapper;
import com.neusoft.smis.service.shiro.ShiroDbRealm;
import com.neusoft.smis.service.user_manage_service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by WanJunliang on 2017/5/19.
 */
@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> selectUsers() {
        return userMapper.selectUsers();
    }

    @Override
    public User doUserLogin(User user) {
        if (user != null && user.getAccount() != null && "".equals(user.getAccount()) != true && user.getPassword() != null && "".equals(user.getPassword()) != true) {
            List<User> userList = userMapper.selectUserBuAccountPassword(user);
            if (userList.size() == 1) {
                return userList.get(0);
            }
        }
        else{
            return null;
        }
        return null;
    }

    @Override
    public List<Role> selectUserRolesByUser_id(User user) {
        return userMapper.selectUserRolesByUser_id(user);
    }

    @Override
    public List<Privs> selectPrivsByRoleId(Role role) {
        return userMapper.selectPrivsByRoleId(role);
    }

    @Override
    @Transactional    //事物回滚
    public String updateUserInfoByUserId(User user) {
        //设置修改时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setUpdate_date(df.format(new Date()));
        int inf=userMapper.updateUserInfoByUserId(user);
        if(inf==1){
            return "SUCC";
        }
        return "FAIL";
    }

    @Override
    @Transactional    //事物回滚
    public String updateUserImgHeadByUserId(String imgName) {
        String res="FAIL";
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute(ShiroDbRealm.SESSION_USER_KEY);
        if(user!=null){
            User newUser=new User();
            newUser.setUser_id(user.getUser_id());
            newUser.setImg_src_head(imgName);
            //设置修改时间
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            newUser.setUpdate_date(df.format(new Date()));
            int inf=userMapper.updateUserInfoByUserId(newUser);
            if(inf==1){
                res="SUCC";
            }
        }
        return res;
    }

    @Override
    public String updatePasswordByUserId(User user,String yuan_password) {
        if(user.getUser_id()==0) {
            return "user_id是空的";
        }
        if("".equals(user.getPassword())){
            return "密码是空的";
        }
        //先查找出用户然后对比密码原来的密码
        User user2=new User();
        user2.setUser_id(user.getUser_id());
        List<User> userList=userMapper.selectUser(user2);
        if(userList.size()==1){
            User user3=userList.get(0);
            //对比密码
            try {
                if(MD5Encoder.validPassword(yuan_password,user3.getPassword())){
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
                    //设置修改时间
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    user.setUpdate_date(df.format(new Date()));
                    int inf=userMapper.updateUserInfoByUserId(user);
                    if(inf==1){
                        return "更新成功";
                    }
                    else{
                        return "更新失败,数据库异常";
                    }
                }
                else{
                    return "原密码错误";
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
        else if(userList.size()>1){
            return "查找到多个用户,通过user_id值";
        }
        else{
            return "查找不到用户";
        }
        return "更新失败";
    }


}
