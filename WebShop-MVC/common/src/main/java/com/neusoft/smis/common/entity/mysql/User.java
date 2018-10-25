package com.neusoft.smis.common.entity.mysql;

import com.neusoft.smis.common.entity.mysql.Role;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by WanJunliang on 2017/5/19.
 */
@Data
public class User {
    private int user_id;                   //id
    private String account;                //账号
    private String password;                //账号
    private String user_name;                //用户名字
    private List<Role> roleList;          //对应的角色列表
    private String salt;   //盐    盐值加密   还没有用,暂时留着

    private String img_src_head;//头像图片地址
    private String phone;//手机号码
    private String birthday;//生日
    private String mail;//邮箱
    private String sex;//性别
    private String create_date;//创建日期
    private String update_date;//修改日期

}
