package com.neusoft.smis.webapp.user_manage_controller;

import com.alibaba.fastjson.JSON;
import com.neusoft.smis.common.entity.mysql.Role;
import com.neusoft.smis.common.entity.mysql.User;
import com.neusoft.smis.webapp.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/7.
 * 用户的管理的控制层
 */
@Controller
@RequestMapping("/androidUser")
public class AndroidUserManageController extends BaseController {
    /***
     * 更新用户的信息,通过用户的user_id
     * @param user
     * @return
     * "info":1."更新成功";2."更新失败".3,"user_id是空的";
     */
    @RequestMapping(value = "/updateUserByUserId",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateUserByUserId(User user){
        String re="";
        if("".equals(user.getUser_id())==false&&user.getUser_id()!=0){
            re=userService.updateUserInfoByUserId(user);
        }
        else{
            re="user_id是空的";
        }
        String info="";
        if("SUCC".equals(re)){
            info="更新成功";
        }
        else if("user_id是空的".equals(re)){
            info="user_id是空的";
        }
        else{
            info="更新失败";
        }
        Map map=new HashMap();
        map.put("info",info);
        String res=null;
        try {
            res = JSON.toJSONString(map);
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }

    /**
     * 修改密码通过user_id,要加密
     * @param user   user_id,password
     * @return json数据
     * "info":1."更新成功";2."更新失败";3."user_id是空的";4."密码是空的";5."更新失败,数据库异常";
     *         6."原密码错误".
     *         7 ."查找到多个用户,通过user_id值"
     *         8."查找不到用户"
     */
    @RequestMapping(value = "/updatePasswordByUserId",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updatePasswordByUserId(User user,String yuan_password){
        String info=userService.updatePasswordByUserId(user,yuan_password);
        Map map=new HashMap();
        map.put("info",info);
        String res=null;
        try {
            res = JSON.toJSONString(map);
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }

    /***
     * 查询角色们通过用户的id,返回角色json格式数据
     * @return
     */
    @RequestMapping(value = "/selectRoleByUserId",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String selectRoleByUserId(User user){
        if(user.getUser_id()==0){   //如果没有传入user_id值
            Map map=new HashMap();
            map.put("info","fail");
            String res=null;
            try {
                res = JSON.toJSONString(map);
            }catch (Exception e){
                System.out.println("封装json失败!");
            }
            return res;
        }
        List<Role> roleList=userService.selectUserRolesByUser_id(user);
        Map map=new HashMap();
        map.put("roleList",roleList);
        map.put("list_size",roleList.size());
        map.put("info","successful");
        String res=null;
        try {
            res = JSON.toJSONString(map);
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }
}
