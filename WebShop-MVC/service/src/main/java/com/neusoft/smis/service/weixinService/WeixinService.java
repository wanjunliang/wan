package com.neusoft.smis.service.weixinService;

import com.neusoft.smis.common.entity.mysql.weixin.WeiXinUser;
import com.neusoft.smis.common.entity.mysql.weixin.WeiXinYuYue;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/12.
 */
public interface WeixinService {
    //新增一条来自微信端的预约数据
    String insertYuYue(WeiXinYuYue weiXinYuYue);
    //查询微信预约表的的数据通过各种组合条件
    List<WeiXinYuYue> selectWeiXinYuYueList(Map map);
//    查询微信用户是否可以使用微信小程序,只有角色是会员的,分配了权限是:允许会员使用微信小程序
    String selectWeixinUserIfkeyishiyongweixin(WeiXinUser weiXinUser);
    //动态新增微信用户
    String insertWeixinUser(WeiXinUser weiXinUser);
    //查询所有的微信用户按照时间逆序排列
    List<WeiXinUser> selectWeixinUserList(String keyword);
    //把微信用户添加成会员通过weixin_user_id
    String insertRoleVIP(String weixin_user_id);
    //取消微信用户的会员
    String deleteWeiXinUserRoleVIP(String weixin_user_id);
}
