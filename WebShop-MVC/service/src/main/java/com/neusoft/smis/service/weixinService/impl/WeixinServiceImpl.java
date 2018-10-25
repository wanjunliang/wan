package com.neusoft.smis.service.weixinService.impl;

import com.neusoft.smis.common.entity.mysql.Privs;
import com.neusoft.smis.common.entity.mysql.Role;
import com.neusoft.smis.common.entity.mysql.weixin.WeiXinUser;
import com.neusoft.smis.common.entity.mysql.weixin.WeiXinYuYue;
import com.neusoft.smis.dao.mysql.weixin.WeiXinUserMapper;
import com.neusoft.smis.dao.mysql.weixin.WeiXinYuYueMapper;
import com.neusoft.smis.service.weixinService.WeixinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2018/6/12.
 */
@Service("weixinService")
public class WeixinServiceImpl implements WeixinService{
    @Autowired
    WeiXinYuYueMapper weiXinYuYueMapper;
    @Autowired
    WeiXinUserMapper weiXinUserMapper;
    @Override
    public String insertYuYue(WeiXinYuYue weiXinYuYue) {
        //避免重复预约,看openid与tdzyf_sifa_paimai_id两个相同的是否已经存在了
        Map map =new HashMap();
        map.put("openid",weiXinYuYue.getOpenid());
        map.put("tdzyf_sifa_paimai_id",weiXinYuYue.getTdzyf_sifa_paimai_id());
        List<WeiXinYuYue> weiXinYuYues =weiXinYuYueMapper.selectWeiXinYuYueList(map);
        if(weiXinYuYues!=null&&weiXinYuYues.size()>=1){
            return "已经预约过了";
        }
        Date date=new Date();
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //设置新增数据的时间
        weiXinYuYue.setTime(f.format(date));
        int re=weiXinYuYueMapper.insertYuYue(weiXinYuYue);
        if(re==1){
            return "新增成功";
        }
        return "新增失败";
    }

    @Override
    public List<WeiXinYuYue> selectWeiXinYuYueList(Map map) {
        return weiXinYuYueMapper.selectWeiXinYuYueList(map);
    }

    @Override
    public String selectWeixinUserIfkeyishiyongweixin(WeiXinUser weiXinUser) {
//        查询微信用户是否可以使用微信小程序,只有角色是会员的,分配了权限是:允许会员使用微信小程序
        //1.查询是否weixin_user表中是否有该用户,通过openid来查询
        WeiXinUser weiXinUser1=weiXinUserMapper.selectIfHaveWeiXinUserByOpenId(weiXinUser);
        if(weiXinUser1==null){
            return "该微信用户没有提出申请,请先申请";
        }
        //2.查询该用户是否设置角色会员
        WeiXinUser weiXinUser2=weiXinUserMapper.selectRolesPrivsWeiXinUserByOpenId(weiXinUser);
        List<Role> roleList=weiXinUser2.getRoleList();
        if(roleList==null||roleList.size()==0){
            return "该微信用户不是会员";
        }
        //3.查询该用户的角色是否分配了权限:允许会员使用微信小程序
        for(Role r:roleList){
            Map roleMap=new HashMap();
            roleMap.put("role_id",r.getRole_id());
            //根据角色ID来查询权限list
            List<Privs> privsList=weiXinUserMapper.selectPrivsByRoleId(roleMap);
            if(privsList==null||privsList.size()==0){
                return "该用户的角色"+r.getRole_name()+"没有分配权限";
            }
            for(Privs p:privsList){
                if("允许使用微信小程序".equals(p.getPrivs_name())){
                    return "允许使用微信小程序";
                }
            }
        }
        return "不允许使用微信小程序";
    }

    @Transactional
    @Override
    public String insertWeixinUser(WeiXinUser weiXinUser) {
        //先查询通过openid来查询看是否已经新增了,如果已经数据库中有了,那么就修改信息就是了
        WeiXinUser weiXinUser1=weiXinUserMapper.selectIfHaveWeiXinUserByOpenId(weiXinUser);
        if(weiXinUser1!=null){//已经有该微信用户了就修改微信用户信息
            Date time = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            weiXinUser.setCreate_time(simpleDateFormat.format(time));
            int re_int=weiXinUserMapper.updateWeiXinUserByOpenid(weiXinUser);
            if(re_int==1){
                return "更新微信用户信息成功";
            }
            else{
                return "更新微信用户信息失败";
            }

        }
        else { //新增用户
            Date time = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            weiXinUser.setCreate_time(simpleDateFormat.format(time));
            int r = weiXinUserMapper.insertWeixinUser(weiXinUser);
            if (r ==1) {
                return "新增微信用户成功";
            } else {
                return "新增微信用户失败";
            }
        }

    }

    @Override
    public List<WeiXinUser> selectWeixinUserList(String keyword) {
        Map map=new HashMap();
        map.put("keyword",keyword);
        //查询所有的微信用户按照时间逆序排列
        List<WeiXinUser> weiXinUserList=weiXinUserMapper.selectWeixinUserList(map);
        //排序,按照时间逆序
        Collections.sort(weiXinUserList);

        return weiXinUserList;
    }

    @Transactional
    @Override
    public String insertRoleVIP(String weixin_user_id) {
        Map map=new HashMap();
        map.put("weixin_user_id",weixin_user_id);

        //先查看是否这个weixin_user_id已经是会员了
        int count=weiXinUserMapper.selectWeixinUserIfVIP(map);
        if(count>0){//已经添加的有会员的角色了
            return "该微信用户已经是会员了";
        }
        else{//添加成会员
            //查询会员的role_id
            Map roleMap=new HashMap();
            roleMap.put("role_name","会员");
            int roleId=weiXinUserMapper.selectRoleId(roleMap);
            if(roleId<=0){
                return "没有找到角色:会员";
            }
            map.put("role_id",roleId);
            int r=weiXinUserMapper.insert_relation_weixin_user_role(map);
            if(r==1){
                return "添加会员成功";
            }
            else{
                return "添加会员失败";
            }
        }
    }

    @Transactional
    @Override
    public String deleteWeiXinUserRoleVIP(String weixin_user_id) {
        //取消微信用户的角色会员在表relation_weixin_user_role中通过weixin_user_id和会员的id
        //查询会员的role_id
        Map roleMap=new HashMap();
        roleMap.put("role_name","会员");
        int roleId=weiXinUserMapper.selectRoleId(roleMap);
        if(roleId<=0){
            return "没有找到角色:会员";
        }
        Map map=new HashMap();
        map.put("weixin_user_id",weixin_user_id);
        map.put("role_id",roleId);
        int r=weiXinUserMapper.deleteWeiXinUserRoleVIP(map);
        if(r<=0){
            return "取消会员失败";
        }
        return "取消会员成功";
    }
}
