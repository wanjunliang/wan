package com.neusoft.smis.webapp.user_manage_controller;

import com.alibaba.fastjson.JSON;
import com.neusoft.smis.common.entity.mysql.Role;
import com.neusoft.smis.common.entity.mysql.User;
import com.neusoft.smis.common.unit.Base64Utils;
import com.neusoft.smis.service.shiro.ShiroDbRealm;
import com.neusoft.smis.webapp.BaseController;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.portlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.util.*;


/**
 * Created by WanJunliang on 2017/5/19.
 */
@Controller
@RequestMapping("/user")
public class UserManageController extends BaseController{
    @RequestMapping(value = "/selectUsers",produces = "text/html;charset=UTF-8")
    public String findDetailInfo(){
        List<User> userList=userService.selectUsers();
        for(User user:userList){
            System.out.println(user.getUser_id()+"  "+user.getUser_name()+"  "+user.getAccount()+"  "+user.getPassword());
        }
        return null;
    }
    @RequestMapping(value = "/updateUserInfo",produces = "text/html;charset=UTF-8")
    @ResponseBody
//    @RequiresPermissions("新闻:删除")
    public String updateUserInfo(User user){
        if(isRelogin()==false){
            ModelAndView mav=new ModelAndView();
            mav.addObject("tip","没有登录");
            String res="";
            try {
                res = JSON.toJSONString(mav.getModel());
            }catch (Exception e){
                System.out.println("封装json失败!");
            }
            return res;
        }
//        更新用户信息通过用户的ID
        String res=userService.updateUserInfoByUserId(user);
        if("SUCC".equals(res)==true){
            //注销重新登录
            Subject subject = SecurityUtils.getSubject();
            if (subject != null) {
                try{
                    subject.logout();
                }catch(Exception ex){
                }
            }
            shiroLogin(user);
        }
        ModelAndView mav=new ModelAndView();
        mav.addObject("tip",res);
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }
    private String shiroLogin(User user) {
        // 组装token，包括客户公司名称、简称、客户编号、用户名称；密码
        UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPassword().toCharArray(), null);
        token.setRememberMe(true);
        // shiro登陆验证
        try {
            SecurityUtils.getSubject().login(token);
        } catch (UnknownAccountException ex) {
            return "用户不存在或者密码错误！";
        } catch (IncorrectCredentialsException ex) {
            return "用户不存在或者密码错误！";
        } catch (AuthenticationException ex) {
            return ex.getMessage(); // 自定义报错信息
        } catch (Exception ex) {
            ex.printStackTrace();
            return "内部错误，请重试！";
        }
        return "SUCC";
    }
    //上传头像
    @RequestMapping(value="/uploadImgHead.do",method= RequestMethod.POST)
    @ResponseBody
    public String uploadImgHead(@RequestParam String base64Data, HttpServletRequest request, HttpServletResponse response){
        String tip="FAIL";
        if(isRelogin()==true){
            try{
                System.out.println("上传的数据:"+base64Data);
                String dataPrix = "";
                String data = "";

                System.out.println("对数据进行判断");
                if(base64Data == null || "".equals(base64Data)){
                    throw new Exception("上传失败，上传图片数据为空");
                }else{
                    String [] d = base64Data.split("base64,");
                    if(d != null && d.length == 2){
                        dataPrix = d[0];
                        data = d[1];
                    }else{
                        throw new Exception("上传失败，数据不合法");
                    }
                }

                System.out.println("对数据进行解析，获取文件名和流数据");
                String suffix = "";
                if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){//data:image/jpeg;base64,base64编码的jpeg图片数据
                    suffix = ".jpg";
                } else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){//data:image/x-icon;base64,base64编码的icon图片数据
                    suffix = ".ico";
                } else if("data:image/gif;".equalsIgnoreCase(dataPrix)){//data:image/gif;base64,base64编码的gif图片数据
                    suffix = ".gif";
                } else if("data:image/png;".equalsIgnoreCase(dataPrix)){//data:image/png;base64,base64编码的png图片数据
                    suffix = ".png";
                }else{
                    throw new Exception("上传图片格式不合法");
                }

                String tempFileName =getRandFileName() + suffix;
                System.out.println("生成文件名字:"+tempFileName);

                //因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
                byte[] bs = Base64Utils.decode(data);
                try{
                    //使用apache提供的工具类操作流
                    String basePath = request.getSession().getServletContext().getRealPath("/");
//                basePath=basePath.replaceAll("\\\\","/");//不用替换
                    System.out.println("项目的路径:"+basePath);
                    FileUtils.writeByteArrayToFile(new File(basePath+"img_head\\", tempFileName), bs);
                }catch(Exception ee){
                    throw new Exception("上传失败，写入文件失败，"+ee.getMessage());
                }
                System.out.println("上传成功");
                //存入到数据库中 通过当前登录用户的ID ，就存图片名字 tempFileName
                String flag_re=userService.updateUserImgHeadByUserId(tempFileName);
                if("SUCC".equals(flag_re)){
                    tip="SUCC";
                    //注销重新登录
                }
                else{
                    tip="mysqlFAIL";
                }
            }catch (Exception e) {
                System.out.println("上传失败");
            }
        }
        else{
            tip="NotLoggedIn";
        }
        ModelAndView mav=new ModelAndView();
        mav.addObject("tip",tip);
        String res="FAIL";
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }
    public String getRandFileName(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//设置日期格式
        String namedate=df.format(new Date());
        int name_int=(int)(1+Math.random()*(10000-1+1));
        String name_string=name_int+"";
        return namedate+"-"+name_string;
    }
    //判断用户登录没有  登录true  没有 false
    private boolean isRelogin() {
        Subject us = SecurityUtils.getSubject();
        if (us.isAuthenticated()) {
            return true; // 参数未改变，无需重新登录，默认为已经登录成功
        }
        return false; // 需要重新登陆
    }
}
