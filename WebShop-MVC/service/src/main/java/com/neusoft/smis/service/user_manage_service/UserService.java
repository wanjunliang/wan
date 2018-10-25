package com.neusoft.smis.service.user_manage_service;

import com.neusoft.smis.common.entity.mysql.Privs;
import com.neusoft.smis.common.entity.mysql.Role;
import com.neusoft.smis.common.entity.mysql.User;

import java.util.List;

/**
 * Created by WanJunliang on 2017/5/19.
 */
public interface UserService {
    //查询用户和角色
    List<User>  selectUsers();
    //登录
    User doUserLogin(User userLogin);
    //通过用户ID查询用户的角色
    List<Role> selectUserRolesByUser_id(User user);
    //查询权限通过角色id
    List<Privs> selectPrivsByRoleId(Role role);
    //    更新用户通过用户的ID
    String updateUserInfoByUserId(User user);
    //上传头像图片设置用户的头像通过用户的ID
    String updateUserImgHeadByUserId(String imgName);
    //修改密码,要先把密码加密
    String updatePasswordByUserId(User user,String yuan_password);
}
