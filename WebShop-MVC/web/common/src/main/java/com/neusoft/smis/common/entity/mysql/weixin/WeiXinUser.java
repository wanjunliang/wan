package com.neusoft.smis.common.entity.mysql.weixin;

import com.neusoft.smis.common.entity.mysql.Role;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2018/7/18.
 * weixin_user表
 */
@Data
public class WeiXinUser implements Comparable<WeiXinUser>{
    private int weixin_user_id;
    private String openid;//微信开放的ID
    private String nickName;//微信昵称
    private String avatarUrl;//微信头像网址
    private String gender;//微信性别,0:未知,1:男,2:女
    private String city;//城市
    private String province;//省
    private String country;//国家
    private String name;//用户真实名字
    private String phone;//手机号
    private String create_time;//创建用户的时间
    private List<Role> roleList;          //对应的角色列表

    @Override
    public int compareTo(WeiXinUser o) {
        if(create_time.compareTo(o.getCreate_time())<0){
            return 1;
        }
        if(create_time.compareTo(o.getCreate_time())>0){
            return -1;
        }
        return 0;
    }
}
