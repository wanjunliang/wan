package com.neusoft.smis.dao.mysql.weixin;

import com.neusoft.smis.common.entity.mysql.Privs;
import com.neusoft.smis.common.entity.mysql.weixin.WeiXinUser;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/18.
 * 微信用户表的mapper
 */
public interface WeiXinUserMapper {
//    1.查询是否weixin_user表中是否有该用户,通过openid来查询
    WeiXinUser selectIfHaveWeiXinUserByOpenId(WeiXinUser weiXinUser);
//    2.查询该用户是否设置角色会员
    WeiXinUser selectRolesPrivsWeiXinUserByOpenId(WeiXinUser weiXinUser);
    //动态新增微信用户
    int insertWeixinUser(WeiXinUser weiXinUser);
    //动态更新微信用户
    int updateWeiXinUserByOpenid(WeiXinUser weiXinUser);
    //查询所有微信用户,按照时间逆序排序
    List<WeiXinUser> selectWeixinUserList(Map map);
    //查询微信用户是否已经是VIP了,查询relation_weixin_user_role表通过角色是role_name是会员的role_id与weixin_user_id是否已经有了
    int selectWeixinUserIfVIP(Map map);
    //新增relation_weixin_user_role通过weixin_user_id和role_id
    int insert_relation_weixin_user_role(Map map);
    //查询会员的role_id通过会员名字
    int selectRoleId(Map map);
    //删除微信用户的角色会员
    int deleteWeiXinUserRoleVIP(Map map);
    //查询角色的权限列表
    List<Privs> selectPrivsByRoleId(Map map);
}
