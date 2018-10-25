package com.neusoft.smis.webapp.company_web_controller;

import com.alibaba.fastjson.JSON;
import com.neusoft.smis.common.entity.mysql.news.NewsContent;
import com.neusoft.smis.common.entity.mysql.news.NewsTitle;
import com.neusoft.smis.webapp.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * Created by Administrator on 2017/7/27.
 */
@Controller
@RequestMapping("/news")
public class News_controller extends BaseController{
    @RequestMapping(value = "/insertNews",produces = "text/html;charset=UTF-8")
    @ResponseBody
    @RequiresPermissions("新闻:新增")
    public String insertNews(NewsTitle newsTitle, NewsContent newsContent,
                             @RequestParam(value = "flag_update", required = false)String flag_update,
                             @RequestParam(value = "title_id", required = false)String title_id,
                             @RequestParam(value = "content_id", required = false)String content_id
                             ){
        String re="FAIL";
        if("1".equals(flag_update)&&"".equals(title_id)==false&&"".equals(content_id)==false){//1表示是更新数据
            newsTitle.setNews_title_id(Integer.parseInt(title_id));
            newsTitle.setNews_content_id(Integer.parseInt(content_id));
            newsContent.setNews_content_id(Integer.parseInt(content_id));
            //通过新闻标题ID修改新闻标题,以及通过内容ID修改新闻内容
            re=newsService.updateNews(newsTitle,newsContent);
        }
        else {
            re = newsService.insertNews(newsTitle, newsContent);
        }
        ModelAndView mav=new ModelAndView();
        mav.addObject("tip",re);
        String res=null;
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }
    //查询新闻标题和内容通过新闻的标题的Id
    @RequestMapping(value = "/selectNewsTitleContentByNewsTitleId",produces = "text/html;charset=UTF-8")
    @ResponseBody
//    @RequiresPermissions("新闻:查询")
    public String selectNewsTitleContentByNewsTitleId(NewsTitle newsTitle){
        System.out.println("新闻查询,newsTitleId:"+newsTitle.getNews_title_id());
        NewsTitle reNewsTitle=newsService.selectNewsTitleContentByNewsTitleId(newsTitle);
        ModelAndView mav=new ModelAndView();
        mav.addObject("newsTitleContent",reNewsTitle);
        String res=null;
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }
    //查询新闻标题列表
    @RequestMapping(value = "/selectNewsTitleList",produces = "text/html;charset=UTF-8")
    @ResponseBody
//    @RequiresPermissions("新闻:查询")
    public String selectNewsTitleList(NewsTitle newsTitle){
        System.out.println("新闻查询,newsTitleId:"+newsTitle.getNews_title_id());
        List<NewsTitle> newsTitleList=newsService.selectNewsTitleList(newsTitle);
        ModelAndView mav=new ModelAndView();
        mav.addObject("newsTitleList",newsTitleList);
        String res=null;
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }
    //查询新闻标题列表   有数量限制
    @RequestMapping(value = "/selectNewsTitleListLimitNumber",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String selectNewsTitleListLimitNumber(String number){
        System.out.println("新闻查询,number:"+number);
        List<NewsTitle> newsTitleList=newsService.selectNewsTitleListLimitNumber(number);
        ModelAndView mav=new ModelAndView();
        mav.addObject("newsTitleList",newsTitleList);
        String res=null;
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }
    //删除新闻标题
    @RequestMapping(value = "/deleteNews",produces = "text/html;charset=UTF-8")
    @ResponseBody
    @RequiresPermissions("新闻:删除")
    public String deleteNews(NewsTitle newsTitle){
        String re=newsService.deleteNews(newsTitle);
        ModelAndView mav=new ModelAndView();
        mav.addObject("tip",re);
        String res=null;
        try {
            res = JSON.toJSONString(mav.getModel());
        }catch (Exception e){
            System.out.println("封装json失败!");
        }
        return res;
    }
}
