package com.neusoft.smis.webapp.UploadController;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.portlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.*;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * Created by Administrator on 2017/11/28.
 * 文件上传控制层
 */
@Controller
@RequestMapping("/file")
public class UploadController {
    @RequestMapping(value = "/upload",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String threeFileUpload(
            @RequestParam("file") CommonsMultipartFile files[],
            HttpServletRequest request, ModelMap model) {
        System.out.println("准备上传");
        List<String> list = new ArrayList<String>();
        // 获得项目的路径
//        ServletContext sc = request.getSession().getServletContext();
        // 上传位置
//        String path = sc.getRealPath("\\img") + "\\"; // 设定文件保存的目录
        String path="E:/fileUpload/project/";//设置文件保存目录
        File f = new File(path);
        if (!f.exists()){
            f.mkdirs();
        }
        String flag="success";
        for (int i = 0; i < files.length; i++) {
            // 获得原始文件名
            String fileName = files[i].getOriginalFilename();
            //获取后缀
            fileName=fileName.substring(fileName.lastIndexOf("."));
            System.out.println("原始文件的后缀名:" + fileName);
            // 新文件名
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//设置日期格式
            String str_date=df.format(new Date());
            String newFileName =str_date+"-"+UUID.randomUUID() +"-"+ fileName;
            if (!files[i].isEmpty()) {
                try {
                    FileOutputStream fos = new FileOutputStream(path
                            + newFileName);
                    InputStream in = files[i].getInputStream();
                    int b = 0;
                    while ((b = in.read()) != -1) {
                        fos.write(b);
                    }
                    fos.close();
                    in.close();
                } catch (Exception e) {
                    flag="fail";
                    e.printStackTrace();
                }
            }
            System.out.println("上传图片到:" + path + newFileName);
            list.add(newFileName);
        }
        ModelAndView mav=new ModelAndView();
        mav.addObject("flag",flag);
        mav.addObject("list",list);
        mav.addObject("size",list.size());
        String res="";
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }
    //上传头像
    @RequestMapping(value = "/upload_head_portrait",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String upload_head_portrait(
            @RequestParam("file") CommonsMultipartFile files[],
            HttpServletRequest request, ModelMap model) {
        System.out.println("准备上传");
        List<String> list = new ArrayList<String>();
        // 获得项目的路径
//        ServletContext sc = request.getSession().getServletContext();
        // 上传位置
//        String path = sc.getRealPath("\\img") + "\\"; // 设定文件保存的目录
        String path="E:/fileUpload/user/";//设置文件保存目录
        File f = new File(path);
        if (!f.exists()){
            f.mkdirs();
        }
        String flag="success";
        for (int i = 0; i < files.length; i++) {
            // 获得原始文件名
            String fileName = files[i].getOriginalFilename();
            //获取后缀
            fileName=fileName.substring(fileName.lastIndexOf("."));
            System.out.println("原始文件的后缀名:" + fileName);
            // 新文件名
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//设置日期格式
            String str_date=df.format(new Date());
            String newFileName =str_date+"-"+UUID.randomUUID() +"-"+ fileName;
            if (!files[i].isEmpty()) {
                try {
                    FileOutputStream fos = new FileOutputStream(path
                            + newFileName);
                    InputStream in = files[i].getInputStream();
                    int b = 0;
                    while ((b = in.read()) != -1) {
                        fos.write(b);
                    }
                    fos.close();
                    in.close();
                } catch (Exception e) {
                    flag="fail";
                    e.printStackTrace();
                }
            }
            System.out.println("上传图片到:" + path + newFileName);
            list.add(newFileName);
        }
        ModelAndView mav=new ModelAndView();
        mav.addObject("flag",flag);
        mav.addObject("list",list);
        mav.addObject("size",list.size());
        String res="";
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }

    /***
     * 加载图片
     * 加载文件都可以,不仅仅是图片
     * @return
     */
    @RequestMapping(value = "/loadPicture",produces = "text/html;charset=UTF-8")
    public void loadPicture(String img_path,HttpServletRequest req, HttpServletResponse resp){
        try {
            FileInputStream in = new FileInputStream("E://fileUpload/" + img_path);
            OutputStream out = resp.getOutputStream();
            int a;
            while ((a = in.read()) != -1) {
                out.write(a);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /***
     * python爬虫上传图片
     * @param files
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/pythonImgUpload",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String pythonImgUpload(
            @RequestParam("file") CommonsMultipartFile files[],
            HttpServletRequest request, ModelMap model) {
        System.out.println("准备上传");
        List<String> list = new ArrayList<String>();
        // 获得项目的路径
//        ServletContext sc = request.getSession().getServletContext();
//        // 上传位置
//        String path = sc.getRealPath("\\img") + "\\"; // 设定文件保存的目录
        String path="E:/fileUpload/python/";//设置文件保存目录
        File f = new File(path);
        if (!f.exists()){
            f.mkdirs();
        }
        String flag="success";
        for (int i = 0; i < files.length; i++) {
            // 获得原始文件名
            String fileName = files[i].getOriginalFilename();
            if (!files[i].isEmpty()) {
                try {
                    FileOutputStream fos = new FileOutputStream(path
                            + fileName);
                    InputStream in = files[i].getInputStream();
                    int b = 0;
                    while ((b = in.read()) != -1) {
                        fos.write(b);
                    }
                    fos.close();
                    in.close();
                } catch (Exception e) {
                    flag="fail";
                    e.printStackTrace();
                }
            }
            System.out.println("上传图片到:" + path + fileName);
            list.add(fileName);
        }
        ModelAndView mav=new ModelAndView();
        mav.addObject("flag",flag);
        mav.addObject("list",list);
        mav.addObject("size",list.size());
        String res="";
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }
}
