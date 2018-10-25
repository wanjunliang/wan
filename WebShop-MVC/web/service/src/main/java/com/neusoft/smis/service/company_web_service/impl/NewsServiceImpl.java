package com.neusoft.smis.service.company_web_service.impl;

import com.neusoft.smis.common.entity.mysql.User;
import com.neusoft.smis.common.entity.mysql.news.NewsContent;
import com.neusoft.smis.common.entity.mysql.news.NewsTitle;
import com.neusoft.smis.dao.mysql.news.NewsMapper;
import com.neusoft.smis.dao.mysql.user.UserMapper;
import com.neusoft.smis.service.company_web_service.NewsService;
import com.neusoft.smis.service.shiro.ShiroDbRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
@Service("newsService")
public class NewsServiceImpl implements NewsService{
    @Autowired
    NewsMapper newsMapper;
    @Autowired
    UserMapper userMapper;
    @Transactional    //事物回滚
    @Override
    public String insertNews(NewsTitle newsTitle, NewsContent newsContent) {
        //先新增内容,得到新增内容的ID
        int flag1=newsMapper.insertNewsContent(newsContent);
        //在新增标题,这里设置刚才新增内容的ID
        newsTitle.setNews_content_id(newsContent.getNews_content_id());
//        得到当前登录用户信息
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute(ShiroDbRealm.SESSION_USER_KEY);
        //设置新增新闻的创建人的id
        newsTitle.setNews_create_by(user.getUser_id());
        //设置新增新闻的创建时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        newsTitle.setNews_create_date(df.format(new Date()));
        //设置修改人
        newsTitle.setNews_update_by(user.getUser_id());
        //设置修改时间
        newsTitle.setNews_update_date(df.format(new Date()));
        //向数据库新增新闻 标题
        int flag2=newsMapper.insertNewsTitle(newsTitle);
        if(flag1==1&&flag2==1){
            return "SUCC";
        }
        return "FAIL";
    }

    @Override
    public NewsTitle selectNewsTitleContentByNewsTitleId(NewsTitle newsTitle) {
        NewsTitle newsTitle1AndContent=newsMapper.selectNewsTitleContentByNewsTitleId(newsTitle);
        User user =new User();
        user.setUser_id(newsTitle1AndContent.getNews_create_by());
        List<User> userList=userMapper.selectUser(user);
        if(userList.size()==1){  //如果个数是1那么说明查找到一个用户
            newsTitle1AndContent.setCreate_name(userList.get(0).getUser_name());
        }
        user.setUser_id(newsTitle1AndContent.getNews_update_by());
        List<User> userList2=userMapper.selectUser(user);
        if(userList2.size()==1){
            newsTitle1AndContent.setUpdate_name(userList2.get(0).getUser_name());
        }
        return newsTitle1AndContent;
    }

    @Override
    public List<NewsTitle> selectNewsTitleList(NewsTitle newsTitle) {
        List<NewsTitle> newsTitleList=newsMapper.selectNewsTitleList(newsTitle);
        for(int i=0;i<newsTitleList.size();i++){
            //通过 创建人的ID查找创建人的名字
            User user =new User();
            NewsTitle title=newsTitleList.get(i);
            user.setUser_id(title.getNews_create_by());
            List<User> userList=userMapper.selectUser(user);
            if(userList.size()==1){  //如果个数是1那么说明查找到一个用户
                title.setCreate_name(userList.get(0).getUser_name());
            }
            user.setUser_id(title.getNews_update_by());
            List<User> userList2=userMapper.selectUser(user);
            if(userList2.size()==1){
                title.setUpdate_name(userList2.get(0).getUser_name());
            }
            newsTitleList.set(i,title);
        }
        return newsTitleList;
    }
    @Override
    @Transactional    //事物回滚
    public String updateNews(NewsTitle newsTitle, NewsContent newsContent) {
        //1 通过新闻内容ID修改新闻内容
        newsMapper.updateNewsContentTextByNewsContentId(newsContent);
        //2 通过新闻标题ID修改新闻标题,修改最后修改人和最后修改时间
        //        得到当前登录用户信息
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute(ShiroDbRealm.SESSION_USER_KEY);
        //设置修改人
        newsTitle.setNews_update_by(user.getUser_id());
        //设置修改时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        newsTitle.setNews_update_date(df.format(new Date()));
        newsMapper.updateNewsTitle(newsTitle);
        return "SUCC";
    }

    @Override
    @Transactional    //事物回滚
    public String deleteNews(NewsTitle newsTitle) {
        newsTitle.setDel_flag("1");//1:表示已删除,0:表示没有删除
        //        得到当前登录用户信息
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute(ShiroDbRealm.SESSION_USER_KEY);
        //设置修改人
        newsTitle.setNews_update_by(user.getUser_id());
        //设置修改时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        newsTitle.setNews_update_date(df.format(new Date()));
        newsMapper.updateNewsTitle(newsTitle);
        return "SUCC";
    }

    @Override
    public List<NewsTitle> selectNewsTitleListLimitNumber(String number) {
        List<NewsTitle> newsTitleList=newsMapper.selectNewsTitleListLimitNumber(Integer.parseInt(number));
        for(int i=0;i<newsTitleList.size();i++){
            //通过 创建人的ID查找创建人的名字
            User user =new User();
            NewsTitle title=newsTitleList.get(i);
            user.setUser_id(title.getNews_create_by());
            List<User> userList=userMapper.selectUser(user);
            if(userList.size()==1){  //如果个数是1那么说明查找到一个用户
                title.setCreate_name(userList.get(0).getUser_name());
            }
            user.setUser_id(title.getNews_update_by());
            List<User> userList2=userMapper.selectUser(user);
            if(userList2.size()==1){
                title.setUpdate_name(userList2.get(0).getUser_name());
            }
            newsTitleList.set(i,title);
        }
        return newsTitleList;
    }
}
