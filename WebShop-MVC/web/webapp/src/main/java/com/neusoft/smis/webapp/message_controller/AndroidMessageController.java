package com.neusoft.smis.webapp.message_controller;

import com.alibaba.fastjson.JSON;
import com.neusoft.smis.common.entity.mysql.message.Message;
import com.neusoft.smis.webapp.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.portlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2018/3/6.
 * android端发送消息,,然后再服务器端接受消息
 * 支持文本,图片,视频,语音
 */
@Controller
@RequestMapping("/androidMessageController")
public class AndroidMessageController extends BaseController{

    /***
     * 接收传来的信息,动态新增到数据库中
     * 参数
     * 必须有发送人的ID:int sender_id
     * 必须有目标标的物的ID:int tdzyf_sifa_paimai_data_id
     * @return
     * 标志发送是否成功:string flag  success,fail
     */
    @RequestMapping(value = "/insertMessage",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String insertMessage(Message message){
        Map map=new HashMap();
        String re=messageService.insertMessage(message);
        String res=null;
        map.put("flag",re);
        map.put("message",message);
        try {
            res = JSON.toJSONString(map);
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }

    /***
     * 上传语音,图片,视频,保存到E:/fileUpload/project
     * @param files
     * @param request
     * @param model
     * @return
     * flag:标记是否成功
     * list:重命名后的文件名字列表
     * size:数量
     */
    @RequestMapping(value = "/upload_file",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String upload_file(
            @RequestParam("file") CommonsMultipartFile files[],
            HttpServletRequest request, ModelMap model) {
        System.out.println("准备上传");
        List<String> list = new ArrayList<String>();
        String path="E:/fileUpload/project";//设置文件保存目录
        File f = new File(path);
        if (!f.exists()){
            f.mkdirs();
        }
        String flag="success";
        for (int i = 0; i < files.length; i++) {
            // 获得原始文件名
            String fileName = "";
            try {
                fileName = new String(files[i].getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            ;
            //获取后缀
            fileName=fileName.substring(fileName.lastIndexOf("."));
            System.out.println("原文件名:" + fileName);
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
            System.out.println("文件保存路径:" + path + newFileName);
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
     * 查询信息通过标的物数据ID和最后一条数据的时间还有消息的发送时间
     * 从数据库中查找大于这个发送时间的数据返回
     * @param message
     * @return
     * 返回大于传入参数发送时间的数据
     */
    @RequestMapping(value = "/selectMessageListBytdzyf_sifa_paimai_data_idAndsend_time",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String selectMessageListBytdzyf_sifa_paimai_data_idAndsend_time(Message message){
        Map map=new HashMap();
        List<Message> messageList=messageService.selectMessageListBytdzyf_sifa_paimai_data_idAndsend_time(message);
        String res=null;
        map.put("messageList",messageList);
        try {
            res = JSON.toJSONString(map);
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }
}
