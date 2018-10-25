package com.neusoft.smis.dao.mysql.user;

import com.neusoft.smis.common.entity.mysql.Privs;
import com.neusoft.smis.common.entity.mysql.Role;
import com.neusoft.smis.common.entity.mysql.User;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * Created by WanJunliang on 2017/5/23.
 */
public interface UserMapper {
    //查询用户和角色
    List<User>  selectUsers();
    //查询用户信息通过账号密码
    List<User> selectUserBuAccountPassword(User user);
    //通过用户ID查询用户的角色
    List<Role> selectUserRolesByUser_id(User user);
    //查询权限通过角色id
    List<Privs> selectPrivsByRoleId(Role role);
    //查询用户通过account
    List<User> selectUserByAccount(User user);
    //新增用户
    int insertUser(User user);
    //查询角色通过角色名称
    List<Role> selectRoleByRoleName(String role_name);
    //新增用户和角色之间的关系
    int insertRelationUserRoleByUserIdRoleId(String user_id,String role_id);
    //查询用户通过用户的各种条件进行查询
    List<User> selectUser(User user);
//    更新用户通过用户的ID
    int updateUserInfoByUserId(User user);
}
